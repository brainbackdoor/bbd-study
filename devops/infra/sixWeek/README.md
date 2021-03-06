1. DB 서버에 요구되는 것은 무엇일까?
   '데이터를 얼마나 빨리 내보내고 저장할 수 있는가' 초점을 맞추자.
   
2. 분류
   Server 자체, Server외 설계 및 SQL 등을 기준으로 접근해본다.
   
3. Server
   mysqld 파라미터 튜닝과 OS 튜닝 그리고 샤딩 등의 접근법이 있다.
   
   1) mysqld 파라미터 튜닝
     (1) 메모리관련 파라미터
     
      - 버퍼의 종류
      * Global Buffer: mysqld에서 내부적으로 하나만 확보되는 버퍼
      * Thread Buffer: 쓰레드(커넥션)별로 확보되는 버퍼
      즉, 쓰레드 버퍼에 많은 메모리를 할당하면 커넥션이 늘어났을 때 순식간에 메모리가 부족해진다.
      
      - 버퍼에 할당할 메모리가 많으면 성능이 올라가지만, 서버의 기준치를 넘기면 swap이 발생하여 성능이 떨어진다.
      
      - 메모리관련 파라미터
        1MB < innodb_log_file_size < 4GB
        innodb_log_file_size * innodb_log_files_in_group < innodb_buffer_pool_size
        
        * innodb_buffer_pool_size : InnoDB의 데이터나 인덱스를 캐시하기 위한 메모리상의 영역, 글로벌 버퍼이므로 크게 할당할 것을 권함(최대 512MB)
        * innodb_log_file_size : InnoDB의 갱신로그를 기록하는 디스크상의 파일. 메모리가 아니지만 튜닝에 있어 중요함. 많이 할당할수록 성능이 향상됨(최대 128MB)  
        * join_buffer_size, read_buffer_size : 인덱스를 사용하지 않는 테이블 결합/스캔 시에 사용됨
        
       (2) OS 튜닝 (디스크 I/O관련 커널 파라미터 조정, 적절한 파일시스템 선택과 마운트 옵션 조정) (추가필요)
  
  샤딩(추가필요)
 
   2) Server 외
  테이블 설계
   - 적절한 인덱스 생성 (인덱스로 고속 액세스 실현하기, 대량의 데이터를 고속으로 처리하는 기술)
     - 인덱스로 고속 액세스 실현하기
     Full Text Scan의 경우 O(n)의 계산량을 가진 선형 검색을 하게 됨. 따라서 인덱스 구조(키값, 바이트 위치)를 도입. 이 경우 인덱스 자체는 본체의 데이터와 별도로 관리하기 때문에 데이터를 업데이트할 때 인덱스를 별도로 업데이트해야 한다. 따라서 데이터 업데이트 비용은 증가하지만, 대신 검색을 고속화할 수 있다. 
     다만, 조건검색/범위검색/정렬 등에서는 해시 인덱스를 사용할 경우 그다지 도움이 되지 않는다. 이 경우 B+Tree 인덱스구조를 사용하는 것도 고려해보자. B+Tree 인덱스를 사용하면 등호검색은 물론 부등호나 전방일치검색 동의 범위검색도 리프 블록을 스캔하는 것만으로(브랜치를 그때마다 거쳐 나갈 필요없이) 완결할 수 있다.
     
     인덱스만을 읽는 검색(추가필요)
     인덱스병합(추가필요)
     
     업데이트 비용절감을 위한 노력 
       디스크에 모아서 기록하기 : 업데이트된 순서대로 디스크에 써나갈 경우 리프블록 이곳저곳에 무작위로 업데이트된다. 랜덤 액세스는 느리기 때문에 이 비용을 얼마나 감소시키느냐가 성능에 있어 중요하다. 이에 업데이트된 정보를 메모리나 전용파일 등에 일시적으로 기록하여 두고 나중에 모아서 단번에 리프블록을 갱신하는 구조를 채택했다. (Sequential write)
       병렬갱신(추가필요)
     
   - 의도적인 비정규화의 이점  (테이블 설계와 릴레이션)
   
  SQL 최적화
   - SQL 문의 실행효율
     - Query 순서
       7) SELECT
			 1) FROM
			 2) WHERE
			 3) rownum
			 4) GROUP BY
			 5) HAVING
		   6) ORDER BY
		   ---
SELECT
	B.부서명
	, COUNT(*) AS 사원수
	, SUBSTR(TO_CHAR(A.입사일,'yyyymmdd'),1,4) AS 입사년도
FROM
	직원정보 A
	, 부서정보 B
WHERE
	B.부서ID = A.부서ID
	AND TO_CHAR(A.입사일,'yyyy') >= '2000'
GROUP BY
	B.부서명
	, SUBSTR(TO_CHAR(A.입사일,'yyyymmdd'),1,4)
  
1. Column을 가공하지 말자 
    TO_CHAR(입사일) >= '2000'보다 입사일 >= TO_DATE(yyyymmdd)가 낫다
    보통 처음에 Index 생성시 Column 이름을 쓰기 때문에, Column을 가공하면 Index를 안탄다고 함
2. 범위를 줄이자
    부서ID를 기준으로 GROUP BY를 먼저 하고 WHERE 조건을 넣을 경우 직원정보테이블과 부서정보테이블이 1:1관계가 된다.
3. 불필요한 함수 사용 자제
    SUBSTR(TO_CHAR(...)) -> TO_DATE(...)

SELECT
 b.부서명
   , a.사원수
   , a.입사년도
 FROM 
 (SELECT
    부서id
  , TO_CHAR(a.입사일, 'yyyy') AS 입사년도
  , COUNT(*) AS 사원수
   FROM
   직원정보
  WHERE  입사일 >= TO_DATE('20000101', 'yyyymmdd')
 GROUP BY
   부서id
 , TO_CHAR(a.입사일, 'yyyy')
 ) a
   , 부서정보 b
WHERE  b.부서id = a.부서id
---
   
     인덱스가 되는 기본 키 외의 열만을 WHERE 조건으로 지정한 검색은 인덱스를 사용할 수 없다. 인덱스를 전혀 사용할 수 없는 경우는 테이블 전체의 레코드를 검색해야만 결과 일치 여부를 판정할 수 있다. 테이블이 거대한 경우 이 작업은 매우 무거워지며, 다른 작업에도 영향을 주게 되기 때문에 서비스 다운을 초래하는 일이 많이 있다. 
     EXPLAIN -> rows의 값이 실제로 데이터베이스 레코드에 액세스되는 횟수가 된다. 인덱스를 제대로 사용하지 않는 경우는 전체 테이블 검색을 할 수밖에 없게 되어 rows값이 커지고 시간이 걸리게 된다. 
     쿼리분석도구(추가필요)
     관리계 명령(추가필요)
     
   - 테이블 결합 순서, 방법 조정 
     my.cnf의 파라미터 튜닝 등의 세세한 튜닝보다도 우선 슬로우쿼리를 파악하고 개선해야 한다.
     슬로우쿼리로그, SHOW FULL PROCESSLIST 등으로 확인
     슬로우쿼리의 대다수는 I/O를 다수 발생시키고 있기 때문에 이 I/O를 얼마나 개선할 수 있는가가 중요한 포인트
     
     I/O 튜닝방법
      - 쿼리를 실행시키지 않기 -> 불필요한 슬로우쿼리가 있는지
      - I/O를 시키지 않기, 필요한 레코드만 I/O 시키기 -> 전체 테이블 스캔되어 있는 곳을 인덱스 검색으로 귀착시키는 방법
      - 많은 I/O가 필요한 경우 순차적인 I/O에 귀착시키기 -> 일치하는 수백개의 레코드를 개별적으로 랜덤 액세스해나가는 것이 아니라 인덱스를 스캔하는 것만으로 대상 레코드를 검색한 후에 되돌려 주어야할 레코드만을 랜덤 액세스하는 것이 효과적이다.
      - 정렬을 시키지 않기
        정렬이 정말 필요한지의 여부를 잘 생각하고 사용하자 특히 정렬 및 LIMIT가 관련된 경우, MySQL의 옵티마이저가 잘못된 판단을 할 수 있다.
        - 효율이 나쁜 인덱스를 선택하고 있는 경우
        - 인덱스의 내용을 올바로 사용하지 않은 경우(멋대로 정렬이..)
        두 경우 모두 FORCE INDEX로 인덱스를 강제하여 올바르게 인덱스를 사용
        
        실행 빈도가 많은 쿼리 밝혀내기
         n+1문제
        
        느린 트랜잭션 개선하기
         잠금 경쟁 
          - 타임아웃 설정: 잠금대기가 되었을 때 InnoDB는 innodb_lock_wait_timeout 동안만 잠금대기한다. 디폴트 50초
          - 잠금을 장시간 걸지 않기 : 데이터베이스에 대한 잠금은 트랜잭션 종료(커밋 또는 롤백)에 의해 해제된다. 그동안 잠금은 해제되지 않는다. 잠금을 보유하는 기간은 최소화되도록 설계되어야 한다. 현실적으로 문제가 되는 것은 잠금상태에서 외부네트워크에 액세스하는 것이다.(memcached, ActiveMQ 혹은 다른 MySQL 서버)
          외부 네트워크로의 액세스는 평소에는 빨리 완료할 수 있지만, TCP접속을 새로 시도할 경우에는 연결재시도를 위해 3초간 기다리는 경우가 있다.
         따라서 샤딩등의 구성에서 잠금기간을 단축하려면 잠금을 호가보하기 전에 필요한 MySQL 서버의 접속을 마치고서 작업을 진행하면 효과적이다. 또는 커넥션 플링과 같은 형태로 연결을 항상 유지해두는 것도 효과적이다.
        
      - 병렬 I/O를 시키기(주로 배치 처리, 추가 필요)
     
    슬로우쿼리, 실행계획(추가필요)

3) 주변 시스템
   캐시서버      