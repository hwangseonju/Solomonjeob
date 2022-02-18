# Nginx TIL

## 1. Nginx

nginx는 정적 파일을 호스팅 해주는 웹 서버로 Event-Driven방식으로 이벤트를 처리해 적은 자원으로 빠른 서비스를 할 수 있도록 해준다.

## 2. Evnet-Driven

비동기 Event-Deriven 방식은 요청을 하나의 Event로 보고 Event Hansdler로 관리 해 메모리의 낭비가 적고 부하에 대한 예측 가능성 또한 제공할 수 있다.

## 3. 장점

정적 처리를 Event-Driven 방식으로 처리해주고 다른 Apache같은 서버에 동적 처리를 위임할 수 있다.

## 4. Nginx의 기능

(이번 PJT를 하며 들어본 용어를 위주로 정리)

1. 캐싱을 통한 리버스 프록시
    - Proxy server :  다른 네트워크 서비스에 간접 접속할 수 있게 해주는 시스템 또는 응용프로그램, server와 client 사이에 중계기로서 대리로 통신을 수행하는 것을 프록시, 기능을 하는 것을 proxy server라 한다.
    - reverse Proxy : 클라이언트 요청 → internet → reverseproxy ↔ server
        
        사용자가 요청시 reverse proxy먼저 요청하므로 사용자는 server의 IP를 알 수 없다.
        
    - forward Proxy : 클라이언트 → Proxy server → server : 서버에서 클라이언트로 응답받은 IP는 포워드 프록시 서버의 IP이기 때문에 Client를 알 수 없다.
2. 로드 밸런싱
    - 하드웨어 로드밸런싱
        - L2, L4, L7
    - 소프트웨어 로드밸런싱 : reverse Proxy 기반으로 동작
    - 트래픽이 몰릴 때 분산 처리를 위해 사용
    - nginx에서 제공하는 load balancing
        - 라운드로빈 : 처음부터 차레로 서버를 선택
        - Least-connected : 접속 수가 가장 적은 서버를 선택
        - ip-hash : hasing key를 사용해 IP별로 Index 생성
        - least_time : 연결 수와 응답시간이 가장 적은 쪽에 분배
    - site-enabled 에 설정을 추가
        
        ```bash
        upstream test{
        	ip-hash; //로드밸런싱 알고리즘
        	server localhost: port //클라이언트가 Nginx 요청하면 보내줄 서버정보	
        }
        server{
        	listen port //client가 요청하는 포트
        	proxy_set_header Host $host; //클라이언트 호스트 설정
        	proxy_set_header Connection ""; //upstream server를 사용하겠다
        	proxy_pass http://test; //설정한 이름으로 요청을 보냄 
        }
        ```
        
3. SSL 지원
    - ssl 인증서 : 클라이언트와 서버간 통신을 제3자가 보증해주는 전자화된 문서
    - 클라이언트 서버에 접속 → 서버가 클라이언트에게 인증서 정보를 전달 →클라이언트는 인증서 정보의 신뢰성을 검증 후 다음 절차를 수행