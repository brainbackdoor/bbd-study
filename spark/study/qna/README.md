>https://spark.apache.org/docs/latest/rdd-programming-guide.html#which-storage-level-to-choose

#### 질문1. Shared Memory Data Parallelism (SDP)와 Distributed Data Parallelism (DDP)의 공통점과 차이점을 얘기해주세요.
```
SDP란 One compute에서 메모리를 공유해서 처리하는 것으로, 데이터를 일단 쪼갠 뒤, 각 Worker 혹은 Thead가 병렬적으로 처리하고 이를 결합하는 것을 말합니다.
DDP란 데이터를 여러 Node에 나눠 각 Node에서 이를 처리한 후 결합하며, 그러다보니 네트워크 비용이 발생할 수 있어 latency 이슈를 고려해야 합니다.

추가 질문 :  DDP에서도 각각의 SDP 처럼 쓰나요 ?
DDP 안에서 작업이 실행되는 순서를 물어보시는거죠? Spark 설정에 보시면 각 node에 thread 수를 할당해주는 부분도 있기 때문에 map operation의 경우 각 partition 안에서는 SDP 처럼 실행 된다고 보시면 됩니다.
https://stackoverflow.com/questions/46271961/are-spark-executors-multi-threaded
```

#### 질문2. 분산처리 프레임워크 Haddop의 Fault Tolerance는 DDP의 어떤 문제를 해결했나요? 
```
질문이 이해가 잘 안되네요.. Spark는 Data를 in memory에 유지하여 그리고 Immutable(변경하고 싶으면 차라리 새로 만듦으로써) 하기에, Functional transformation을 replaying해줌으로써 Fault tolerance를 확보한 것으로 알고 있습니다.
```

#### 질문3. Spark가 하둡과 달리 데이터를 메모리에 저장하면서 개선한 것 무엇이고, 왜 메모리에 저장하면 그것이 개선이 되나요? 
```
Hadoop은 Iteration할때마다 filesystem에 써야하지만(맵리듀스 잡의 결과를 다른 잡에서 사용하려면 HDFS에 저장해야 하여 반복알고리즘에는 본질적으로 맞지 않습니다), Spark는 여러 데이터 소스에서 읽을 경우 persist(), cache()를 사용합니다. memory는 disk에 비해 100배 빠르므로 속도부분에서 크게 개선하였습니다. 
```

#### 질문4. val ramyons = List("sin", "them", "nugu"); val kkodulRamyons = ramyons.map(ramyon => "koko " + ramyon); 을 사용하여 ramyons 리스트에서 kkodulRamyon List를 새로 만들었습니다. kkodulRamyons랑 똑같이 생긴 List를 만드는 Scala 코드를 써주세요. 
```scala
val kkodulRamyonsList = List("꼬들꼬들 신라면", "꼬들꼬들 틈새라면", "꼬들꼬들 너구리")
```

#### 질문5. val noodles = List(List("sin", "them", "nugu"), List("zza", "whang", "jin")); val flatNoodles = noodles.flatMap(list => list); 을 사용하여 noodles 리스트에서 flatNoodles List를 새로 만들었습니다. flatNoodles랑 똑같이 생긴 List를 만드는 Scala 코드를 써주세요. 
```scala
val flatNoodlesList = List("신라면", "틈새라면", "너구리", "짜파게티", "짜왕", "진짜장")
```

#### 질문6. val jajangs = flatNoodles.filter(noodle => noodle.contains("짜"))를 사용하여 flatNoodles 리스트에서 jajangs List를 새로 만들었습니다.jajangs랑 똑같이 생긴 List를 만드는 Scala 코드를 써주세요. 
```scala
val jajangsList = ("짜파게티", "짜왕", "진짜장")
```

#### 질문7. val jajangMenu = jajangs.reduce((first, second) => first +"," + second); 를 사용하여 jajangs 리스트에서 jajangMenu String을 만들었습니다.jajangMenu랑 똑같이 생긴 String을 만드는 Scala 코드를 써주세요. 
```scala
val jajangMenuList = ("짜파게티,짜왕,진짜장")
```

#### 질문8. Eager execution와 Lazy execution의 차이점은 무엇인가요?
```
Eager execution은 바로 실행되는데 반해, Lazy execution은 Action을 호출하기 전까지는 Transformation의 계산을 실제로 실행하지 않는다. RDD에 Action이 호출되면 Spark는 해당 RDD 계보를 살펴보고, 이를 바탕으로 연산 그래프를 작성하여 Action을 계산한다. 
```

#### 질문9. Transformation과 Action의 결과물 (Return Type)은 어떻게 다를까요? 
```
Transformation는 RDD의 데이터를 조작해 새로운 RDD를 생성하는데 반해, Action은 연산자를 호출한 프로그램으로 계산 결과를 반환하거나 RDD 요소에 특정작업을 수행하려고 실제 계산을 시작하는 역할을 한다.

Action의 경우 결과값이 Unit (Python의 None, C의 void) 인경우도 있고, collection인경우도 있고 진짜 숫자하나인 경우도 있고 다양합니다.

보통 RDD를 중간 처리 결과물을 저장하는데 쓰구요, 최종결과물은 HDFS, DB,  Text파일 등으로 저장합니다.
```

#### 질문10. RDD.cache()는 어떤 작동을 하고, 언제 쓰는 것이 좋은가? 
```
cache 함수를 호출할 경우, 추후 다른 잡을 수행할 때도 RDD가 메모리에 계속 유지되도록 지정할 수 있다. 
따라서 RDD에 API를 사용해 다양한 작업을 실행할 때 유용하다.

추가 질문 : 1 ~ 10 까지 데이타가 있는경우에
전체 노드가 1 ~ 10까지의 RDD를 메모리에 캐시 하는건가요?
아니면 자기가 로드한 데이타의 양만큼만 캐시되는건가요?
각 노드가 로드한 데이타의 양만큼만 캐시됩니다. 노드간 같은 정보공유는 broadcasting이라는 개념이 있는데요 이건 작은 데이터를 공유할때  lookup map같은 유용하게 쓰입니다.

어떤 함수에서 cache()를 했을 때 해당 함수가 종료되면 자동으로 cache()도 해제 되나요?
cache()한 rdd를 unpersist() 연산을 해줘야 메모리에서 해제된다.

그럼 중간에 한 노드가 OOM이 발생하면 다른 노드로 알아서 분산처리되나요? 그러다 혹여 OOM  확산같은게 발생하지는 않나요?
아 네 fault tolerance관련 부분이네요. 이 경우엔 특정한 횟수만큼 원데이터로 부터 여러 transformation을 거쳐 실패한 데이터를 복구 시도하도록 되있습니다. 특정한 횟수를 넘어가는 경우 application이 실패합니다. 
```

#### 질문 11. Lazy Execution이 Eager Execution 보다 더 빠를 수 있는 예를 얘기해주세요. 
```
빈번한 반복 연산이 이루어질 경우 마지막에 메모리에 올려 계산하여 값을 작성함으로써 IO를 줄일수 있다.
조건 검색 혹은 count(10)등의 경우 Full-scan 비용을 줄일 수 있다.
```
```scala
val largeList: List[String] = ...
val wordsRdd =sc.paralleize(largeList)
val lengthsRdd = wordsRdd.map(_.length) -> lazy하기 때문에 아직 아무 작업도 안함
val totalChars = lengthsRdd.reduce() -> 이때 실행이 됨.

http://knight76.tistory.com/entry/%ED%8E%8C-lazy-evaluation%EB%8A%90%EA%B8%8B%ED%95%9C-%EA%B3%84%EC%82%B0%EB%B2%95%EC%97%90-%EB%8C%80%ED%95%9C-%EC%A2%8B%EC%9D%80-%EC%84%A4%EB%AA%85-%EA%B7%B8%EB%A6%BC-%EC%9E%90%EB%A3%8C
```