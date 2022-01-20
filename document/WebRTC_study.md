# WebRTC

- WebRTC (Web Real-Time Communication)

  - 웹 애플리케이션과 사이트가 중간자 없이 브라우저 간에 오디오나 영상 미디어를 포착하고 마음대로 스트림 할 뿐 아니라, 임의의 데이터도 교환할 수 있도록 하는 기술이다.
  - WebRTC는 구글이 주도학 오픈소스 프로젝트를 기반으로 하는 웹 표준이기 때문에, 특히 크롬에서 호환성이 높다.

- P2P 절차

  - 1. 각 브라우저가 P2P 커뮤니케이션 동의
    2. 서로의 주소를 공유
    3. 보안 사항 및 방화벽 우회
    4. 멀티미디어 데이터를 실시간으로 교환

    우리는 2번과 3번 단계가 일반적인 웹 개발의 접근 방법으로 해결하기 어렵다는 것을 확인할 수 있다. 브라우저는 웹 서버가 아니기 때문에, 외부에서 접근할 수 있는 주소가 없기 때문이다. 때문에 WebRTC가 P2P 기반이긴 하지만 통신 설정 초기 단계에서는 중재자의 역할이 필요하다.

- 방화벽과 NAT 트래버셜
  - 일반적인 컴퓨터에는 공인 IP가 할당되어 있지 않다. 방화벽(Firewall), 여러대의 컴퓨터가 하나의 공인 IP를 공유하는 NAT, 유휴 상태의 IP를 일시적으로 임대받는 DHCP 때문이다. 이 때문에 단순히 공인 IP만을 알아낸다고 해서, 특정한 사용자를 가릴 수 없다. 공인 IP뿐만 아니라 해당 네트워크에 연결된 사설 IP 주소까지 알아내야 특정한 사용자를 지정할 수 있게 된다.
  - 일반적으로 라우터가 NAT 역할을 한다. 외부에서 접근하는 공인 IP와 포트 번호를 확인하여 현재 네트워크 내의 사설 IP들을 적절히 매핑시켜준다. 어떤 브라우저 두 개가 서로 직접 통신을 하려면, 각자 현재 연결된 라우터의 공인 IP 주소와 포트를 알아야 한다.
  - 어떤 라우터들은 특정 주소나 포트와의 연결을 차단하는 방화벽 설정이 되어 있을 수도 있다. 이처럼 라우터를 통과해서 연결할 방법을 찾는 과정을 NAT 트래버셜(NAT traversal)이라 한다.

- STUN, TURN

  - NAT 트래버설 작업은 STUN(Session Traversal Utilities for NAT) 서버에 의해 이루어진다. STUN 방식은 단말이 자신의 공인 IP 주소와 포트를 확인하는 과정에 대한 프로토콜이다.

    WebRTC 연결을 시작하기 전에 STUN 서버를 향해 요청을 보내면, STUN 서버는 NAT 뒤에 있는 Peer들이 서로 연결할 수 있도록 공인 IP와 포트를 찾아준다.

  - STUN 서버를 통해 자기 자신의 주소를 찾아내지 못했을 경우, TURN(Traversal Using Relay NAT) 서버를 대안으로 이용하게 된다.

    TURN 방식은 네트워크 미디어를 중개하는 서버를 이용하는 것이다. TURN 방식은 중간에 서버를 한 번 거치기 때문에, 엄밀히 이야기하면 P2P 통신이 아니게 되며 그 구조상 지연이 필연적으로 발생하게 된다. 하지만 보안 정책이 엄격한 개인 NAT 내부에 위치한 브라우저와 P2P 통신을 할 수 잇는 유일한 방법이기 때문에, TURN 방식은 최후의 수단으로 선택되어야 한다.

- ICE와 Candidate

  - STUN, TURN 서버를 이용해서 획득했던 IP 주소와 프로토콜, 포트의 조합으로 구성된 연결 가능한 네트워크 주소들을 후보(candidate)라고 부른다. 이 과정을 후보 찾기(Finding Candidate)라 부른다.

    이렇게 후보들을 수집하면 일반적으로 3개의 주소를 얻게 된다.

    - 자신의 사설 IP와 포트 넘버
    - 자신의 공인 IP와 포트 넘버(STUN, TURN 서버로부터 획득 가능)
    - TURN 서버의 IP와 포트 넘버(TURN 서버로부터 획득 가능)

  -  이러한 모든 과정은 ICE(Interactive Connectivity Establishment)라는 프레임워크 위에서 이루어진다. ICE는 두 개의 단말이 P2P 연결을 가능하게 하도록 최적의 경로를 찾아주는 프레임워크이다.

  - 두 브라우저가 P2P 통신을 위해 통신할 수 있는 주소를 알아내었기 때문에 남은 것은 미디어와 관련된 정보를 교환하는 것이다.