# VirtualBox에 Ubuntu 설치해서 가상서버 만들기

<aside>
💡 아마존 EC2 에 해보기전 Kurento , 서버 환경 설정 연습하기 위해 설치하는 것!!!

</aside>

# 1. Virutal-Box 설치

> 왜 설치 하는가?
> 

현재 사용하는 컴퓨터를 클라이언트로 놓고 서버가 필요할 때 가상으로 Virtual Box로 서버를 만들어 테스트

- [https://www.virtualbox.org/wiki/Downloads](https://www.virtualbox.org/wiki/Downloads)
- 기본 경로 : C:\Program Files\Oracle\VirtualBox\

# 2. Ubuntu

> 설치 하는 이유
> 

서버에서 필요한 OS를 무료인 Ubuntu OS를 다운로드 받아 설치

- [https://ubuntu.com/download/desktop](https://ubuntu.com/download/desktop)

# 3. Virtual-Box에 우분투 setting하기

1. Virtual Box에서 새로 만들기 클릭 
2. 가상 머신 만들기
    - 종류 : Linux
        - 버전 : ubuntu-64bit
3. 메모리설정
    - 2048로 설정하였음
4. 하드디스크 설정
    - 지금 새 가상 하드디스크 만들기
    - 파일 종류 VDI (디스크 이미지)
    - 동적할당
    - 가상 하드디스크 만들기 : D 드라이브 있으면, D에 설치
        - 경로 : D:\VirtualBox\서버이름.vdl
        - VirtualBox 폴더는 그냥 만들어서 경로로 해준 것..
        - Drive 크기 10.00gb → 만들기
5. 새로 생긴 서버 OS설정하기
    - 왼쪽에 새로생긴 서버 클릭 →  설정
    - 클릭 후 나온 창에서 저장소 클릭
    - 컨트롤러 > 비어있음 > 광학 드라이브 IDE 세컨더리 마스터 > 옆에 파란 CD클릭 >가상 광학 디스크 선택 > 상단 추가 버튼 클릭 > 다운 받았던 Ubuntu 선택 > 확인
6. 실행
    - 왼쪽 OS설정 완료한 서버 클릭 > 상단 실행 후 Ubuntu 설정
    - 왼쪽스크 롤바 한국어 > 우분투 설치 (그림 두개 중 오른쪽)
    - 한국어 모든거 한국어
    - 일반설치 ubuntu 설치중 다운로드
    - 디스크 지우고 우분투 설치 > 가상디스크라서 아무것동 ㅓㅄ음...
    - 본인 설정
    - 설치 > 설치 후 계정 연결 건너뛰어도 OK