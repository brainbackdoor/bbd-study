### SQL Study [[SQL튜닝의 시작]](http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=9788996817932)

#### 과제
- [실행계획](http://o-m-i.tistory.com/m/85)
```sql
읽는 순서
1. 위에서 아래로
2. 안에서 밖으로

SELECT STATEMENT
    HASH JOIN
        10) VIEW
            9) HASH UNIQUE
                8) VIEW
                    7) UNION-ALL
                        2) CONNECT BY WITHOUT FILTERING
                            1) FAST DUAL
                        4) CONNECT BY WITHOUT FILTERING
                            3) FAST DUAL
                        6) CONNECT BY WITHOUT FILTERING
                            5) FAST DUAL
        ...            
---
call    count   cpu     elapsed     disk    query   current     rows                                       
                        (쿼리실행시간)       (queryblock byte값) (데이터건수)
Parse(파싱하는 시간)
Excute(실행된시간)
Fetch(실행후데이터를 가져오는 시간)
---
Rows    Row Source  OPERATION
23001   FILTER (cr=1156653 pr=0 pw=0 time=1846157 us)
                cr:block건수, pr:물리디비읽어온시간, pw:물리디비에쓴시간
```
- 용어 숙지

```
- 스칼라 서브쿼리 : Select 절에 추가로 쿼리를 사용하는 것
    - 하나의 레코드만 리턴이 가능하며, 두개 이상의 레코드는 리턴할 수 없다.
- 인라인뷰 서브쿼리 : From 절에서 테이블들이 있는 위치 괄호 안에 작성하는 것
- 서브쿼리 : Where절에 추가로 쿼리를 사용하는 것
```
```sql
SELECT A.계좌번호, B.고객번호, B.고객명, C.적립금,
    (SELECT 이름
        FROM 사원 A
        WHERE D.사원번호 = A.사원번호) 담당자_이름 -- 스칼라 서브쿼리
FROM 계좌 A,
    (SELECT 고객번호, MAX(적립금) 적립금
        FROM 포인트
        WHERE 적립일 = '20180324') C -- 인라인뷰 서브쿼리
WHERE NOT EXISTS 
    (SELECT 'X'
        FROM 입금_목록 B
        WHERE A.계좌번호 = B.계좌번호
            AND B.거래일자 LIKE '201803%') -- 서브쿼리
    AND A.지점_위치 = '서울'
    AND A.고객번호 = C.고객번호;

```

```
- Nested Loop Join : 선행 테이블의 처리범위를 하나씩 액세스하면서 그 추출된 값으로 연결할 후행테이블을 조인하는 방식(filter도 이런 방식)
    - /*+ USE_NL(A B) */
    - 주로 좁은 범위에 유리하다. (순차적으로 처리하기 때문)
    - 선행 테이블의 크기가 작거나, where절 조건을 통해 결과 집합을 제한할 수 있어야 함
    - 후행 테이블에는 조인을 위한 인덱스가 생성되어 있어야 함
    - 실행 속도 = 선행 테이블 사이즈 * 후행 테이블 접근횟수
    - 데이터를 랜덤으로 액세스하기 때문에 결과 집합이 많으면 수행속도가 저하됨

- Single Loop Join : 후행 테이블의 조인 속성에 인덱스가 존재할 경우 사용, 선행 테이블의 각 레코드들에 대하여 후행 테이블의 인덱스 접근 구조를 사용하여 직접 검색 후 조인하는 방식

- Sort Merge Join : 양쪽 테이블의 처리범위를 각자 액세스하여 정렬한 결과를 차례로 스캔하면서 연결고리의 조건으로 Merge해가는 방식(서로서로)
    - /*+ USE_MERGE(A B) */
    - 정렬되어 있지 않은 경우 우선 외부 정렬을 사용하여 정렬 후 조인
    - 조인의 대상범위가 넓을 때 발생하는 랜덤 액세스를 줄이기 위한 경우나 연결고리에 마땅한 인덱스가 존재하지 않을 때 해결하기 위한 대안
    - 연결을 위해 랜덤 액세스를 하지않고 스캔을 하면서 이를 수행
    - 정렬을 위한 영역에 따라 효율에 차이가 남
    - 조인 연결고리의 비교연산자가 범위연산(>, <)인 경우 Nested Loop 조인보다 유리
    - 두 결과집합의 크기가 차이가 많이 나는 경우에는 비효율적임

- Hash Join : 해시함수를 사용하여 두 테이블의 자료를 결합하는 조인 방식
    - /*+ USE_HASH(A B) */
    - 해시함수는 직접적인 연결을 담당하는 것이 아니라 연결될 대상을 특정지역(partition)에 모아두는 역할만을 담당함
    - Nested Loop Join과 Sort Merge Join의 문제점을 해결
    - Hash Join 만을 이용하는 것보다 Parallel processing을 이용한 Hash Join은 대용량 데이터를 처리하기 위한 최적의 솔루션을 제공
    - Hash Bucket이 조인 집합에 구성되어 해시함수 결과를 저장하여야 하는데, 이러한 처리에 많은 메모리와 CPU 자원이 소모됨 -> 지나친 오버헤드 발생 가능성
    - Hash table 생성 후 Nested loop처럼 순차적인 처리 형태로 수행함
    - 연결조건 연산자가 '='인 동치조인인 경우에만 가능
```
- ANSI 방식 : Inner Join / Outer { left, right, full } Join 
- dual (가상테이블) / rownum(가상컬럼) / rowid
```
- rownum : 결과집합에 대한 가상의 순번
- rowid : 테이블에 있는 해당 row를 찾기위해 사용되는 논리적인 정보
```

- [techonthenet](https://www.techonthenet.com/oracle/functions/index.php) 카테고리 숙지
- Chpater02 ~ Chapter 03까지 읽기 (나는 '조인동작방식' 부분 정리해가기)

#### Chapter 02 서브쿼리와 성능 문제 이해하기

#### 2. 서브쿼리 동작방식 이해하기

#### 2.1 FILTER 동작방식
- Filter 동작방식은 Main SQL에서 추출된 데이터 건수만큼 서브쿼리가 반복적으로 수행되며 처리되는 방식
- Filter 동작방식의 경우 항상 Main SQL이 먼저 수행되며, 서브쿼리는 Main SQL에서 추출된 데이터의 값을 전달받아 매번 확인하는 형태로 수행된다. 이처럼 FILTER 오퍼레이션은 항상 한 가지 방법만을 고수하기 때문에 다양한 상황에서 유연하게 대처하기가 어려운 동작 방식이라 볼 수 있다. 
- 만약, SQL의 실행계획을 점검하다 서브쿼리가 Filter 동작방식으로 수행되고 있다면, 먼저 서브쿼리의 조인 연결컬럼에 인덱스가 존재하는지 확인해야 한다. 왜냐하면, 서브쿼리가 Filter 동작방식으로 수행되는데, Full Table Scan으로 처리하고 있다면 심각한 성능 문제가 발생할 수 있기 때문이다.


#### 2.2 조인 동작방식
- Filter 동작방식과의 가장 큰 차이점은 **가변성**

> Nested Loops Join, Hash Join, Sort Merge Join, Semi Join, Anti Join 등

```
인덱스가 없는 경우 Filter 동작방식에서는 Main SQL의 추출건수 만큼 테이블을 반복적으로 Full Table Scan하게 된다.
실 운영 환경에서 인덱스를 생성하는데에는 여러가지 제약이 있다.(다른 SQL의 실행계회에 미치는 영향, 트랜잭션 프로그램의 부하, 디스크 여유 등)
```
- 서브쿼리를 조인 동작방식으로 변경하기
```sql
- MainSQL의 추출건수는 매우 많고 서브쿼리에 있는 상수 조건이 매우 효율적이어서, 서브쿼리를 먼저 수행해야 효율적인 처리가 되는 SQL
    /* 1. 서브쿼리를 조인으로 변경하여 효율적인 실행계획으로 유도 */
    /* 힌트만 변경하면 된다. */
    AND EXISTS ( SELECT /*+ NO_UNNEST */ 'x' FROM ...)
    AND EXISTS ( SELECT /*+ UNNEST_HASH_SJ */ 'x' FROM ...)

    /* 서브쿼리와 Main SQL에 조인순서(LEADING), 조인방법(NL_SJ), QUERY BLOCK(QB_NAME) 힌트를 추가하여 효율적인 실행게획으로 유도 */
    SELECT /*+ QB_NMAME(MAIN) LEADING(T2@SUB) USER_NL(T1@MAIN) /*
        ... 
        ANS EXISTS ( SELECT /* UNNEST QB_(SUM))
```
- Full Table Scan이 반드시 나쁜 것만은 아니다. IO를 줄이는 것이 더 중요하다.(Hash Join을 사용할 경우 단 한번의 Full Table Scan으로 수행되어 더 효율적일 수도 있다.)
- 다만, 먼저 읽는 테이블의 추출 결과가 많을 경우 선행테이블에 대한 Hash Map을 생성할 때 부하가 있을 수 있다. 

#### 2.3 서브쿼리 동작방식을 제어하는 힌트들
- 

#### 3. 서브쿼리를 활용한 SQL 성능 개선

#### 3.1 비효율적인 MINUS 대신 NOT EXISTS를 사용하자

#### 3.2 조인 대신 서브쿼리를 활용하자

#### 3.3 WHERE절의 서브쿼리를 조인으로 변경하자

#### Chpater 03 스칼라 서브쿼리의 이해와 효율적인 SQL 작성하기

#### 1. 스칼라 서브쿼리의 특성 이해하기

#### 1.1 최대 결과 건수만큼 반복적으로 수행된다.

#### 1.2 추출되는 데이터는 항상 1건만 유효하다.

#### 1.3 데이터가 추출되지 않아도 된다.

#### 2. 스칼라 서브쿼리와 조인의 이해 및 활용하기

#### 2.1 스칼라 서브쿼리는 최종 결과만큼 수행하자

#### 2.2 스칼라 서브쿼리와 조인의 관계로 보는 SQL 성능 문제





