#### 1장 아파치 스파크 소개

##### 1.1 스파크란

```
고속 범용 분산 컴퓨팅 플랫폼
Hadoop MapReduce보다 10~100배 빠른 속도로 같은 작업 수행
사용자가 Cluster를 다루고 있다는 사실을 인지할 필요가 없도록 설계된 컬렉션 기반의 API 제공
Hadoop이 HDFS + Mapreduce 처리엔진으로 구성된데 반해, Spark는 대량의 데이터를 메모리에 유지하는 독창적인 설계로 계산 성능을 대폭 끌어올렸다. 
Spark는 MapReduce와 유사한 일괄처리 기능, 실시간 데이터 처리기능, SQL과 유사한 정형데이터 처리 기능, 그래프 알고리즘, 머신러닝 알고리즘을 모두 단일 프레임워크와 통합했다.

R/Python/MATLAB 등은 memory 기반의 프로그램으로는 빅데이터를 다루기 어렵다.

분산 아키텍처 때문에 처리 시간에 약간의 오버헤드가 발생한다. 따라서 단일 머신에서도 충분히 처리할 수 있는 데이터셋을 다룰때는 비효율적일 수 있다. 또한 대량의 원자성 트랜잭션을 빠르게 처리해야 하는 OLTP 작업에는 적합하지 않다. 반면, 일괄처리 작업이나 데이터 마이닝같은 온라인 분석처리(OLAP) 작업에는 적합하다

Parallelization : 전체 연산을 잘게 나누어 동시에 처리하는 방법
Distribution : 데이터를 여러 노드로 분산하는 방법
Fault tolerance : 분산 컴포넌트의 장애에 대응하는 방법

맵리듀스의 한계
1. 맵리듀스 잡의 결과를 다른 잡에서 사용하려면 HDFS에 저장해야 하여 반복알고리즘에는 본질적으로 맞지 않다.
2. 모든 문제를 일련의 맵과 리듀스 연산으로 분해할 수 없으며, 맵리듀스 API를 사용하여 해결하기 어려운 문제들이 있다.
3. Hadoop이 raw level framework라, 데이터 내보내기/가져오기, 조작, 실시간 처리 등 상위 레벨의 도구들이 우후죽순 등장하여 복잡해짐
그리고 무엇보다도 느리다. memory는 disk에 비해 100배 빠르며 network 통신에 비해 일반적으로 100만배 빠르다. Hadoop은 Iteration할때마다 filesystem에 써야한다. Spark는 여러 데이터 소스에서 읽을 경우 persist(), cache()를 사용한다.
Spark는 Functional Programming 개념을 개져와서 Latency 문제를 해결하였다. Data를 in memory에 유지하여 그리고 Immutable(변경하고 싶으면 차라리 새로 만듦으로써) 하기에, Functional transformation을 replaying해줌으로써 Fault tolerance를 확보할 수 있다. 또한 lazy evaluation을 통해 network bottleneck을 피할 수 있다. 
```

```
데이터 병렬처리 vs 분산된 데이터 병렬처리
병렬처리란, One compute에서 메모리를 공유해서 처리하는 것으로, Shared memory data parallelism에선 데이터를 일단 쪼갠 뒤, 각 Worker 혹은 Thead가 병렬적으로 처리하고 이를 결합하는 것을 말한다.
분산된 데이터 병렬처리란, 데이터를 여러 Node에 나눠 각 Node에서 이를 처리한 후 결합한다. 
그러다보니 네트워크 비용이 발생할 수 있어 latency 이슈를 고려해야 한다. 
```

##### 1.2 스파크를 구성하는 컴포넌트

```
RDD는 분산 데이터 컬렉션(즉, 데이터셋)을 추상화한 객체로 데이터셋에 적용할 수 있는 연산 및 변환 메서드를 함께 제공한다.
```

##### 1.3 스파크 프로그램의 실행과정

```
Spark는 Data locality를 최대한 달성하려고 파일의 각 블록이 저장된 위치를 하둡에게 요청한 후, 모든 블록을 클러스터 노드의 RAM 메모리로 전송한다. 
데이터 전송이 완료되면 스파크셸에서 RAM에 저장된 각 블록(partition이라 함)을 참조할 수 있다. 이 파티션의 집합이 RDD가 참조하는 분산 컬렉션이다. 
RDD에 API를 사용해 다양한 작업을 실행할 수 있으며, 이 RDD에 cache 함수를 호출해서 추후 다른 잡을 수행할 때도 RDD가 메모리에 계속 유지되도록 지정할 수 있다. 
```

##### 1.4 스파크 생태계

```
Hadoop Ecosystem은 인터페이스 도구, 분석 도구, 클러스터 관리 도구, 인프라 도구로 구성된다.
```

---

#### 2장 스파크의 기초

##### 2.1 가상머신 사용

```
1. virtualbox, vagrant install
2. vagrant box add --name manning/spark-in-action spark-in-action-box.json
   vagrant init manning/spark-in-action
3. Vagrantfile correct
    config.vm.network "private_network", ip: "192.168.33.10"
    config.vm.network "public_network"
4. vagrant up    
5. 해당 가상머신 터미널 접속
6. git clone https://github.com/spark-in-action/first-edition.git
7. echo $JAVA_HOME
   hadoop fs -ls /user
8. /usr/local/hadoop/sbin/start-dfs.sh
   vi /usr/local/spark/conf/log4j.properties
   가장 하단에 추가
   		log4j.logger.org.apache.hadoop=WARN
9. spark-shell

```

##### 2.2 스파크 셸로 첫 스파크 프로그램 설정

```
RDD
- 불변성(immutable) : 읽기전용
- 복원성(resilient) : 장애내성
- 분산(distributed) : 노드 한 개 이상에 저장된 데이터셋
RDD의 목적은 분산 컬렉션의 성질과 장애 내성을 추상화하고 직관적인 방식으로 대규모 데이터셋에 병렬 연산을 수행할 수 있도록 지원하는 것이다.
일반적인 분산 프레임워크는 노드에 장애가 발생하면 온전한 복제본을 가져와 데이터를 복원하지만, RDD는 데이터셋을 만드는데 사용된 변환 연산자의 로그를 가져와 해당 부분만 다시 계산해 복원한다. 

How Create RDD  ?
기존의 RDD를 변경하거나, SparkContext(or SparkSession)로부터..	
	parallelize : convert a local Scala collection to an RDD
	textFile : read a text file from HDFS or local file system and return an RDD of string
```

##### 2.3 RDD의 기본 행동 연산자 및 변환 연산자

```
Transformation : RDD의 데이터를 조작해 새로운 RDD를 생성(filter, map 등)
Action : 연산자를 호출한 프로그램으로 계산 결과를 반환하거나 RDD 요소에 특정작업을 수행하려고 실제 계산을 시작하는 역할 (count, foreach 등)

Transformation의 lazy evaluation은 Action을 호출하기 전까지는 Transformation의 계산을 실제로 실행하지 않는 것을 의미한다. RDD에 Action이 호출되면 Spark는 해당 RDD 계보를 살펴보고, 이를 바탕으로 연산 그래프를 작성하여 Action을 계산한다. 이렇게 하는 이유는 Network Communication 비용을 최소화하기 위해서이다.
```

```scala
val largeList: List[String] = ...
val wordsRdd =sc.paralleize(largeList)
val lengthsRdd = wordsRdd.map(_.length) -> lazy하기 때문에 아직 아무 작업도 안함
val totalChars = lengthsRdd.reduce() -> 이때 실행이 됨.

Benefits of Laziness for large-scale data
val lastYearsLogs: RDD[String] = ..
val firstLogsWithErrors = lastYearsLogs.filter(_.contains("ERROR")).count(10)
							10개까지만 찾고 다른 일은 하지 않음
```

```
Scala와 signature가 똑같다
map[B](f: A => B): List[B] 	// Scala List
map[B](f: A => B): RDD[B] 	// Spark RDD
Scala와 유사하지만 데이터가 여러 노드에 분산되있다는 차이가 있다. RDD와 scala는 매우 닮았지만 사용하려면 실제로 어디서 동작하는지 파악해야 한다.
```

```scala
val first10 = people.take(10) -> driver program에 저장됨
								
people.foreach(println) -> executor 상에서 실행되므로 driver node에는 아무런 output이 없음
```

```scala
val rdd = spark.textFile("hdfs://...")
val count = rdd.flatMap(line => line.split(" ")) 	// separate lines into words
				.map(word => (word, 1))			  // include something to count
				.reduceByKey(_ + _)				  // sum up the 1s in the pairs
```

```
Seq : Spark의 Collection interface로, 이 인터페이스를 구현한 클래스로는 Array, List 등이 있다.
parallelize 메서드는 seq 객체를 받아 이 seq객체의 요소로 구성된 새로운 RDD를 생성

_ : 플레이스홀더로, '이 함수가 호출될 때 인자로 전달된 객체가 무엇이든 다음 함수를 호출하라'는 의미
```

```scala
val numbers = sc.parallelize(10 to 50 by 10)
numbers.foreach(x => println(x))
val numbersSquared = numbers.map(num => num * num)
val reversed = numbersSquared.map(_.toString.reverse)
reversed.foreach(x => println(x))
```

```
collect action은 새로운 배열을 생성하고 RDD의 모든 요소를 이 배열에 모아 스파크 셸로 반환한다. 
flatmap은 익명함수가 반환한 배열의 중첩구조를 한 단계 제거하고 모든 배열의 요소를 단일 컬렉션으로 병합한다. 
```