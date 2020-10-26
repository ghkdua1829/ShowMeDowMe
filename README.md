# [Sub PJT 3] 자율 프로젝트 프로젝트 

## :smiley: ​프로젝트 소개

### 기획 배경

1.  장보러 갔을 때 가격 파악의 어려움으로 인해 예산에 맞는 쇼핑을 하도록 도와주는 서비스를 기획하게 되었습니다.

2. 생필품과 같이 주기적으로 사야하는 물품에 대해 알림 기능으로 과소비를 막아주기 위한 기능을 구현하고자 하였습니다.

### 프로젝트 이름

`쇼우미도우미`는 쇼핑(장보기)할 때 도와주는 서비스를 제공하여 쇼핑의 도우미라는 의미를 갖고 있습니다.

![로고화면](https://user-images.githubusercontent.com/60081201/96952527-ff89de80-1529-11eb-9128-bc19eca5d01c.PNG)

### 주요 기능

쇼핑하는(장보는) 사람들의 편의를 위해 가격표 사진 분석 장바구니 담기, 가격 비교, 소비패턴 분석의 기능을 제공하는 모바일웹입니다.

![아이디어 소개](https://user-images.githubusercontent.com/60081201/96952896-d61d8280-152a-11eb-94cd-0a51776abb22.PNG)



### 와이어프레임

![와이어프레임](https://user-images.githubusercontent.com/60081201/96954086-a02dcd80-152d-11eb-8d29-86317b78823e.PNG)

#### 세부페이지 소개

##### 장보기 준비 화면 

- 목표 금액, 목표 시간 설정이 가능합니다.
- 구매 목록 메모 기능을 통해 장보기 전 구입할 목록을 작성할 수 있습니다.

![기능1](https://user-images.githubusercontent.com/60081201/96954306-17fbf800-152e-11eb-9000-fe63451bb4fb.PNG)

##### 가계부 화면

- 월별로 사용자의 소비패턴을 분석하여 구입한 물품 통계를 보여줍니다.

![기능2](https://user-images.githubusercontent.com/60081201/96954299-16cacb00-152e-11eb-8038-025ff68db8e1.PNG)

##### 마이페이지 화면

- 주기적으로 구입이 필요한 생필품의 구매 내역 분석을 통해 사용자 맞춤 알람을 제공합니다.
- 개인정보 수정이 가능한 페이지입니다.

![기능3](https://user-images.githubusercontent.com/60081201/96954305-17636180-152e-11eb-8676-53139f5e24da.PNG)



### ERD

![erd](https://user-images.githubusercontent.com/60081201/96953487-1e897000-152c-11eb-8f48-ec2b89d3f6fb.PNG)



### 기대효과

![기대효과](https://user-images.githubusercontent.com/60081201/96952891-d584ec00-152a-11eb-8845-3b22e2b8fc6d.PNG)



### :key: ​프로젝트 사용법

로컬 웹 서버 실행 방법은 다음과 같습니다.

#### Frontend

```bash
$ cd frontend/
$ npm install
# 서버를 실행합니다.
$ npm run serve
```

#### Backend

```bash
$ cd backend/
$ java -jar showm2dowm2-spring-0.0.1-SNAPSHOT.jar
```



### 💻 기술스택

<img src="https://img.shields.io/badge/Frontend-Vue.js, css/html/JavaScript, vuetify, scss-green" alt="기술스택" style="zoom:120%;" /><img src="https://img.shields.io/badge/database-MySQL, mariaDB-yellowgreen" alt="기술스택" style="zoom:120%;" /><img src="https://img.shields.io/badge/backend-Springboot, swagger-ff69b4" alt="기술스택" style="zoom:120%;" /><img src="https://img.shields.io/badge/server-AWS-9cf" alt="기술스택" style="zoom:120%;" /><img src="https://img.shields.io/badge/language-Java, JavaScript, Python-important" alt="기술스택" style="zoom:120%;" /><img src="https://img.shields.io/badge/OS-Ubuntu 18.04-blueviolet" alt="기술스택" style="zoom:120%;" /> <img src="https://img.shields.io/badge/WAS-Tomcat-lightgray" alt="기술스택" style="zoom:120%;" /><img src="https://img.shields.io/badge/server-docker-9c" alt="기술스택" style="zoom:120%;" />



### 주요 기술

- PWA

   PWA를 사용하여 브라우저에서 카메라 접근합니다.

- Vision API

   google vision Api 를 사용하여 텍스트 감지 구현합니다.

- OpenCV

  OpenCV을 이용한 가격표 인식 알고리즘 제작합니다.

  ![기술](https://user-images.githubusercontent.com/60081201/96953374-e124e280-152b-11eb-9328-2b9c2f66457f.PNG)

## 기타

### :pushpin: ​Git convention

#### Git commit Ground Rule

```bash
$ git commit -m "이슈번호 | 상태 | 작업 설명(optional)"
```

1. 총 70자를 넘지 않는다.
2. 마침표는 사용하지 않는다. 
3. 작업 설명 - 명령문 형식으로 작성 + 현재 시제로 작성

##### 커밋메시지 타입

| git status | 의미                  |
| :--------: | :-------------------- |
|   added    | 추가되었다.           |
|  updated   | 발전되었다.           |
|  deleted   | 삭제되었다.           |
|  replaced  | 대체되었다.           |
|  feature   | 기능 작업 시 사용     |
|    doc     | 문서 작업 시 사용     |
|   style    | 스타일 작업 시 사용   |
|    cicd    | 배포 작업 시 사용     |
|  refactor  | 코드 리팩토링 시 사용 |

#### branch 명명 규칙

|       Branch Name        | 목적                                  |
| :----------------------: | :------------------------------------ |
|          master          | 배포                                  |
|         develop          | Frontend, Backend 각 브랜치 기능 결합 |
| [front/back]/[기능 이름] | 각 기능 개발 브랜치                   |
|         release          | 배포하기 전의 브랜치                  |
|           fix            | 배포버전의 버그 발견시 버그킬 용도    |



### 👨‍👩‍👦‍👦  팀원소개 

#### 👦 김원정(팀장/Frontend) : Front(굿)

#### 👦 권오정(팀원/Frontend) : Front

#### 👧 신유빈(팀원/Frontend) : Front

#### 👦 김찬영(팀원/Backend) : Back

#### 👦 양동찬(팀원/Backend) : Back