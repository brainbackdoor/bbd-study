## Think Data Structures

#### 인터페이스

##### 1.1 리스트가 두 종류인 이유

```
어느 것이 더 좋을지는 수행하는 동작에 달려있다. ㅡ.ㅡ?
```

##### 1.2 자바 interface

```
메서드 집합을 의미한다. Interface를 구현하는 클래스는 이러한 메서드를 제공해야 한다.
```

##### 1.3 List Interface

```java
//JCF(Java Collections Framework)는 List라는 Interface를 정의하고 ArrayList와 LinkedList라는 두 구현 클래스를 제공한다.
//인터페이스 기반의 프로그래밍을 하자


public class ListClientExample() {
    private List list;
    public ListClientExample() {
        list = new LinkedList(); // 나중에 ArrayList 클래스를 사용하고자 한다면 생성자만 바꾸면 된다.
    }
    private List getList() {
        return list;
    }
}
```

#### 알고리즘 분석

```
```

#### ArrayList 클래스

#### LinkedList 클래스

#### 이중 연결 리스트

#### 트리 순회

#### 철학으로 가는 길

#### 인덱서

#### Map 인터페이스

#### 해싱

#### HashMap 클래스

#### TreeMap 클래스

#### 이진탐색 트리

#### 영속성

#### 위키피디아 크롤링

#### 불리언 검색

#### 정렬