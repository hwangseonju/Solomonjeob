# Openvidu Sever Setting

**WebRTC : Openvidu Server Setting**

- **Installation Environment**

  → AWS EC2 Linux 환경, Docker,  Docker Compose

## **Docker install**

---

```
# 도커 설치 방법

$ sudo apt-get update

$ sudo apt-get install \
	apt-transport-https \
	ca-certificates \
	curl \
	gnupg \
	lsb-release
	
$ sudo -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o
/usr/share/keyrings/docker-archive-keyring.gpg

$ echo \
	"deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg]
	https://download.docker.com/linux/ubuntu \
	$(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
	
$ sudo apt-get update

$ sudo apt-get install docker-ce docker-ce-cli containerd.io

$ sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

$ sudo chmod +x /usr/local/bin/docker-compose
```

## **Openvidu Install**

---

1. Openvidu Port 확보
   - `22 TCP`, `80 TCP` , `443 TCP`, `3478 TCP+UDP`, `40000~57000 TCP+UDP`, `57001~65535 TCP+UDP`

1. Openvidu Install

```
$ cd /opt   # openvidu는 /opt 디렉토리에 설치 권장

$ sudo curl https://s3-eu-west-1.amazonaws.com/aws.openvidu.io/install_openvidu_latest.sh | sudo bash
```

1. 설정 파일 수정 ( /opt/openvidu/.env)

```
$ sudo vi .env
```

```
DOMAIN_OR_PUBLIC_IP=<Linux 서버의 public ip 주소 또는 도메인>
OPENVIDU_SECRET=<사용할 비밀번호 입력>
CERTIFICATE_TYPE=letsencrypt # default 값은 selfsigned지만 selfsigned 방식 사용시 보안 문제 di야기
							 # SSL 키가 있다면 owncert 방식으로 하되, /owncert 디렉토리 안에 키가 있어야 한다.
LETSENCRYPT_EMAIL=<이메일>
HTTP_PORT=80
HTTPS_PORT=443

# HTTP_PORT와 HTTPS_PORT는 letsencrypt 방식의 키를 발급 받기 전까진 기본 포트인 80, 443을 사용해야 합니다!
# 키를 발급받고 난 후부터는 포트 변경해도 무방합니다!
```

1. Openvidu Server 실행

```
$ sudo ./openvidu start
```

1. Openvidu Server 동작 확인

 Docker Container에 아래와 같이 올라와 있는지 확인

 `openvidu-coturn`, `kurento-media-server`, `openvidu-call`, `openvidu-proxy`, 

 `openvidu-redis`, `openvidu-server` 

```
$ sudo docker ps
```

```
CONTAINER ID   IMAGE                                 COMMAND                  CREATED        STATUS                  PORTS     NAMES
1c2af864a37c   openvidu/openvidu-coturn:5.0.0        "docker-entrypoint.s…"   20 hours ago   Up 20 hours                       openvidu_coturn_1
575695716b60   kurento/kurento-media-server:6.16.0   "/entrypoint.sh"         20 hours ago   Up 20 hours (healthy)             openvidu_kms_1
9abf26e074bd   openvidu/openvidu-call:2.20.0         "docker-entrypoint.s…"   20 hours ago   Up 20 hours                       openvidu_app_1
f222d92e2f00   openvidu/openvidu-proxy:7.0.0         "/docker-entrypoint.…"   20 hours ago   Up 20 hours                       openvidu_nginx_1
09474b6ed7ec   openvidu/openvidu-redis:3.0.0         "docker-entrypoint.s…"   20 hours ago   Up 20 hours                       openvidu_redis_1
8b27e879a593   openvidu/openvidu-server:2.20.0       "/usr/local/bin/entr…"   20 hours ago   Up 20 hours                       openvidu_openvidu-server_1
```

1. openvidu와 관련한 nginx 파일 설정 

   - /etc/nginx/sites-available/test.conf

     ```
     server {
     
             server_name <도메인>;
     
             # spring-boot 서버 설정
             location / {
                 proxy_pass http://localhost:8080;
             }
     
             # 여기에 경로를 다른것으로 설정해서 사용하는게 좋을것 같습니다.
             # ex) location /openvidu
             # 위 예제처럼 설정하게 되면 https://<도메인>/openvidu로 접속하면
             # 도커의 5443 포트로 이동하게 됨. (현재 openvidu 포트)
             location /openvidu {
                 proxy_pass http://127.0.0.1:5443;
     		        proxy_set_header Upgrade $http_upgrade;
     		        proxy_set_header Connection 'upgrade';
     		        proxy_set_header Host $host;
     		        proxy_cache_bypass $http_upgrade;
     		   }
     
             location /dashboard {
                 proxy_pass http://127.0.0.1:5443/dashboard;
     				}
     
             # 에러 페이지 location 수정 필요.
             error_page 404 /404.html;
                 location = /index.html {
             }
             error_page 500 502 503 504 /50x.html;
                 location = /index2.html {
             }
     
         listen [::]:443 ssl ipv6only=on; # managed by Certbot
         listen 443 ssl; # managed by Certbot
         ssl_certificate /etc/letsencrypt/live/<도메인>/fullchain.pem; # managed by Certbot
         ssl_certificate_key /etc/letsencrypt/live/<도메인>/privkey.pem; # managed by Certbot
         include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
         ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot
     
     }
     server {
         if ($host = <도메인>) {
             return 301 https://$host$request_uri;
         } # managed by Certbot
     
             listen 80 default_server;
             listen [::]:80 default_server;
     
             server_name <도메인>;
         return 404; # managed by Certbot
     }
     ```

   - nginx 수정 시 restart

   ```
   sudo service nginx restart 
   ```

   - • https://<DOMAIN_OR_PUBLIC_IP>/dashboard 정상 동작 확인

     User : OPENVIDUAPP

     Password : <.env에 설정한 OPENVIDU_SECRET 값>

     ![Untitled](./photo/openvidu/Untitled.png)

- openvidu server 설정 관련 사이트

(공식 사이트) [https://docs.openvidu.io/en/stable/deployment/ce/on-premises/](https://docs.openvidu.io/en/stable/deployment/ce/on-premises/)

[https://github.com/483759/Square4Us/wiki/Openvidu-구축-과정](https://github.com/483759/Square4Us/wiki/Openvidu-%EA%B5%AC%EC%B6%95-%EA%B3%BC%EC%A0%95)

[https://whatisthenext.tistory.com/123](https://whatisthenext.tistory.com/123)

- 이후 코드를 올려보기 위하여 Openvidu-tutorial을 진행하면 된다.

이는 공식사이트가 더 정확하게 나와있다.

[https://docs.openvidu.io/en/stable/tutorials/openvidu-insecure-vue/](https://docs.openvidu.io/en/stable/tutorials/openvidu-insecure-vue/)