# Ningx SSL/TLS Docker Container

사전 준비:

```
$ mkdir /tmp/ssl
$ openssl req -x509 -nodes -days 1 -newkey rsa:4096 -subj "/C=KR/ST=Seoul/L=Seoul/O=Gangnam AG/OU=IT/CN=k3a301.p.ssafy.io" -keyout /tmp/ssl/nginx.key -out /tmp/ssl/nginx.crt
```

Dockerfile 빌드:

```
$ docker build -t nginx-ssl:1.9.9 .
```

Ningx 도커 실행:

```
$ docker run -d -p 80:80 -p 443:443 -v /etc/letsencrypt/archive/k3a301.p.ssafy.io/:/etc/nginx/ssl/ nginx-ssl:1.9.9
```

---

# Docker 안쓰는 볼륨 제거하기

볼륨 목록:

```
$ docker volume ls
```

컨테이너에 연결되지 않은 태그가없는 볼륨을 포함하여 도커 볼륨 이름의 목록:

```
$ docker volume ls -qf dangling=true
```

연결되지 않은 볼륨 제거:

```
$ docker volume rm $(docker volume ls -qf dangling=true)
```
