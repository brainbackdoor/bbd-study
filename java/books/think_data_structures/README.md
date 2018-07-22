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
어떤 것이 좋은지는 둘다 시도해보고 각각 얼마나 걸리는지 알아보는 것. 이것을 프로파일링이라고 한다.
문제점 : 1) 알고리즘을 비교하려면 사전에 그것을 모두 구현해봐야 한다.
        2) 컴퓨터 성능에 의존성이 있어, 알고리즘에 따라 특정 환경에서 더 좋은 결과가 나오기도 한다.
        3) 때론, Input Data의 크기에 의존성이 있기도하다.

알고리즘 분석은 그것을 구현하지 않고도 알고리즘을 비교할 수 있게 한다.
상수시간, 선형, 이차
```

##### 2.1 선택정렬

```java
	public static void swapElements(int[] array, int i, int j) { // 상수시간
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * Finds the index of the lowest value
	 * between indices low and high (inclusive).
	 */
	public static int indexLowest(int[] array, int start) { // n - start이므로 선형
		int lowIndex = start;
		for (int i = start; i < array.length; i++) {
			if (array[i] < array[lowIndex]) {
				lowIndex = i;
			}
		}
		return lowIndex;
	}

	/**
	 * Sorts the cards (in place) using selection sort.
	 */
	public static void selectionSort(int[] array) { // n번반복하여 indexLowest(n에 비례하는 선형)와 swapElements를 실행하므로 
                                                    // n^2에 비례
		for (int i = 0; i < array.length; i++) {
			int j = indexLowest(array, i);
			swapElements(array, i, j);
		}
	}
```

##### 2.2 빅오 표기법

```
상수시간은 O(1)에 속함
선형은 O(n)
이차는 O(n^2)
```

#### ArrayList 클래스

##### 3.1 MyArrayList 메서드 분류하기

##### 3.2 add 메서드 분류하기

##### 3.3 문제 크기

##### 3.4 연결 자료구조

##### 3.6 가비지 컬렉션

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