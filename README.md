# [Sub PJT 3] 자율 프로젝트 프로젝트 

## :smiley: ​프로젝트 소개

### 기획 배경

1.  장보러 갔을 때 가격 파악의 어려움으로 인해 예산에 맞는 쇼핑을 하도록 도와주는 서비스를 기획하게 되었습니다.

2. 생필품과 같이 주기적으로 사야하는 물품에 대해 알림 기능으로 과소비를 막아주기 위한 기능을 구현하고자 하였습니다.

### 프로젝트 이름

`쇼우미도우미`는 Show me 도우미 직역하면 **도우미의 역할을 보여줘**라는 의미로 장 보는 사람들을 위한 도우미의 역할을 톡톡히 할 것 을 보여준다는 포부를 담고 있습니다.

![로고화면](https://user-images.githubusercontent.com/60081201/96952527-ff89de80-1529-11eb-9128-bc19eca5d01c.PNG)

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

<img src="https://img.shields.io/badge/OS-Ubuntu 18.04-blueviolet" alt="기술스택" style="zoom:120%;" /><img src="https://img.shields.io/badge/server-nginx, AWS, docker-9cf" alt="기술스택" style="zoom:120%;" /><img src="https://img.shields.io/badge/database- mariaDB-yellowgreen" alt="기술스택" style="zoom:120%;" /><img src="https://img.shields.io/badge/Frontend-Vue.js, css/html/JavaScript, vuetify, scss-green" alt="기술스택" style="zoom:120%;" /><img src="https://img.shields.io/badge/backend-Springboot, flask-ff69b4" alt="기술스택" style="zoom:120%;" /><img src="https://img.shields.io/badge/language-Java, JavaScript, Python-important" alt="기술스택" style="zoom:120%;" /> 



### 주요 기술

- PWA

  PWA를 사용하여 브라우저에서 카메라 접근합니다.

- Vision API

  google vision Api 를 사용하여 텍스트 감지 구현합니다.

![주요기술](https://user-images.githubusercontent.com/60081201/99199150-7724f380-27e0-11eb-954f-864b78552bc6.PNG)



### 아키텍쳐

<img width="1195" alt="아키텍처" src="https://user-images.githubusercontent.com/60081201/99185305-39967b00-278c-11eb-8d0e-9dc39c8cb2ac.png">

- NGINX

  ​	80번 포트로 접근하는 요청들을 443포트로 변경하여 http요청을  https 요청으로 리다이렉트

- Flask

  ​	사진 분석요청을 Google Vison Api 를 통해 분석

- Spring Boot

  ​	데이터베이스에 접근하거나 상품의 대분류나 소분류를 Naver Developer API를 통해 분석



### 핵심 기능

쇼핑하는(장보는) 사람들의 편의를 위해 가격표 사진 분석 **장바구니 담기, 생필품 맞춤 알람, 월별 소비 분석**의 기능을 제공하는 모바일웹입니다.

![핵심기능](https://user-images.githubusercontent.com/60081201/99199083-f8c85180-27df-11eb-8012-c0d89e9f156d.PNG)



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

![erd](https://user-images.githubusercontent.com/60081201/99185323-621e7500-278c-11eb-9e0e-0618ed468c6a.png)



## :star: 쇼우미 도우미 웹 모바일 페이지 및 기능 소개

### **시작화면**

- 로그인 기능과 비회원 기능을 통해 서비스를 이용하실 수 있습니다.
  - 비회원인 경우 3가지 주요 기능(장바구니 담기, 생필품 맞춤 알람, 월별 소비 분석) 중 장바구니 기능에 대한 이용을 제공합니다.
- 오른쪽 상단의 느낌표 아이콘을 통해 쇼우미도우미에 관한 설명을 볼 수 있습니다.

![첫화면](https://user-images.githubusercontent.com/60081201/99185306-3a2f1180-278c-11eb-93b8-7af1a1a2542d.png)



### **회원가입 화면**

- 쇼우미도우미의 기능들을 이용하기 위한 회원가입 화면입니다.
- 아이디 중복체크와 비밀번호로 간편하게 회원가입을 할 수 있습니다.

![회원가입](https://user-images.githubusercontent.com/60081201/99185302-37ccb780-278c-11eb-9dd3-670f545ea7bb.png)

### **마이페이지 화면**

- 주기적으로 구입이 필요한 생필품의 구매 내역 분석을 통해 사용자 맞춤 알람을 제공합니다.
- 개인 정보 설정 탭을 통해 비밀번호를 변경하거나 회원을 탈퇴하실 수 있습니다.
- 로그아웃 탭을 사용하여 로그아웃 하실 수도 있습니다.

![마이페이지](https://user-images.githubusercontent.com/60081201/99185294-2683ab00-278c-11eb-80fb-b45ac71ff374.png)



### 장보기 준비 화면 

- 목표 금액, 목표 시간 설정이 가능합니다. 또한 스킵버튼을 통해 설정하지 않을 수도 있습니다.
- 구매 목록 메모 기능을 통해 장보기 전 구입할 목록을 작성할 수 있습니다.

![image](https://user-images.githubusercontent.com/44996614/99185608-4c11b400-278e-11eb-8ad6-451c1c836be8.png)

#### **구매 목록 메모 화면**

- 장보기 전 구입할 목록을 기입하여 메모에 추가할 수 있습니다.
- 상품들 옆 X버튼을 통해 메모장에서 지울 수 있습니다.

![메모장화면](https://user-images.githubusercontent.com/60081201/99185303-38fde480-278c-11eb-8ca9-306b4bb5710e.png)

#### **장보기 화면**

- 상단에는 사용자가 설정한 시간이 표시됩니다. 하단에는 현재 결제 예상 금액이 표시됩니다.
- 사진으로 제품 등록 버튼을 통해 사진으로 제품을 등록하거나
- 직접 추가하기 버튼을 통해 직접 추가하실 수 있습니다.
- 장보기가 끝났을 경우 장보기 완료 버튼을 통해 장보기를 마무리하실 수 있습니다.

![바코드화면](https://user-images.githubusercontent.com/60081201/99185304-38fde480-278c-11eb-98c8-c60f5a3ba290.png)

#### **장보기 결과 화면**

- 설정한 금액을 초과하거나 시간을 초과한 경우 얼굴이 바뀌어 출력됩니다.
- 초과한 금액이나 시간을 표시해줍니다.

![피드백화면](https://user-images.githubusercontent.com/60081201/99185308-3b603e80-278c-11eb-8a07-eae2b33b5279.png)

### 가계부 화면

- 월별로 사용자 소비했던 가격들을 보여주며, 해당 구매 날짜를 클릭할 경우 그날 샀던 물품과 이모티콘얼굴을 통해 설정한 시간, 가격을 잘 지켰는 지를 표시해줍니다.

- 월별로 사용자의 소비패턴을 분석하여 구입한 물품 통계를 보여줍니다.

![캘린더화면](https://user-images.githubusercontent.com/60081201/99185307-3ac7a800-278c-11eb-8112-e294f961744c.png)



## 기대효과

![기대효과](https://user-images.githubusercontent.com/60081201/96952891-d584ec00-152a-11eb-8845-3b22e2b8fc6d.PNG)



---

### :calendar: 개발일정​

![image](https://user-images.githubusercontent.com/44996614/99185625-6f3c6380-278e-11eb-8d2a-f5720e1c76bf.png)




## :school: ​팀원 소개 & 소감


- #### 김원정 (팀장 / Spring 백엔드)

  맏형으로 프로젝트를 이끌어가며 동생들의 비유를 잘 맞춰주는 오정이형, 프론트와 백엔드에 다재다능하며 가끔 엉뚱한 매력이 있는 찬영이, 자신의 위치에서 책임감있게 끝까지 마무리하는 동찬이, 항상 팀분위기를 밝게 해주며 누구보다도 착하고 열심히 하는 막내 유빈이 이러한 사람들 덕분에 서로에게 시너지를 주면서 다들 발전했다고 생각하고 성공적으로 프로젝트를 마무리 했다고 생각합니다. 마지막 프로젝트이지만 우리는 이제 시작이다. 연탄태우기 전에 다들 취업하기를
  
- #### 권오정 (팀원 / Front, 서버 배포 및 환경 구축)

  자율 프로젝트에서 프론트개발과 배포를 맡아 클라이언트에서 서버로 통신 하는 과정을 좀더 자세히 알수있었습니다. 그러면서 프로토콜과 도커 가상화를 공부하며 적용하는 것이 저에게 좋은 경험이었습니다. 마지막까지 취업 준비와 자율 프로젝트 모두 열심히해준 팀원들 모두 고맙습니다.
  


- #### 신유빈 (팀원 / Front)

  이번 자율 프로젝트를 통해 vuex에 대한 이해도를 높였고 postman을 이용하여 백과의 연결을 원활하게 진행할 수 있었습니다. 또한 좋은 팀원들과 함께하여 더 즐겁게 프로젝트를 할 수 있었습니다. 프로젝트를 진행하면서 이슈 발생할 때마다 서로 도와주며 해결하는 과정 속에서 많이 배웠습니다. 마지막 프로젝트라는 것이 시원섭섭한 것 같습니다. 모두 수고하셨습니다!
  
- #### 김찬영 (팀원 / flask 백엔드)

  좋은 사람들과 좋은 프로젝트를 만들수 있어서 뜻 깊었습니다. 팀원들에게 배울점들도 많았고, 노력하여 프로젝트를 완성할 수 있어서 성취감은 두배였습니다. 기술적으로나 협업적으로 한층 더 성장할 수 있었던 프로젝트였습니다. 감사합니다.

- #### 양동찬 (팀원 / Spring 백엔드)

  마지막 프로젝트에서 좋은 팀원들을 만나 좋은 프로젝트를 수행할 수 있어서 정말 좋았습니다.

  Spring boot에 대해 이해도를 높일 수 있었고, 개발에 대한 많은 점들을 배울 수 있었습니다. 다양한 기술을 사용해보려고 노력했고 좋은 결과물이 탄생하여 더욱 뜻깊었던 프로젝트였던 것 같습니다. 다들 좋은 곳에 취업해서 다시 웃으면서 볼 수 있기를. 또한  프로젝트 수행하며 도와주었던 오정이형 원정이 찬영이 유빈이 모두 감사합니다.



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

