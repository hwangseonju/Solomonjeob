# MobaXterm

### 1️⃣ MobaXterm?

### 2️⃣ 기본 용어

- Host(호스트) = 본인의 메인 PC
- Guest(Guest) = 모든 가상 서버
- 공인 IP = 외부 세계와 연결할 때 쓰는 통신사에 돈 내고 얻은 내 고유의 IP (1개)
- 사설 IP = 통신사에서 준 유무선공유기(AP)를 통해 집 내부 기기들에 할당되는 모든 IP (여러개)
    - A : 10.0.2.X~
    - B : 172.16.6.X~
    - C : 192.168.10.X~

### 3️⃣ 네트워크 방식 - NAT 방식(Network Address Translation)

- 구조
  
    ![Untitled](MobaXterm/Untitled.png)
    

### 2️⃣ 설치 방법

### [ MobaXterm 설치 ]

1. MobaXterm 설치한다.
    - [https://mobaxterm.mobatek.net/download-home-edition.html](https://mobaxterm.mobatek.net/download-home-edition.html)
    
    ![Untitled](MobaXterm/Untitled%201.png)
    
1. 압축을 풀고 실행파일을 실행한다.
    - 따로 설정해주는 부분이 없고, 계속 Next한 후 설치하면된다.
    
    ![Untitled](MobaXterm/Untitled%202.png)
    

### [ 네트워크 설정 ]

1. PC의 IP를 확인한다.
    - ipconfig
    - 이더넷 어댑터 VirtualBox Host-Only Network → IPv4 주소
    
    ![Untitled](MobaXterm/Untitled%203.png)
    
1. VirtualBox IP를 확인한다.
    - hostname -I
    
    ![Untitled](MobaXterm/Untitled%204.png)
    
2. 네트워크를 설정한다.
    -