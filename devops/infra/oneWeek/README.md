## 서버 / 인프라 구축 입문

#### 다중화/부하분산의 기본

```
1.1 다중화의 기본
다중화란
- 다중화란, 장애가 발생해도 예비 운용장비로 시스템의 기능을 계속할 수 있도록 하는 것을 말한다.

다중화의 본질
1) 장애를 상정한다.
- 라우터/서버 등 장애
2) 장애에 대비해서 예비 운용장비를 준비한다.
3) 장애가 발생했을 때 예비 운용장비로 교체할 수 있는 운용체제를 정비한다.
- 라우터 장애 대응(Cold Standby)
  물리적으로 회선 연결을 변경하거나 전원을 투입
  네트워크 장비는 설정 변경이 빈번하지 않으므로 가능함
- 서버 장애 대응(Hot Standby)  
  항상 전원을 켜두고 네트워크에 연결해두는 것이 좋다.
  현재 운용장비의 내용을 갱신할 때에 같이 갱신되어야 한다.

장애극복
웹서비스는 VIP를 할당하여, 장애 발생시 예비 운용장비가 VIP를 인계한다.

장애검출
ICMP/Port/HTTP 등을 요청하여 응답여부 체크

Active/Backup 구성 만들기
/// 구성
/// ARP 에 대하여 (GARP)

서버를 효과적으로 활용하자
- LB로 구성하는 것이 더 효과적이다.
```

```
1.2 웹 서버의 다중화
DNS 라운드로빈
- DNS를 이용해서 하나의 서비스에 여러 대의 서버를 분산시키는 방법
  DNS 서버는 동일한 이름으로 여러 레코드를 등록시키면 질의할 때마다 다른 결과를 반환한다. 다만, 이 경우 1)서버의 수만큼 IP주소가 필요 2)균등하게 분산되지 않음(캐싱, 프록시 서버 경유 등) 3)서버가 다운되어도 감지하지 못함
  
DNS 라운드로빈의 다중화 구성 예
/// 구성
보다 편하게 시스템 확장하기
- 서버가 3대가 되었을 경우
  어떤 서버가 VIP를 인계할지
  두 대의 서버가 같은 IP를 지닐 가능성
  한번 정지한 서버를 복귀시키기 곤란
```

```
1.3 웹 서버의 다중화
DNS 라운드로빈과 로드밸런서의 차이
- 로드밸런서는 하나의 IP주소에 대해 요청을 복수의 서버로 분산할 수 있다. 
  클라이언트로부터 전송되어 온 요청을 실제 웹 서버로 중계함으로써 자신이 웹서버인 것처럼 동작한다.
  
IPVS
- 이 책의 LB는 L4를 의미
  L4SW의 경우 IP주소나 포트번호에 따라 분산대상 서버를 지정할 수 있음
  L7SW의 경우 URL에 따라 분산대상 서버를 지정할 수 있음

스케줄링 알고리즘
- rr : 모든 서버 균등
- wrr : 가중치 부여
- lc : ESTABLSHED/TIME_WAIT/FIN_WAIT인 접속수가 가장 적은 서버
- wlc : wrr + lc
- sed : ESTABLSHED인 접속수가 가장 적은 서버
- nq : sed (+ active가 0인 서버 최우선)

IPVS 사용하기
- ipvsadm : command tool
            가상서버를 정의하고 리얼서버를 할당
            설정내용/접속상황/전송률 등을 확인
- keepalived : 설정파일을 기준으로 IPVS의 가상서버를 구축
               다운된 서버를 부하분산에서 제외
               모두 다운되었을 경우 에러 메시지 전송
               
로드밸런서 구축하기
// keepalived 구성

L4스위치와 L7스위치
- L4SW에서는 클라이언트가 통신하는 곳은 리얼서버지만, 
  L7SW에서는 로드밸런서와 클라이언트가 TCP 세션을 전개한다.
  
L4스위치의 NAT구성과 DSR구성
- NAT는 패킷의 수신지 주소를 변경하지만, DSR은 IP주소를 변경하지 않음
// NAT vs DSR vs DNAT

동일 서브넷인 서버를 부하분산할 경우 주의사항
```

```
1.4 라우터 및 로드밸런서의 다중화
다중화란
다중화 프로토콜 VRRP
- HSRP vs VRRP

VRRP의 구조
keepalived의 구조상의 문제
- garp 의 지연송신(STP 컨버전스로 인한 지연현상///)
keepalived 다중화
keepalived 응용
- vrrp 기능만 독립적으로 사용 가능
```


#### 다중화란? 

```
- 다중화란, 장애가 발생해도 예비 운용장비로 시스템의 기능을 계속할 수 있도록 하는 것을 말한다. 
다중화의 대상은 Server, Load balancer, Network Device 등이 있을 수 있다. 
단일장애점(SPOF)을 없애고 무중단/고가용성 서비스를 위해 다중화가 필요하다.

OnPremise환경에서의 다중화 관점
1) 장애를 상정한다.
2) 장애에 대비해서 예비 운용장비를 준비한다.
3) 장애가 발생했을 때 예비 운용장비로 교체할 수 있는 운용체제를 정비한다.
- 유휴장비 역시 비용이므로 ROI에 따라 다중화 수준을 정한다.

HealthCheck
ICMP/Port/HTTP 등을 요청하여 응답여부 체크
ping check의 경우 IP 정보만으로 요청이 가능한지를 확인하며,
port check의 경우 해당 서비스의 정상 구동 여부를 확인할 수 있다.
HTTP request의 경우 HTTP status code를 기반으로 보다 상세하게 이상유무를 판단할 수 있다.

기본 구성(Active/StandBy)
VIP를 할당하고 장애 발생시 예비 운용장비가 VIP를 인계한다.
구성 방식은 HSRP, VRRP 등의 프로토콜을 활용할 수 있으나, 내부적으로는 GARP를 사용하게 된다.

* GARP? 
장비가 ARP 패킷을 broadcast를 통해 같은 네트워크상에 있는 다른 장비에 자신의 존재를 알리는 목적으로 사용한다. 
이 경우 시스템은 아무런 정보도 얻지 못하지만 이 장비에 대한 엔트리를 가지고 있는 장비는 자신의 ARP cache를 갱신하여 이 장비의 엔트리가 만료되지 않도록 한다. 
또한 MAC 주소 변경시에 다른 호스트의 Cache를 갱신할 목적으로도 전송하며, 
이중화 게이트웨이 프로토콜(HSRP/VRRP)에서 Active Router가 변경되었을 경우 새로 활성화된 Router가 이를 발송한다.

보통은 Active/StandBy 형태로 구성하지는 않고(비효율적), LB를 앞단에 두어 부하분산을 한다. 
과거에 DNS RR 방식으로도 구성하였으나, 이는 
1)서버의 수만큼 IP주소가 필요 
2)균등하게 분산되지 않음(캐싱, 프록시 서버 경유 등) 
3)서버가 다운되어도 감지하지 못하는 등의 문제로 자주 사용되진 않는다.

LB는 OSI 7 Layer 기준으로 L4/L7으로 나뉜다.(elb vs alb)
L4SW의 경우 IP주소나 포트번호에 따라 분산대상 서버를 지정할 수 있다.
L7SW의 경우 URL에 따라 분산대상 서버를 지정할 수 있다.
L4SW에서는 클라이언트가 통신하는 곳은 리얼서버지만, 
L7SW에서는 로드밸런서와 클라이언트가 TCP 세션을 전개한다.

LB 장비가 고가이므로, 오픈소스인 ipvs를 사용하여 구성하는 것도 고려할만 하다.
- ipvsadm : command tool
            가상서버를 정의하고 리얼서버를 할당
            설정내용/접속상황/전송률 등을 확인
- keepalived : 설정파일을 기준으로 IPVS의 가상서버를 구축
               다운된 서버를 부하분산에서 제외
               모두 다운되었을 경우 에러 메시지 전송
스케줄링 알고리즘
- rr : 모든 서버 균등
- wrr : 가중치 부여
- lc : ESTABLSHED/TIME_WAIT/FIN_WAIT인 접속수가 가장 적은 서버
- wlc : wrr + lc
- sed : ESTABLSHED인 접속수가 가장 적은 서버
- nq : sed (+ active가 0인 서버 최우선)
```
<img width="651" alt="vrrp" src="https://user-images.githubusercontent.com/29951288/54737335-c4f3ed80-4bf2-11e9-95fe-5c8bc2aad888.png">
<img width="549" alt="stp" src="https://user-images.githubusercontent.com/29951288/54737340-ccb39200-4bf2-11e9-87b3-25a17e9af2a5.png">

## 책의 범위는 아니지만 다중화와 관련된 주제들

#### Nginx를 활용한 부하분산

```
다음 챕터의 내용이므로 생략합니다. :)
```

#### PublicCloud 환경에서의 다중화(scale up -> scale out)

```
- multi az
- elb(alb) + auto scaling
  CPU, Memory 외에도 Tomcat의 Thread나 DB Idle count 등의 metric도 임계치로 설정 가능
```

####  Container 운용상에서의 다중화(표준화,코드화)

```
- ami 등 이미지의 단점: 세부 내용을 보거나 변경이 어려움(버전관리 x)
  Dockerfile을 통해 코드화 가능
- Container란, 리눅스 기술을 사용하여 선박의 컨테이너처럼 프로세스가 사용하는 자원을 격리
  Docker의 경우 이미 존재했지만 사용하기 어려웠던 리눅스 기술들
  (Namespaces, Cgroups, Storage, Networking, Security 등)을 잘 포장하여 제공
  기존 OS위에 GuestOS를 올려 Hypervisor를 통해 하드웨어 자원을 할당받아 사용하던 VM과 비교하여, 
  HostOS 위에서 동작하는 Docker가 훨씬 효율적
- Docker는 cold booting이 없으므로 scale out하기에 유연하며, 
   aufs 를 사용하여 layer로 구성되어 있기 때문에 fetch도 수월하게 진행된다.
```

#### Kubernetes

<img width="854" alt="kubernetes" src="https://user-images.githubusercontent.com/29951288/54737392-10a69700-4bf3-11e9-9c2e-58a12d6ff259.png">

![asa](https://user-images.githubusercontent.com/29951288/54737405-1f8d4980-4bf3-11e9-9505-cdb46620a34f.png)

```
#### 다중화와 관련해서는 Pods의 개념이 중요하며, 하단에서 Pods을 관리하는 스케줄러의 기능(4.)만 보셔도 됩니다. 
(Pods의 상태 관리는 Controller-manager 중 Replica-set Controller 등이 수행합니다.)

1. kubernetes component는 오직 api 서버와 통신한다. 
즉, api 서버는 etcd와 통신할 수 있는 유일한 component다.

2. etcd 는 key-value storage로, 모든 object(pod, rc, service, secret 등)을 분산 저장한다. 
api server를 통해 간접적으로 읽고 쓰이는데 이때 낙관적 잠금방식을 통해 동시성 제어를 하며 우선순위는 RAFT 알고리즘을 활용한다
(이에 etcd 인스턴스는 홀수여야 한다.https://blog.containership.io/etcd/)

3. kubectl 등의 client로 api server에 post요청을 할때, 인증/권한승인/승인제어 플로그인 등을 거치게 된다. 
이 후 api server는 object의 유효성을 검사하고 etcd에 저장한 다음 client에 응답을 전달한다.

4. Scheduler는 Pod가 1)스케줄될 수 있는 모든 노드의 목록을 얻은 후 2)우선순위를 정한다.
(1) 노드가 포드의 하드웨어 리소스 요청을 이행할수 있는지
(2) 노드에 리소스가 부족한지(메모리나 디스크 상태)
(3) 노드가 포드 스펙의 노드 셀렉터에 맞는 라벨을 가질 수 있는지
(4) 특정 호스트 포트에 바인딩 된 경우, 해당 노드에 포트가 이미 사용되고 있는지
(5) 포드가 특정 볼륨 유형을 요청하는 경우 이 볼륨을 노드가 마운트 할 수 있는지
등의 부분을 체크한다. 

5. Controller-manger에는 RC manager, Replica-set/demon-set/Job-Controller, Deployment Controller, Stateful Controller, Node Controller, Service Controller, Endipoint Controller, Namespace Controller, PersistentVolume Controller 등이 있다. 

6. 실제 포드 컨테이너는 Worker node에서 실행된다. 
1) kubectl을 사용하여 (api server에) Deployment Resource를 생성요청을 하면, 
2-1) Deployment Controller가 이를 감지하고 (api server에) ReplicaSet 요청을 한다.
2-2) 이를 ReplicaSet Controller가 감지하고 (api server에) Pod 생성 요청을 한다. 
2-3) Scheduler는 이를 감지하고 Node에 Pod를 할당하며
3) Kubelet은 node에 스케줄되는 pod 할당을 감지하고 pod의 container를 실행하고 api 서버에 보고한다. 

7. kube-proxy는 endpoint object의 변경을 감지하며, client가 kubernetes api에 정의된 Pods에 연결될 수 있도록 리다이렉팅한다. (내부적으로 iptables 사용)

8. 모든 포드가 클러스터 내에서 사용하는 DNS Server pod가 있다. URL로 라우팅하기 위해 Nginx reverse proxy를 활용한 pod도 addon하여 사용할 수 있다. 
```


