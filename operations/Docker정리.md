Ningx SSL/TLS Docker Container

사전 준비:

```
$ mkdir /tmp/ssl
$ openssl req -x509 -nodes -days 1 -newkey rsa:4096 -subj "/C=DE/ST=Berlin/L=Berlin/O=Endocode AG/OU=IT/CN=localhost" -keyout /tmp/ssl/nginx.key -out /tmp/ssl/nginx.crt
```

Dockerfile 빌드:

```
$ docker build -t nginx-ssl:1.9.9 .
```

Ningx 도커 실행:

```
$ docker run -d -p 80:80 -p 443:443 -v /tmp/ssl/:/etc/nginx/ssl/ nginx-ssl:1.9.9
```
