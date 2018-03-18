### SQL Study [[SQL튜닝의 시작]](http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=9788996817932)

#### Chapter 01 SQL 튜닝의 시작은?

#### 1. Oracle Query 익히기
```sql

SELECT *
FROM (
	SELECT /*+ INDEX_DESC (A IDX_MBOX_SENDDATE) */
		a.*,
		rownum AS rnum
	FROM 	tbs_mbox a
	WHERE 	userid = :b1
	AND	STATUS = :b2
	AND	ROWNUM <= :b3
)
WHERE rnum >= :b4;

SELECT statement - choose- cost estimate:3
 VIEW
  COUNT stopkey
   TABLE access BY INDEX rowid :imsi.tbs_mbox
    INDEX RANGE scan descending :imsi.id_mbox_senddate

-------------------------------------
INDEX_NAME          COLUMN List
-------------------------------------
idx_mbox_status     userid, status
idx_mbox_senddate   userid, senddate
-------------------------------------
```
```
- /*+ ~~ */ : HINT
- INDEX_DESC : HINT 이름
- A : Table Alias
- IDX_MBOX_SENDDATE : INDEX 이름
- :b1~4 : 변수
- ROWNUM : 항상 1부터 시작하며, index는 정렬되어서 들어옴

- Oprimizer는 실행계획에서 방향을 잡아주는 역할을 함 / Cost방식과 Rule 방식이 있음
```

#### 2. Query 순서
```sql
7) SELECT
1) FROM
2) WHERE
3) rownum
4) GROUP BY
5) HAVING
6) ORDER BY
```

#### 3. Query 최적화 연습문제
```sql
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
```
```
1. Column을 가공하지 말자 
    TO_CHAR(입사일) >= '2000'보다 입사일 >= TO_DATE(yyyymmdd)가 낫다
    보통 처음에 Index 생성시 Column 이름을 쓰기 때문에, Column을 가공하면 Index를 안탄다고 함
2. 범위를 줄이자
    부서ID를 기준으로 GROUP BY를 먼저 하고 WHERE 조건을 넣을 경우 직원정보테이블과 부서정보테이블이 1:1관계가 된다.
3. 불필요한 함수 사용 자제
    SUBSTR(TO_CHAR(...)) -> TO_DATE(...)
```

#### 4. 서브쿼리의 사용패턴
- 비교 
```sql
SELECT  *
FROM    emp
WHERE   sal > ( SELECT AVG( sal ) FROM emp )
```
```
- 추출 결과가 반드시 1건이어야 한다.
```

- 필터
```sql
SELECT  c1, c2, c3
FROM    SUBQUERY_T2 t2
WHERE   c2 = 'A' 
        AND EXISTS (
                SELECT  /*+ NO_UNNEST */
                        'x'
                FROM    SUBQUERY_T1 t1
                WHERE t1.c5 = t2.c2
        )
```
```
- NO_UNNEST : 조인하지 않도록..
```

#### 5. IN 과 OR의 차이
```sql
A IN [ 1, 2, 3 ]
A = 1 OR A = 2 OR A = 3
```
```
- 후자의 경우 full scan될 수도 있다.
```

#### 6. IN 과 EXISTS의 차이
```
- IN : 제공 
        즉, 서브쿼리의 IN이 먼저 돎
- EXISTS : 필터 (Nested LOOP)
            즉, FROM에 있는 절이 먼저 돎
```

---

#### 과제
- 실행계획
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
```
- 용어 숙지
```
- 스칼라 서브쿼리 / 인라인뷰 서브쿼리 / 서브쿼리
- Oracle : Nested Loop Join / Hash Join / Sort merge Join
- ANSI 방식 : Inner Join / Outer { left, right, full } Join 
- dual (가상테이블) / rownum(가상컬럼) / rowid
```
- [techonthenet](https://www.techonthenet.com/oracle/functions/index.php) 카테고리 숙지
- Chpater02 ~ Chapter 03까지 읽기 (나는 '조인동작방식' 부분 정리해가기)


