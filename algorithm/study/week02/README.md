### [코딩인터뷰완전분석 스터디 과제] 2주차 
---
### 01 배열과 문자열

#### 해시테이블
- 해쉬테이블은 효율적인 탐색을 위한 자료구조로서 키를 값에 대응시킨다.
- 해쉬테이블을 구현하기 위해선, 연결리스트와 해시코드함수만 있으면 된다.
```
1. 키의 해시코드 계산
2. 해시코드를 이용하여 배열의 인덱스를 구함
3. 충돌에 대비하여 연결리스트를 이용
``` 
>충돌이란 서로 다른 두 개의 키가 같은 해시코드를 가리키거나 서로 다른 두 개의 해시코드가 같은 인덱스를 가리키는 경우
```
- 충돌이 자주 발생하여 최악의 경우 O(N)
- 하지만, 일반적으로 충돌을 최소화하도록 잘 구현된 경우를 가정하여 O(1)
```
```
- 연결리스트를 이용한 체이닝
- 이진 탐색트리를 이용한 체이닝
```
#### ArrayList와 가변 크기 배열
- ArrayList는 필요에 따라 크기를 변화시킬 수 있으면서도 O(1)의 접근시간을 유지한다. 
- 통상적으로 배열이 가득차는 순간, 배열의 크기를 두 배로 늘린다. 분할 상환을 고려하면, O(1)

