#### 코드값을 레이블로 변경하기

```
로그데이터 혹은 업무데이터에 코드값으로 저장한 경우 집계에 그대로 사용하면 리포트의 가독성이 떨어지므로 변환작업이 필요하다.
```

```sql
SELECT
	user_id
	, CASE
		WHEN register_device = 1 THEN '데스크톱'  /* WHEN : 비교 조건을, THEN : WHEN 조건이 TRUE일 때 */
		WHEN register_device = 2 THEN '스마트폰'  /* 위 조건이 FALSE 일경우 다음 WHEN으로 */
		WHEN register_device = 3 THEN '애플리케이션'		
		ELSE '' /* 모든 조건이 해당되지 않을 때 */
	END AS device_name
FROM mst_users
/* 위의 CASE문은 탐색 Case문이다. 이 경우 다양한 비교조건을 사용할 수 있다. 
단순 Case문으로 변환하면, */
SELECT
	user_id
	, CASE register_device
		WHEN 1 THEN '데스크톱'
		WHEN 2 THEN '스마트폰'		
		WHEN 3 THEN '애플리케이션'		
		ELSE ''
	END AS device_name
FROM mst_users
```
```
위의 SQL은 ANSI에서 가능하고, ORACLE에서는 DECODE 구문을 사용하여야 한다.

DECODE(컬럼 or 계산식, 조건1, 답1[, 조건2, 답2, 조건3, 답3, .., ELSE값])
```
```sql
/* register_device 값이 1일때, 2일때, 3일때, 1이나 2가 아닐경우의 결과를 추출 */
SELECT DECODE(register_device, 1, '데스크톱', 2, '스마트폰', 3, '애플리케이션', '') AS device_name
FROM mst_users

/* DECODE 구문은 부등호 조건 처리가 되지 않기 때문에 예전엔 (SIN함수를 이용해서) 계산을 통해 처리하는 방법이 있었다. */ 
```

#### URL에서 요소 추출하기

```sql
/* 레퍼러로 어떤 웹 페이지를 거쳐 넘어왔는지 판별해야 하는 경우가 있다. 이 경우 페이지 단위로 집계하면 복잡해져서 Host 단위로 집계하는 것이 일반적이다. posrgreSQL은 SUBSTRING에 그룹을 사용하여, Hive 혹은 SparkSQL의 경우 parse_url 함수로, big query의 경우 host 함수로 해결 가능하다. 
*/
SELECT
	stamp
	, SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(SUBSTRING_INDEX(referrer, '/', 3), '://', -1), '/', 1), '?', 1) AS referrer_host
FROM access_log

```

#### 결손값을 디폴트 값으로 대치하기

```sql
/*
값이 없는 데이터를 의미하는 NULL

NVL(t1, t2) : t1의 값이 NULL일 경우 t2의 값으로 대체
(t1과 t2의 데이터 타입은 동일해야 함)

NLV2(t1, t2, t3) : t1의 값이 NOT NULL일 때 t2, NULL일 때 t3의 값으로 대체
(t2의 데이터 타입으로 t3가 맞춰짐)

COALESCE(expr1,expr2,expr3,…)
expr1이 NULL이 아니면 expr1값을 그렇지 않으면 COALESCE(expr2,expr3,…)값을 반환
*/
/* ORACLE */
SELECT empno, NVL(mgr, 0) mgr
  FROM emp  
 WHERE deptno = 10;

SELECT empno, NVL2(mgr, 1, 0) mgr
  FROM emp  
 WHERE deptno = 10;

/* ANSI에서도 가능 */
SELECT COALESCE(comm,1), comm 
  FROM emp;
```

#### 증감 표현

```sql
SIGN(q2 - q2) 
/* 양수라면 1, 같으면 0, 음수라면 -1을 리턴 */
```

#### 최대/최소값 찾기

```sql
SELECT
GREATEST(q1, q2, q3, q4)
, LEAST(q1, q2, q3, q4) 
FROM ...
```

#### 평균값 구하기

```sql
SELECT
(COALESCE(q1, 0) + COALESCE(q2, 0) + COALESCE(q3, 0) + COALESCE(q4, 0)) 
/ (SIGN(COALESCE(q1, 0)) + SIGN(COALESCE(q2, 0)) + SIGN(COALESCE(q3, 0)) + SIGN(COALESCE(q4, 0))) AS average
FROM ...
```

#### 두 값의 거리 계산하기

```sql
SELECT
	ABS(x1 - x2) AS `abs`
	, SQRT(POWER(x1 - x2, 2)) AS rms
FROM location_1d
```

```sql
SELECT 
	SQRT(POWER(x1 - x2, 2) + POWER(y1 - y2, 2))AS dist
FROM location_2d
```


#### RANK 

```sql
DROP TABLE IF EXISTS popular_products;
CREATE TABLE popular_products (
    product_id varchar(255)
  , category   varchar(255)
  , score      numeric
);

INSERT INTO popular_products
VALUES
    ('A001', 'action', 94)
  , ('A002', 'action', 81)
  , ('A003', 'action', 78)
  , ('A004', 'action', 64)
  , ('D001', 'drama' , 90)
  , ('D002', 'drama' , 82)
  , ('D003', 'drama' , 78)
  , ('D004', 'drama' , 58)
;


/* ORACLE의 경우 
ROW_NUMBER() OVER(ORDER BY score DESC) AS row
, RANK() OVER(ORDER BY score DESC) AS rank
, DENSE_RANK() OVER(ORDER BY score DESC) AS dense_rank -- 같은 순위의 경우 처리 

-- 현재 행보다 앞에 있는 행의 값 추출
, LAG(product_id) OVER(ORDER BY score DESC) AS lag1 
, LAG(product_id, 2) OVER(ORDER BY score DESC) AS lag2 


-- 현재 행보다 뒤에 있는 행의 값 추출
, LEAD(product_id) OVER(ORDER BY score DESC) AS lead1 
, LEAD(product_id, 2) OVER(ORDER BY score DESC) AS lead2 
등으로 가능하나, mysql은 8.0 부터나 생긴다.
https://dev.mysql.com/doc/refman/8.0/en/window-function-descriptions.html
이 함수들과 집약 함수를 조합하면 굉장히 많은 부분들을 하나의 쿼리에서도 효율적으로 수행 가능해진다.


SELECT
product_id
, score
, rank
, real_rank AS dense_rank
FROM
(
SELECT
	product_id
	, score
	, ( @rank := @rank + 1 ) AS rank
	, ( @real_rank := IF ( @last > score, @real_rank:=@real_rank+1, @real_rank ) ) AS real_rank
	, ( @last := score )
	FROM popular_products
	JOIN   ( SELECT @rank := 0, @last := 0, @real_rank := 1 ) AS b 
	ORDER BY `score` DESC
) AS a	
```

#### 열로 표현된 값을 행으로 변환하기

```sql
DROP TABLE IF EXISTS quarterly_sales;
CREATE TABLE quarterly_sales (
    year integer
  , q1   integer
  , q2   integer
  , q3   integer
  , q4   integer
);

INSERT INTO quarterly_sales
VALUES
    (2015, 82000, 83000, 78000, 83000)
  , (2016, 85000, 85000, 80000, 81000)
  , (2017, 92000, 81000, NULL , NULL )
;

SELECT
	q.year
	, CASE 
		WHEN p.idx = 1 THEN 'q1'
		WHEN p.idx = 2 THEN 'q2'
		WHEN p.idx = 3 THEN 'q3'
		WHEN p.idx = 4 THEN 'q4'		
	END AS `quarter`
	
	, CASE 
		WHEN p.idx = 1 THEN q.q1
		WHEN p.idx = 2 THEN q.q2
		WHEN p.idx = 3 THEN q.q3
		WHEN p.idx = 4 THEN q.q4		
	END AS `sales`						
FROM quarterly_sales AS q
CROSS JOIN 
(
	SELECT 1 AS idx 
	UNION ALL SELECT 2 AS idx
	UNION ALL SELECT 3 AS idx
	UNION ALL SELECT 4 AS idx
) AS p
```