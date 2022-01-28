# MobaXterm으로 가상 서버에 접속하기
> 사전 준비물
> 
- mobaxterm
    - 설치 : [https://mobaxterm.mobatek.net/](https://mobaxterm.mobatek.net/download.html)
    - portable : 압축 파일로 받아서 설치
    - installer 버전 : 바로 실행 파일로 설치 가능 이걸로설치!!!!!!!!!!!!!!!!!!!!!!!!!
- ubuntu가 설치되어 있는 virtualBox server

<aside>
💡 mobaxterm을 왜 사용하는건가?

</aside>

- virtual box로 가상서버를 만들경우 내부GUI로 서버를 볼 수 있지만 virtualBox의 서버가 아닌 다른 아마존 EC2같은 서버에 접속하려면 원격으로 접속을 해야 함
- 그럴 때 mobaxterm, putty같은 원격접속 툴을 사용한다!

# 1. 네트워크 설정

1. 현재 내 사용중인 컴퓨터의 아이피 확인
    - cmd > ipconfig > 이더넷 어댑터 : VirtualBox > IPv4 확인
2. VirtualBox 의 아이피 확인
    - 처음 접속 시 터미널 아이콘이 없음
    - 왼쪽 맨 하단의 점으로 이루어진 네모 아이콘을 클릭 > terminal 검색
    - hostname -I 입력 > 10.x.x.x 으로 구성된 아이피 확인
3. 네트워크 설정하기
    - Virtual Box 접속  > 새로생긴 서버 오른쪽 클릭 설정 > 왼쪽 네트워크 탭 > 고급 > 포트포워딩 > 맨 오른쪽 초록색 플러스 버튼으로 네트워크 추가 >기본상태에서 포트포워딩 버튼 클릭 > 새로 나타난 창에서 맨오른쪽 초록색 플러스 버튼 클릭
        
        호스트 IP : 1번에서 확인했던 아이피입력
        
        호스트 포트 : 22
        
        게스트 IP : 2번에서 확인했던 아이피 입력
        
        게스트 포트 : 22
        

# 2. MobaXterm 설정

1. 상단 Session 탭 클릭 > ssh 탭 클릭 > root host : 1번에서 호스트 ip에 입력했던 ip입력 port는 22  설정 > ok > 왼쪽에 ip이름으로 원격 connection 생김 더블클릭 > 
    1. Connection refure 또는 ~~~abort 뜨면 3번(우분투 ssh설정)으로 넘어가기 
    2. login as : 가 뜬다면 우분투 설치 시 입력했던 사용자 계정 입력 후 로그인하면 연결됨

# 3. 우분투 ssh 설정 ( 2번까지 해보고 안되면 할 것)

<aside>
💡 사용할 서버 아마존 EC2는 이렇게 설정하면 안됨... 로컬에서 virtualBox에서 설정할 때 쓰세요..

</aside>

1. ssh 확인 및 openssh 서버 설치
- linux에서 sudo service ssh status 입력→ 없다고 뜨면
- sudo apt-get install openssh-server 명령어로 설치
- sudo service ssh status 명령어로 설치 확인 가능함
- ufw allow 22  명령어로 22번 방화벽 해제
1. 저렇게 만해도 접속은 되지만 루트 계정 사용 불가
- sudo nano /etc/ssh/sshd_config 입력
    - sudo는 root 권한
    - nano는 에디터 종류 (vi가 편하면 sudo vi /etc/ssh/sshd_config 입력으로 편집 가능)
- # PermitRootLogin~~로 되어있는  부분에 엔터치고 PermitRootLogin yes 입력
- !!!!!!!!!!!!다른 글자들 건들면 안됨!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
- 바로 저장 후 에디터 상태 빠져나오기
- service ssh restart