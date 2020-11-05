# Ningx SSL/TLS Docker Container

nginx-ssl container start

```
$ cd ./operations/docker/nginx-ssl
$ docker-compose up -d
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
