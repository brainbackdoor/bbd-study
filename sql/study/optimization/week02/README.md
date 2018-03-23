### SQL Study [[SQL튜닝의 시작]](http://www.kyobobook.co.kr/product/detailViewKor.laf?barcode=9788996817932)

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

#### Chapter 02 서브쿼리와 성능 문제 이해하기

#### 2. 서브쿼리 동작방식 이해하기

#### 2.1 FILTER 동작방식

#### 2.2 조인 동작방식
- Filter 동작방식과의 가장 큰 차이점은 **가변성**

> Nested Loops Join, Hash Join, Sort Merge Join, Semi Join, Anti Join 등

```
인덱스가 없는 경우 Filter 동작방식에서는 Main SQL의 추출건수 만큼 테이블을 반복적으로 Full Table Scan하게 된다.
실 운영 환경에서 인덱스를 생성하는데에는 여러가지 제약이 있다.(다른 SQL의 실행계회에 미치는 영향, 트랜잭션 프로그램의 부하, 디스크 여유 등)
```
- 서브쿼리를 조인 동작방식으로 변경하기
```sql
    /* 1. 서브쿼리를 조인으로 변경하여 효율적인 실행계획으로 유도 */
    /* 힌트만 변경하면 된다. */
    AND EXISTS ( SELECT /*+ NO_UNNEST */ 'x' FROM ...)
    AND EXISTS ( SELECT /*+ UNNEST_HASH_SJ */ 'x' FROM ...)

    /* 서브쿼리와 Main SQL에 조인순서(LEADING), 조인방법(NL_SJ), QUERY BLOCK(QB_NAME) 힌트를 추가하여 효율적인 실행게획으로 유도 */
    SELECT /*+ QB_NMAME(MAIN) LEADING(T2@SUB) USER_NL(T1@MAIN) /*
        ... 
        ANS EXISTS ( SELECT /* UNNEST QB_(SUM))
```

#### 2.3 서브쿼리 동작방식을 제어하는 힌트들

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





