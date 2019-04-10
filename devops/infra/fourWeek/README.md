#### 1. DNS 다중화
- 주소 변환 라이브러리를 이용한 다중화와 문제점
  /etc/resolv.conf에 여러 DNS 서버를 지정할 경우,
  질의가 타임아웃되면 다음 네임서버에 질의하는데, 이 때 지연현상이 발생한다.(디폴트는 5초)
  만약 네임서버가 장애라면 매번 질의할 때마다 지연현상이 발생하여 성능저하가 나타난다. 이 경우 '성능은 저하되지만 에러가 발생하지 않는' 상황이기에, 장애의 발견을 늦추는 요인이 된다. 
  따라서 DNS 서버를 다중화해야 한다. 이 때 방식은 VRRP/LoadBalancing 등의 방식이 있다.
  
#### 2. Storage Server 다중화 (DRBD로 미러링 구성)
- 스토리지 서버의 장애 대책
  하드디스크 고장에 의한 데이터 손실은 복구하기 어렵다,.
  복구작업은 보통 백업한 데이터로 recovery하는 것이지만, 모든 파일을 복구하는데에는 많은 시간이 소요된다.
  따라서 RAID 구성이 필요하나, 이 경우에도 만약 RAID 컨트롤러가 고장난 경우엔 데이터 손실이 발생할 여지도 있다. 결국 스토리지 서버도 다중화해야 한다.

- 스토리지 서버의 동기화 문제
  두 대의 서버에 대해 파일 단위로 디스크를 동기화하거나 정합성을 체크하면 파일 개수가 많아질수록 디렉토리 검색이나 비교에 시간이 오래 걸린다. 
  또한 그런 경우 하드디스크에 과도한 부하가 걸리므로 서버 전체의 성능이 크게 저하된다.

- DRBD
![drbd](https://user-images.githubusercontent.com/29951288/55684655-9b73e980-5988-11e9-9165-3c213a56d5f6.png)
  네트워크를 이용한 RAID1라고 생각하면 된다.
  DRBD는 실제 사용하는 블럭 디바이스의 I/O 명령을 대신 받아서 처리한다. 실제 블럭 디바이스에 데이터를 쓰고 해당 데이터를 네트워크를 통해 mirroring 되는 장치로 보낸다.
  
#### 3. 네트워크(L1,L2)의 다중화(Bonding 드라이버, RSTP)

1) OSI 7 Layer 중 L1,L2

```
1계층 - Physical Layer
- 역할: Bit Stream(이진수 흐름)을 전기, 빛 등의 신호로 변환
- PDU: Bit
- 대표장비: 케이블(LAN-UTP, WAH-Serial), 허브, 리피터, 커넥터(LAN-RJ45) 등

2계층 - Data-Link Layer
- 역할: 물리적 주소를 이용해 노드(L2 스위치에 연결될 수 있는 3계층 이상의 장비) 간 연결
- PDU: Frame
- 대표장비: 브릿지, L2 스위치 등
- 프로토콜 및 기술: LAN - Ethernet Protocol, WAN - PPP, HDLC 등
```

2) Collision Domain & Broadcast Domain

- Collision Domain이란,
<img width="608" alt="스크린샷 2019-04-07 오후 11 06 49" src="https://user-images.githubusercontent.com/29951288/55684741-dc203280-5989-11e9-81f9-13d34cedf0c1.png">

```
  Half Duplex로 다중접속환경(Multiple Access)를 구성했을 경우, 데이터를 동시에 전송했을 때 충돌이 일어날 수 있는 영역을 의미한다.
  허브를 통해 MA를 만들었을 경우, 허브는 1계층 장비이기 때문에 신호 증폭, 즉 Collision Domain을 확장시킨다.
```
<img width="606" alt="스크린샷 2019-04-07 오후 11 08 54" src="https://user-images.githubusercontent.com/29951288/55684786-36b98e80-598a-11e9-9eb2-296506f23ed1.png">

```
 스위치를 통해 MA를 구성할 경우, 스위치는 2계층 장비이기 때문에 신호증폭도 시키지만 2계층 헤더를 열어 MAC주소를 확인해 전송해서 Port별로 Collision Domain을 나눈다.
```
- Broadcast Domain이란,
```
  Broadcast 데이터가 전달되는 범위로써 영역 내에 있는 단말은 직접 통신이 가능하며, 허용 영역은 라우터를 기준으로 분할된다.
  Broadcast해야 할 정보량이 많으면 통신망에 과대한 부하를 주기 때문에 Broadcast 허용영역이 적당한 크기로 분할되도록 라우터를 배치하여 통신망의 부하를 줄여야 한다.
```

3) Switch

(1) Switch vs Router
```
* Switch

- Multiple Access 환경을 구성하기 위한 장비
- MAC Table에 정보가 있을 때 : Forwarding
- MAC Table에 정보가 없을 때: Flooding
- Fail open 형 장비
- Broadcast 데이터를 Flooding하기 때문에 Broadcast domain을 확장시킨다.

*  Router

- 통신을 가능하게 하기 위한 장비
- MAC Table에 정보가 있을 때 : Forwarding
- MAC Table에 정보가 없을 때 : Drop
- Fail drop형 장비
- Broadcast 데이터를 drop하기 때문에 Broadcast domain을 나눈다.
```

(2) 동작방식

```
- 기본 동작 원리
   스위치는 프레임 헤더의 Destination MAC 주소를 기반으로 Frame을 지정된 포트로 전송하는 장비
   Transparent Bridging이라는, Frame을 수신하여 목적지로 전송하는 방식과 절차를 정의해놓은 표준에 따라 동작한다.

- Transparent Bridge 동작 방식
   a. 인터페이스를 통해 들어오는 Frame 수신
   b. Frame의 Source MAC 주소 확인
        - Learning: MAC 테이블에 해당 주소가 없으면 수신한 포트와 출발지 주소를 MAC 테이블에 저장
        - Aging: 스위치는 MAC 테이블에 정보를 300초동안 저장하며, 갱신될 시 Aging Timer를 초기화
    c. Frame의 Destination MAC 주소 확인
        - Flooding: 목적지 주소가 Bloadcast나 Multicast, MAC 테이블에 없는 주소인 경우, 수신된 포트를 제외한 모든 포트에 Frame을 전송
        - Forwarding: MAC 테이블에 목적지 주소가 있는 경우 해당 포트에 Frame을 전송
        - Filtering: MAC 테이블에 목적지 주소가 Frame을 수신한 포트와 동일한 경우 해당 Frame을 차단,
                          MAC 테이블에 목적지 주소정보가 있는 경우 해당 포트를 제외한 포트에 Frame을 전달하지 않음
```

example ㄱ)

<img width="374" alt="스크린샷 2019-04-07 오후 11 24 58" src="https://user-images.githubusercontent.com/29951288/55684993-61a4e200-598c-11e9-93c5-7070d603c792.png">

```
초기에는 MAC 테이블이 비어있다.
A가 D에게 Frame을 전달할 때 스위치는 MAC 테이블이 비어있기 때문에 Frame을 전달받은 포트를 제외하고 모든 포트로 Flooding을 한다.
Frame을 Flooding하는 동안 A의 MAC 주소를 학습한다.
```

example ㄴ)

<img width="367" alt="스크린샷 2019-04-07 오후 11 27 00" src="https://user-images.githubusercontent.com/29951288/55685020-a9c40480-598c-11e9-83cb-ad36444e7dbd.png">

```
A가 C에게 Frame을 전달할 때 스위치는 MAC 테이블에서 목적지 주소에 대한 정보를 찾아 해당하는 포트인 E2로 Frame을 전달한다.
이 때, E2에 대한 Aging Time이 초기화되고, MAC 테이블에 목적지 경로가 있는 경우에는 포트로만 Frame을 전달(Forwarding)하고 그 외의 포트로는 Frame이 전달되지 않는다.(Filtering)
```

example ㄷ)

<img width="369" alt="스크린샷 2019-04-07 오후 11 28 48" src="https://user-images.githubusercontent.com/29951288/55685039-f1e32700-598c-11e9-934e-4fa777fbc2c1.png">

```
D가 Broadcast 또는 Multicast를 보낼 때 Frame을 보낸 쪽의 포트 즉, E3만 제외하고 모든 포트로 Frame을 Flooding 한다.
```

example ㄹ)

<img width="377" alt="스크린샷 2019-04-07 오후 11 29 53" src="https://user-images.githubusercontent.com/29951288/55685054-150dd680-598d-11e9-9bb2-c332ef25ce40.png">

```
스위치는 MAC 테이블이 비어있기 때문에 Frame이 들어온 포트를 제외하고 모든 포트로 Flooding해준다.
하지만 B는 허브를 통해서 A가 보낸 Frame을 받았기 때문에 응답을 하게 되고 허브는 해당 Frame을 Flooding한다.
허브가 보낸 Frame을 받은 스위치는 A와 B가 같은 포트에 있는 것을 학습하고 이후부터는 A와 B의 통신시에 받은 Frame은 다른 포트로 Flooding하지 않고 Filtering한다.
```

4. 이중화

1) 장애발생 포인트
  LAN 케이블: 단선이나 커넥터의 접속 불량
  NIC: 링크업/다운 반복
  네트워크 스위치의 포트
  네트워크 스위치

2) 개요
```
이중화란, 네트워크의 안전성을 위해 장비 이중화 또는 링크 이중화를 통해서 장비나 링크의 장애로 인한 네트워크의 기능 상실을 방지할 수 있음 

- 장비이중화: HSRP, VRRP, GLBP 등
- 링크이중화: STP, EtherChannel 등

- 이중화: 백업경로를 만드는 목적 (주 경로에 장애 발생시 백업 경로를 사용)
- 로드밸런싱: 부하분산 목적 (다중경로를 동시에 사용, 케이블의 성능에 따른 부하분산)
```

3) 링크의 다중화와 Bonding (Cisco에서는 Etherchannel)
- 여러 NIC를 모아서 하나의 논리적인 NIC로 다룰 수 있도록 하는 것을 말한다.
   장치를 바꾸지 않고도 링크의 대역폭을 증가시킬 수 있다는 이점이 있다.
- Bonding에는 MII감시(링크다운여부)와 ARP 감시가 있다. 

4) 이중화의 문제점

(1) BroadcastStorm
<img width="573" alt="스크린샷 2019-04-07 오후 11 44 50" src="https://user-images.githubusercontent.com/29951288/55685280-27890f80-598f-11e9-80da-327d7a576bbe.png">

```
- 장비들간에 발생하는 Broadcast 트래픽들이 지속적으로 해당 링크를 사용하여 네트워크 기능을 악화시키는 현상
  스위치는 수신한 포트를 제외한 나머지 포트로 Broadcast Frame을 Flooding한다.
  1) Host X가 Broadcast Frame을 보내고 Switch A가 Frame을 수신한다.
  2) Switch A는 Frame에 있는 목적지 주소를 검사하고 이 Frame을 flooding한다.
  3) 이 Frame을 Switch B가 수신하고 2단계 과정이 반복되어 반시계 방향으로 루프가 발생한다.
```

(2) Multiple Frame Copy

<img width="504" alt="스크린샷 2019-04-07 오후 11 48 47" src="https://user-images.githubusercontent.com/29951288/55685320-b564fa80-598f-11e9-837d-6220463817cb.png">

```
  1) Host X가 Router Y에게 Unicast Frame을 보내고 Router Y는 원본 Frame을 수신한다.
  2) Switch A도 Frame을 수신하고, Unknown Frame으로 인식하여 이 Frame을 Flooding한다.
  3) 이 Frame을 Switch B가 수신하고 2단계 과정이 반복되어 Flooding 한다.
  4) SwitchA는 위의 과정을 반복하며 이 과정에서 Router Y는 같은 Frame을 계속 수신하게 된다.
```

(3) MAC Database Instability

<img width="501" alt="스크린샷 2019-04-07 오후 11 54 57" src="https://user-images.githubusercontent.com/29951288/55685393-91ee7f80-5990-11e9-8d09-1d1615efe2ad.png">

```
- 서로 다른 포트로 출발지가 동일한 Frame을 수신하게 되어 MAC 주소 테이블이 불안정하게 유지된다.

- 스위치는 일반적으로 Frame을 수신하면 출발지 MAC 주소와 수신한 포트의 번호를 MAC 주소 테이블에 학습하므로, Multiple Frame Copy와 같이 Unicast Frame의 루프가 발생한 경우 MAC Database Instability 문제가 수반된다.
  1) Host X가 보낸 Unicast Frame을 Switch A와 Switch B는 Port 0으로 수신
  2) 각각의 스위치는 이 Frame을 Flooding하고 서로 Flooding한 Frame을 각각 다시 수신
      위의 과정이 반복되면 Flapping이 발생함
```

5) STP
<img width="486" alt="스크린샷 2019-04-08 오전 12 13 05" src="https://user-images.githubusercontent.com/29951288/55685602-1e01a680-5993-11e9-8d2d-3cff9e7422d5.png">

(1) 개요
```
- 스위치로만 연결된 네트워크에서 최적경로를 찾는 역할을 담당한다.
- 스위치가 토폴로지 내의 루프를 인식하고 하나의 Link를 차단하여 Loop를 제거하고 포트를 계속 모니터링하다가 다른 포트에 장애나 토폴로지 변경이 발생하는 경우 포트를 재설정하여 연결이 완전 손실이나 새로운 루프를 방지하도록 한다.
```

(2) RSTP 

```
RSTP는 링크 장애시 빠르게 포트변경이 이루어 진다.
이전에 802.1d는 BPDU Time이 완료되기를 기다렸다가 포트를 변경하지만 802.1w는 포트 장애나 토폴로지 변경 시 즉시 그 정보를 인접장비에 전달하여 포트선출을 하여 변경 즉시 토톨로지에 적용된다
```

6. VLAN

(1) 개요

```
VLAN을 이용하여 물리적인 네트워크를 논리적인 네트워크로 나눌 수 있지만 서로 다른 VLAN에 있는 PC끼리 통신을 하기 위해서는 3계층 이상의 장비가 필요하다.
```

(2) 사용목적

```
- VLAN을 구성하지 않을 경우 WAN 구간 스위치의 포트 낭비가 발생할 수 있다.
- Bloadcast Domain 분할: 같은 VLAN 번호를 쓰는 포트로만 프레임이 전송되므로 Broadcast로 인한 트래픽을 줄일 수 있다.
- 보안성 강화: VLAN을 사용하여 구분할 경우 Router를 통해야만 통신이 이루어지기 때문에 Router에 다양한 보안 정책을 적용하여 보안성을 강화할 수 있다.
- Load Balancing 기능: 이중화된 구간의 경로별로 VLAN을 설정하면 Path Cost, Port ID 등으 이용하여 부하분산이 가능하다.
```

(3) 구성

```
- Access Port: 하나의 포트가 하나의 VLAN에 속해 해당 포트는 자신이 속한 VLAN 네트워크 Frame만 전송 가능하다.
- Trunk Port: 하나의 포트로 여러 개의 VLAN Frame이 지나다닐 수 있는 포트로, 다수의 같은 VLAN이 여러 개의 스위치에 존재할 경우 Trunk Port를 통해 각 스위치에 연결된 같은 VLAN에 속한 장비끼리 서로 통신이 가능하다.
```