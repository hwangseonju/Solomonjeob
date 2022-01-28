# 쿠렌토(Kurento)

 : 전체 WebRTC 스택의 기능적 구현을 제공하는 미디어 서버



- 쿠렌토를 사용할 수 있는 3가지 방법

  1. 웹소켓 브라우저로부터 직접적으로 Kurento JavaScript SDK를 사용하기(빠르게 테스트할때만 추천)
  2. Java EE 서버에서 Kurento Java SDK를 사용하기 (웹브라우저는 HTML, WebRTC 시그널링과 같은 작업을 위한 애플리케이션의 클라이언트)
  3. Node.js에 Kurento JavaScript SDK를 사용하기

  

- Media Server를 왜 사용하는가?

  - WebRTC는 P2P연결을 통해 웹 브라우저와 모바일 애플리케이션의 RTC기능을 제공하는 APIs이다.
  - 한명의 호스트와 여러명의 참가자들이 존재하는 1:N 서비스를 하기 위해서는 P2P 방식을 사용해서 개발하기엔 무리가 있다.
  - 이러한 이유로 Peer의 중간에 미디어 서버가 필요하다. 여기서 미디어 서버가 Kurrernto Media Server이다.

  

- Media Server는 어떤 기능을 제공하는가?

  - Group Communications : 한명의 스트리머가 방송을 시작하고, 그방에 접속해 있는 참여자들에게 미디어 스트림을 배포한다. (다중 회의 장치 MCU 역할 수행)
  - Mixing : 여러 수신 스트림을 하나의 복합 스트림으로 변환한다.
  - Transcoding : 호환되지 않는 클라이언트 간에 코덱 및 형식을 즉석에서 조정한다.
  - Recording: 미디어에 들어오는 스트림을 영구적인 방식으로 저장한다.



- KMS가 제공하는 기능

  - Transcoding, MCU, Recording과 더불어 Flexible Processing, Augmented reality, Blending, Mixing, Analyzing 등이 있다.

    

- TIL

  VirtualBox를 통해 Ubuntu를 설치하고, MobaXterm을 설치하였다. 