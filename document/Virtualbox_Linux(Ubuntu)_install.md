# Virtualbox + Linux(+Ubuntu)

### 1️⃣ Virtualbox?

- Oracle에서 개발 중인 가상화 소프트웨어로 여러가지 OS(리눅스, MacOS, 솔라리스, Window 등)를 가상화하여 운영 가능

### 2️⃣ 설치 방법

### [ VirtualBox  설치 ]

1. VirtualBox 설치
   
     - [www.virtualbox.org/](https://www.virtualbox.org/wiki/Downloads) 사이트에서 현재 PC에 맞게 설치해준다.
    
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled.png)
    
2. Next를 누르고 설치한다.
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%201.png)
    
1. 필요한 옵션들을 선택하여 계속해서 설치를 진행한다.
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%202.png)
    
1. 설치 완료
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%203.png)
    

### [ 가상머신 영역 생성 ]

1. Ubuntu 사이트에서 다운받는다. 시간이 꽤 소요될 수 있다. (30분 정도 소요)
    - [https://ubuntu.com/#download](https://ubuntu.com/#download)
      
        ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%204.png)
    
2. Ubuntu.iso가 설치되는 동안 virtualbox에서 설정을 해준다.
    - Virtualbox에서 새로 만들기로 들어간다.
    - 이름은 아무거나 지정해주면 되고, 종류는 Linux, 버전은 본인 컴퓨터에 맞는 비트수로 설정해준다.
      
        ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%205.png)
        

1. 메모리 크기는 나중에 변경이 가능하기 때문에 초기 셋팅으로 설정하면 된다
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%206.png)
    
2. 지금 새 가상 하드 디스크 만들기를 선택하고 만들기를 진행한다.
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%207.png)
    
3. 동적 할당(D)를 선택한다.
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%208.png)
    
4. VDI(VirtualBox 디스크 이미지)를 선택한다.
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%209.png)
    
5. 용량은 앞으로 사용하게될 저장소의 최대 크기이기 때문에 본인의 컴퓨터 용량과 무관하게 60GB 이상으로 설정하는 것이 좋다.
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%2010.png)
    
6. 가상머신 영역 생성 완료
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%2011.png)
    

---

### 1️⃣ Ubuntu?

- 다양한 Linux 배포판 중 하나이다. → Linux 커널을 기반으로한 OS
- Ubuntu Desktop은 개인용 컴퓨터에 적합하고, Ubuntu Server은 클라우드 및 서버에 적합하다.

### 2️⃣ Linux?

- Linux = 커널
- 커널은 OS의 핵심이다.
- 장치 메모리를 관리하고 프로세스를 관리한다.
- Linux 커널을 기반으로 하는 OS 시스템을 Linux 배포판이라고 한다.
- 유닉스 기반의 무료 오픈 소스 OS이다.

### 3️⃣ Linux 장점

- 안정적이고 안전한 OS이다.
- 여러 사용자가 동시에 작업이 가능하다. → 다중 사용자 시스템
- 동시에 더 많은 프로세서를 지원하고 사용하는 OS이다.
- 멀티 태스킹이 많은 프로세스를 동시에 실행 가능

### 4️⃣ OS?

- 컴퓨터 시스템의 각종 하드웨어적인 자원과 소프트웨어적인 자원을 효율적으로 운영 관리함으로써 사용자가 시스템을 이용하는데 편리함을 제공한다.
- 많은 애플리케이션들이 잘 동작할 수 있도록 자원을 할당하고 관리한다.
- 외부의 접근을 방어해주는 역할을 한다.

### 5️⃣ 커널?

- 컴퓨터의 OS의 핵심이 되는 컴퓨터 프로그램의 하나이다.
- 시스템의 모든 것을 완전히 통제한다.
- 보안, 자원 관리, 추상화 같은 역할을 한다.

### 6️⃣ 설치 방법

### [ Ubuntu 이미지 VirtualBox에 삽입 ]

1. 생성한 가상 머신 영역을 선택하고 설정을 한다.
    - 저장소에서 컨트롤러: IDE 하단에 비어있음을 선택한다.
    - 오른쪽 속성에서 CD모양을 클릭하여 가상 광학 디스크 선택/만들기를 선택한다.
      
        ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%2012.png)
        

1. 설치가 완료된 Ubuntu.iso파일을 추가하여 선택한다.
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%2013.png)
    
1. 설정이 완료되면 실행한다.
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%2014.png)
    

### [ Ubuntu 설치 ]

1. 지원하는 언어를 선택하고, Ubuntu를 설치한다.
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%2015.png)
    
1. 한국어 - 한국어(101/104키 호환)을 선택한다.
    - 혹시 하단에 버튼이 보이지 않는다면, 우선 실행중인 화면을 종료한다.
    - 가상머신 영역에서 오른쪽 마우스를 클린한 후 설정에 들어간다.
    - 디스플레이에서 그래픽 컨트롤러를 VBoxVGA로 설정해준 다음 다시 실행하면 된다.
    
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%2016.png)
    
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%2017.png)
    
1. 업데이트와 관련된 설정을 한 후에 계속하기를 누른다.
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%2018.png)
    
1. 설치 형식을 설정하고 설치를 진행한다.
    - 여기서 디스크는 가상 머신 영역이기 때문에 본인의 컴퓨터 하드가 지워지는 것이 아니다.
      
        ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%2019.png)
        

1. 이름과 암호를 설정한 후에 계속하기를 선택한다.
   
    ![Untitled](./photo/Virtualbox_Linux(Ubuntu)_install/Untitled%2020.png)
    
    <br>

📚 **References**

- [https://hanamon.kr/리눅스는-무엇이고-우분투는-무엇인가/](https://hanamon.kr/%EB%A6%AC%EB%88%85%EC%8A%A4%EB%8A%94-%EB%AC%B4%EC%97%87%EC%9D%B4%EA%B3%A0-%EC%9A%B0%EB%B6%84%ED%88%AC%EB%8A%94-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80/)
- [https://dasima.xyz/ubuntu-installation-on-virtualbox/](https://dasima.xyz/ubuntu-installation-on-virtualbox/)