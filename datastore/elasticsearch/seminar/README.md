

#### 1. 장애경험 공유

```
1) Cerebro가 노란색으로 바뀐다면

우선 지켜본다. 해소가 안되면 껐다 켠다. 혹은 더 지켜본다...

노란색의 의미는 replica가 있으므로 서비스에는 문제가 없다는 의미이다. 하지만 기존의 replica shard가 master node로 전환되었기 때문에 새로운 replica shard를 생성하다보니 부하가 걸리기 시작한다. 

그런데 때때로 replica shard가 생성되었는데 CPU가 올라가기 시작하는 경우가 있다. 죽었던 노드가 복구되면서 다시 Shard를 분산하기 때문이다.

이러한 리밸런싱이 모두 완료되어야 정상으로 변경된다. 이런 경험을 통해 볼 때, 복구 메커니즘에 따른 적절한 데이터 분산(Node수 혹은 Shard수)이 필요하다. 왜냐하면 일반적으로 Shard 단위만큼 장애시 replica로 데이터가 이동하면서 네트워크 대역을 차지하기 때문이다.

일반적으로 데이터 사이즈는 Compressed OOP를 고려할때 32GB 이상을 권장하지 않는다고 한다. 그리고 replica set의 개수는 많아질수록 읽기 성능이 좋아지나, 인덱싱될 때마다 전부 복사가 이루어지며 복구과정에서의 비용이 크므로 자신의 상황에 맞추어 고민하여야 한다. 

내부 네트워크는 가급적 10G로, 같은 라우터 안에 클러스터를 구성하는 것이 좋다.



2) Elasticsearch는 Transaction을 지원하지 않기때문에..

쓰기 작업을 하면 즉시 Indexing하지 않고 Queue에 쌓은 후 index thread pool에서 처리한다. 이에 bulk 요청 후 read할 경우 원하지 않는 결과가 나올 가능성이 크다. 이 경우 refresh=wait_for 옵션을 주면 indexing하고 replica set에 복사된 후 리턴된다. 그러나 이 경우 시간이 어느정도 걸리는지 테스트해봐야 한다.



3) Kibana를 헤비유저에게 오픈할 경우..

각 요청이 aggregation call이므로 구성 등에 따라 node에 부하를 줄 수 있다. 그래서 최근 Elasticsearch는 coordinating node를 제공한다. kibana는 coordinating node에 요청을 하게 되고 이 노드가 각 elasticsearch node에 요청을 하는 구조이다. 즉, 중간 버퍼역할을 하게 된다는 것인데 이 또한 coordinating node가 죽었을 경우에 대한 근본적인 한계를 갖고 있다. 결국 대안으로는 xpack을 사용하여 인가 및 접근권한 세부화하는 것인데, 이는 비용이 많이 나간다. 다른 대안으로는 proxy를 도입하여 kibana 앞에서 기한 설정 등을 관리하는 것이 제시되었다.



4) Aggregation시 장애지점이 될만한 부분

Elasticsearch의 Aggregation은 Solar와 가장 구별되는 부분으로, 기존 Lucene의 facets과는 별도로 자체적으로 구현한 기능이다.

만약 distinct 요청으로 cadinality 연산을 할 경우, 값이 아닌 데이터리스트를 주고 받는 상황에서 데이터사이즈가 크면 coordinating node가 죽는 이슈가 발생할 수 있다. 이에 Elasticsearch는 Hyperloglog++ 알고리즘을 이용한 근사값을 사용한다. 이 경우 threshold를 설정하여 샘플링 갯수를 지정할 수 있는데, 정확한 값은 자신의 데이터사이즈/건수/노드수 등을 고려하여 테스트해봐야 한다.

그렇다면 UV, PV처럼 정확한 값을 보여주어야 하는 경우는 어떻게 해야 할까?

Unique 처리한 데이터로 별도의 인덱스를 생성해서 관리하는 방식이 고려될 수 있다. 아니면 실시간 처리와 배치 처리된 데이터를 나눠서 보여주는 방안도 고려될 수 있다.
```

#### 2. 장애방지를 위한 제안
```
우선, 모니터링을 cluster/node/index 구성별로 나눠서 하여야 한다. (이때부터 시간이 촉박해서인지 빠르게 지나가서 아쉽다. 운영하면서 좀 더 metric를 살펴봐야겠다.)

그리고 환경설정에 Priority가 있다는 것을 처음 알았다. persistent, transitent, 논리적인 index 설정, 물리적인 index 설정순으로 설정이 적용된다. 가능하면 물리적인 설정은 Node명/Node타입/네트워크설정만 하도록 하고, 클리우드 전체에 적용되는 설정은 REST를 이용하여 논리적으로 설정하는 것이 좋다. 그리고 반드시 Runtime 환경에서 설정값을 확인해보아야 한다. '_cluster/settings?include_default=true'으로 실제로 설정된 값들을 확인 가능하다.
```

#### 3. 대용량 클러스터를 위한 제안
```
데이터 노드간에 네트워크 트래픽이 많이 발생하므로 의존성을 줄이시키기 위해서 Master node는 분리시키는 것이 좋다.

그리고 가능한 64G 장비에서 Elassticsearch에 31G만 할당하고 나머지는 Lucene이 쓰도록 하는 것이 좋다. Elasticsearch는 mmap을 통해 시스템 콜이 빈번히 발생하기 때문이다. 분산클러스터 특성상 고사양 서버보다는 저사양 서버 다수가 더 효율적이라고 한다. 그리고 앞서 이야기했듯 64bit 시스템이더라도 heap 메모리가 32G 이하일 경우 Compressed OOP를 사용하여 32bit 포인터 사용이 가능하므로 이를 활용하는 것이 좋다. (Context switching 등에서 Inode와 같은 메타데이터의 저장공간을 줄이기 위함인데 실제로 엄청난 성능차이가 발생하는 것도 아니고 SSD를 사용하는 등 다른 조치들이 더 효율적이므로 이 부분에 너무 스트레스받지는 말자) 
```


#### 4. 클러스터 최적화를 위한 벤치마크
```
rally를 사용하여, cluster 설정들을 바꿔가면서, 벤치마크를 돌려봐야 최적화된 클러스터를 구성할 수 있다.(https://www.elastic.co/kr/blog/announcing-rally-benchmarking-for-elasticsearch)

그리고 kibana monitoring으로 시계열정보도 같이 보는 것이 중요하다. 
```