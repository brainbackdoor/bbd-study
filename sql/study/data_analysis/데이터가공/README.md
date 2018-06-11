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
