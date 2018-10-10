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

#### LinkedList 클래스

#### 이중 연결 리스트

|구분|ArrayList|단일LinkedList|이중LinkedList|
|---|---|---|---|
|add(끝)|1|n|1|
|add(시작)|n|1|1|
|add(일반적으로)|n|n|n|
|get/set|1|n|n|
|indexOf/lastIndexOf|n|n|n|
|isEmpty/size|1|1|1|
|remove(끝)|1|n|1|
|remove(시작)|n|1|1|
|romove(일반적으로)|n|n|n|

```
ArrayList의 유일한 이점은 get, set
실행시간이 시작이나 끝 근처에 요소를 추가하거나 제거하는 연산에 의존한다면 이중 linked list가 좋다

다만, 위의 사항들은 작업하는 리스트가 매우 클 경우에(응용프로그램이 다른 일을 하느라 대부분의 시간을 소모하는 것이 아닐 경우에) 유의미하다.

또한, 실행시간 외에도 공간 측면에서, ArrayList에서 요소들은 한 덩어리의 메모리 안에 나란히 저장되어 거의 낭비되는 공간이 없는데 반해 Linked List의 경우 하나 또는 두 개의 참조가 있는 노드가 필요하다.
```

#### 트리 순회

##### HTML 파싱하기

```java
/* jsoup 사용하기 */
	public static void main(String[] args) throws IOException {
		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		
		// download and parse the document
		Connection conn = Jsoup.connect(url);
		Document doc = conn.get();
		
		// select the content text and pull out the paragraphs.
		Element content = doc.getElementById("mw-content-text");

		Elements paras = content.select("div");
		Element firstPara = paras.get(0);
		recursiveDFS(firstPara);
		System.out.println();

		iterativeDFS(firstPara);
		System.out.println();

		Iterable<Node> iter = new WikiNodeIterable(firstPara);
		for (Node node: iter) {
			if (node instanceof TextNode) {
				System.out.print(node);
			}
		}				
	}

/* DFS - 깊이 우선 탐색 */
	private static void recursiveDFS(Node node) {
		if (node instanceof TextNode) {
			System.out.print(node);
		}
		for (Node child: node.childNodes()) {
			recursiveDFS(child);
		}
	}
/*
	자식 노드를 탐색하기에 앞서 각 TextNode를 출력하므로 전위 순회에 해당
*/
```

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