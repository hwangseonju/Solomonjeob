# OpenVidu Tutorial

### OpenVidu 공식 문서

[https://docs.openvidu.io/en/stable/tutorials/](https://docs.openvidu.io/en/stable/tutorials/)

### 1. Openvidu + Docker 설치하기

- 서버가 1개이므로 Docker를 사용한다.
1. Docker 설치 후 실행하기 (docker가 실행되어 있어야 docker에 설치할 수 있다)
2. 시작 > Microsoft store 접속해 ubuntu 설치 > 한번 실행
    - id, pw 입력(계정 생성) (Unix user name)
    - 생성된 계정으로 로그인 후 (sudo su)
        
        ```bash
        sudo su
        #test. test
        ```
        
    - 디렉토리 변경
        
        ```bash
        cd /opt
        ```
        
    - openvidu 다운로드
        
        ```bash
        curl https://s3-eu-west-1.amazonaws.com/aws.openvidu.io/install_openvidu_latest.sh | bash
        ```
        
3. 도커에 컨테이너를 만들어짐 및 실행 확인
4. openvidu tutorial git clone
    
    ```bash
    git clone https://github.com/OpenVidu/openvidu-tutorials.git -b v2.19.0
    ```
    
5. vscode로 clone받은 파일 열기
6. vscode에서 npm install , npm run으로 실행 확인 하기

### * Error : Docker kernel 오류

- docker 실행 불가 → linux kernel update error 발생
- [이전 버전 WSL의 수동 설치 단계 | Microsoft Docs](https://docs.microsoft.com/ko-kr/windows/wsl/install-manual#step-4---download-the-linux-kernel-update-package)
- 해결 방법 해당 파일 설치
    - [https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi](https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi)