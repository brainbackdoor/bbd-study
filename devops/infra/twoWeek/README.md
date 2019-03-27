1. 왜 리버스프록시를 도입해야하는가?
	
- Reverse Proxy란,
  Reverse Proxy는 클라이언트로부터의 요청을 받아서(필요하다면 주위에서 처리한 후) 적절한 웹 서버로 요청을 전송한다. 웹 서버는 요청을 받아서 평소처럼 처리를 하지만, 응답은 클라이언트로 보내지 않고 Reverse Proxy로 반환한다. 요청을 받은 Reverse Proxy는 그 응답을 클라이언트로 반환한다. 
  통상의 프록시서버는 LAN -> WAN 의 요청을 대리로 수행하지만 Reverse Proxy는 WAN -> LAN 의 요청을 대리한다. 
  클라이언트로부터의 요청이 웹서버로 전달되는 도중의 처리에 끼어들어서 다양한 전후처리를 시행할 수 가 있게 된다.

- HTTP 요청 내용에 따른 시스템의 동작 제어
  mod_rewrite의 RewriteRule 기능
   - 클라이언트의 IP 주소를 보고 특정 IP 주소만 서버로의 접속을 허가한다.
   - 클라이언트의 User-Agent를 보고 특별한 웹서버로 접속되도록 유도한다.
     (봇의 경우 캐시서버로 전달)

- 시스템 전체의 메모리 사용효율 향상
  동적 컨텐츠를 반환하는 웹서버는 통상 애플리케이션이 이용하는 프로그램을 메모리에 상주시킴으로써, 애플리케이션 기동시의 오버헤드를 회피할 수 있게 설계되어 있다. 통상 WAS 서버는 클라이언트의 하나의 요청에 대해 하나의 프로세스 또는 하나의 쓰레드를 할당해서 처리하는 방식을 취하고 있다. 각각의 프로세는/쓰레드는 다른 프로세스/쓰레드와는 독립적으로 동작한다. 이로 인해 애플리케이션 개발자는 리소스 경합을 신경쓰지 않고 프로그램을 개발할 수 있으므로, 애플리케이션 설계가 쉽고 편해진다는 장점이 있다. 그러나 이 경우 이미지나 자바스크립트, CSS와 같은 정적 컨텐츠를 반환하는, 즉 파일에 쓰인 내용을 그대로 반환하기만 하면 될 경우도 동일한 방식으로 반환하게 된다. 
  Reverse Proxy를 사용하여 URL 기준으로 요청을 분기할 경우 이 문제를 해결하여 메모리 사용효율을 향상시킬 수 있다. 
	
- 웹 서버가 응답하는 데이터의 버퍼링의 역할
  HTTP의 Keep-Alive
  서버측이 Keep-Alive OK라는 지시를 HTTP헤더로 브라우저에 알리면, 브라우저는 서버와의 접속을 계속 유지해서 Keep-Alive 사양을 따라 하나의 접속으로 여러 파일을 다운로드한다.
  Keep-Alive는 한번 연결된 접속을 당분간 유지하는 특성상, 웹 서버에 다소 부하를 야기한다. 구체적으로는 특정 클라이언트로부터 요청을 받은 프로세스/쓰레드는 그 시점으로부터 일정 시간동안 해당 클라이언트로의 응답을 위해서 점유되는 것을 들 수 있다.
  Reverse Proxy의 경우 프로세스당 메모리 소비량이 그다지 많지 않으므로 하나의 호스트내에 1000~10000프로세스를 실행할수도 있다. 이 경우에는 일부 프로세스가 Keep-Alive연결을 유지하기 위해 소비된다고 해도 문제가 되지 않는다. 
  결국 클라이언트와 Reverse Proxy 사이에만 Keep-Alive on, Reverse Proxy와 백엔드 사이에는 Keep-Alive OFF로 설정한다.
  
- 아파치 모듈을 이용한 처리의 제어
  mod_deflate: 백엔드로부터 수신한 HTTP 응답을 클라이언트에 압축해서 보냄
  mod_ssl: 응답을 SSL로 암호화
  mod_dosdetector: 특정 클라이언트로부터 과도한 접속이 있을 경우 일시적으로 차단	
  mod_proxy_balancer로 여러 호스트로 분산하기
	
2. 왜 캐시서버를 도입해야하는가?
- 동작방식
	클라이언트로부터 송신된 If-Modified-Since의 갱신일시 수신
  로컬 문서의 날짜비교
  클라이언트가 저장한 문서는 갱신되지 않았다고 판단 (304 Not Modified)
- Squid 캐시서버
  Squid는 클라이언트로부터 HTTP 요청이 있으면 해당 문서를 백엔드 서버로 질의한다.
  서버로부터 수신한 문서는 Squid가 자신의 로컬 영역에 캐싱한다.
  다른 클라이언트로부터 요청이 있으면 Squid는 캐시의 유효성을 확인해서 캐시가 유효하면 클라이언트로 캐시를 반환한다. 
  Squid간에 캐시를 공유할 수도 있다. 
  Squid는 URL을 키로 문서를 캐싱하므로 페이지 전체를 캐싱할 수 있는 경우에만 유효하다.
- memcached에 의한 캐시
  데이터의 단위 크기로 캐시를 관리
  Disk IO를 줄이기 위함

- LB vs Reverse Proxy: https://www.nginx.com/resources/glossary/reverse-proxy-vs-load-balancer/
	LB: 부하분산, 서버상태체크, 세션관리
	Reverse Proxy: 보안성향상, 확장성향상, 웹가속(압축/SSL처리로 백엔드 리소스 확보/캐싱)
	
- route-by-name: http://mmlab.snu.ac.kr/publications/docs/caching_summary.pdf

- IP Anycast: https://www.google.co.kr/url?sa=t&rct=j&q=&esrc=s&source=web&cd=8&cad=rja&uact=8&ved=0ahUKEwi7gdqx1KHWAhXHoZQKHcT1CrIQFghRMAc&url=http%3A%2F%2Fwww.kisa.or.kr%2Fjsp%2Fcommon%2Fdown.jsp%3Ffolder%3Duploadfile%26filename%3DDNS%2520Technical%2520Issue%2520Report%282005%25EB%2585%25841%25EC%2582%25AC%25EB%25B6%2584%25EA%25B8%25B0%29.pdf&usg=AFQjCNFG83zo748D60T13YBN0qnHVIpwow

- cdn: https://cdn.hosting.kr/cdn%EC%9D%B4%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80%EC%9A%94/
	
- Apache vs Nginx: https://brainbackdoor.tistory.com/28