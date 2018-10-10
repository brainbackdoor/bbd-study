#### Spark

```
범용적 목적의 분산 고성능 클러스터링 플랫폼 (General purpose high performance distributed platform)

Map & Reduce (cf. Hadoop)
Streaming 데이타 핸들링 (cf. Apache Storm)
SQL 기반의 데이타 쿼리 (cf. Hadoop의 Hive)
머신 러닝 라이브러리 (cf. Apache Mahout)

스파크는 다양한 연산 모델을 효과적으로 지원하는 맵리듀스(MapReduce) 모델을 대화형(Interactive) 명령어 쿼리나 스트리밍 처리 등이 가능하도록 확장하였다. 스파크는 속도를 높이기 위해 연산을 메모리에서 수행하지만, 이를 디스크에서 돌리더라도 맵리듀스보다 좋은 성능을 보인다. (맵리듀스는 반복적인 대화형 연산작업에 비효율적이다.)
또한 배치 애플리케이션, 반복 알고리즘, 대화형 쿼리, 스트리밍등의 다양한 워크로드를 단일 시스템에서 지원함으로써 저비용의 데이터 분석 파이프라인을 형성한다. 
```

```
Spark Core
분산 데이터 셋(RDD, Resilient Distributed Dataset)을 정의하는 API의 기반이 되며, 스파크의 모든 작업은 새로운 RDD를 만들거나 존재하는 RDD를 변형하거나 결과 계산으 ㄹ위해 RDD에서 연산을 호출하는 것 중의 하나로 표현된다. (작업 스케줄링, 메모리 관리, 장애 복구, 저장장치와의 연동 등)
```

```
Spark SQL
SQL뿐만 아니라 Hive Table, Parquet, JSON 등 다양한 데이터 소스를 지원한다. 이 때 단순히 SQL 인터페이스를 제공하는 것외에도 Python, Java, Scalar의 RDD에서 지원하는 코드를 SQL 쿼리와 함께 사용하여 보다 복잡한 분석 작업을 가능케 한다.
```

```
Spark Streaming
실시간 데이터 스트림(웹 서버가 생성한 로그 파일, 상태 업데이트 메시지들이 저장되는 큐 등 포함)을 처리해주는 컴포넌트이다. 
```

```
MLib
다양한 타입의 머신러닝 알고리즘뿐만 아니라 모델 평가 및 외부 데이터 불러오기 등의 기능, 최적화 알고리즘등의 ML 핵심 기능들을 지원한다고 한다. (머신러닝을 모르는 관계로 ㅡ.ㅡ)
```

```
Graph X
그래프(SNS 친구관계 그래프 등)를 다루기 위한 라이브러리로 그래프 병렬 연산을 수행한다.
```

```
Cluster Manager
yarn, apache mesos, standalone scheduler 등의 매니저 위에서 동작한다. 
```

```
저장소 계층
HDFS나 Haddop API가 지원하는 다른 저장 시스템(local file system, amazon s3, cassandra, hive, Hbase 등)으로부터 RDD를 만들 수 있다. 하지만 중요한 점은 반드시 하둡을 필요로 하지는 않는 다는 것이다. 단순히 하둡 API를 사용하는 저장시스템들을 지원하는 것 뿐이다.
```