### SQL Study [[SQL튜닝의 시작]](http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=9788996817932)

```sql
1123445
13779
UNION (중복을 제거하고 합침) -> 1234579
UNION ALL (그냥 붙여놓음) -> 112344513779
조인은 곱하기다
UNION은 더하기다
FULL OUTER JOIN하면 1이 두개나옴
MINOUS 하면 245 혹은 79
UNION / UNION ALL / MINUS 는 컬럼 갯수, 타입등이 같아야 한다.
Nested Loop일 때는 모수 데이터가 많으면 부하가 크므로 꼭 DISTINCT + NOT EXISTS가 답은 아니다.
```

#### 3. 서브쿼리를 활용한 SQL 성능 개선

#### 3.1 비효율적인 MINUS 대신 NOT EXISTS를 사용하자
```sql
/* MINUS */
SELECT ... FROM A MINUS SELECT ... FROM B
1. 테이블 A에서 데이터 추출
2. 추출된 데이터 SORT 연산
3. 테이블 B에서 데이터 추출
4. 추출된 데이터 SORT 연산
5. 2번과 4번 데이터 비교 후 최종 데이터 추출
수행 순서는 A -> B로 고정

/* NOT EXISTS */
SELECT ... FROM A WHERE NOT EXISTS ( SELECT ... FROM B WHERE B.XX = A.XX )
1. 테이블 A에서 데이터 추출
2. 1번에서 추출한 데이터와 서브쿼리 테이블 B 데이터와 비교 후 최종 데이터 추출
수행 순서는 A -> B or B -> A (다만, 서브쿼리에서 MainSQL으로 올리는 건 SEMI)
NOT EXISTS 의 경우 SELECT 절에 나열된 컬럼의 조합이 UNIQUE 하지 않다면, 중복된 값이 추출될 가능성이 있다. 
이 경우 데이터 정합성이 훼손될 수 있다. 그래서 이 경우 DISTINCT 처리해야 한다. 
```
#### 3.2 조인 대신 서브쿼리를 활용하자
```
확인자 역할을 수행하는 테이블이란, 
FROM 절에 나열된 테이블 중 SELECT 절에 추출하는 컬럼은 없고, 단순히 WHERE 절에 조인조건이나 FILTER 조건으로만 사용되는 테이블을 말한다.
이 경우 값을 추출하지 않고 EXISTS를 사용하는 서브쿼리로 대체해도 데이터 정합성을 훼손하지 않는다.
```

#### 3.3 WHERE절의 서브쿼리를 조인으로 변경하자
```
WHERE 절에 서브쿼리가 많다면, Optimizer가 SQL에 대한 최적의 실행계획을 수립하는 것은 힘들다. 왜냐하면 서브쿼리들이 가질 수 있는 모든 조합에 대한 Cost를 계산해야 하므로 Optimizer가 실행계획을 최적화하는 과정이 더 부하가 될 수 있어, 과감하게 정확도를 포기하고, 모든 서브쿼리를 Unnest 수행한 것과 모두 수행하지 않은 것 두가지의 Cost만 계산하기 때문이다. 
```


