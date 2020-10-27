<template>
  <div>
    <v-img
      src="https://user-images.githubusercontent.com/60081201/96660569-e562cb80-1384-11eb-9b19-107de574a592.PNG"
      alt="쇼우미도우미 로고"
    />
    <div class="text-center homepage">
      <div>
        <v-btn
          rounded
          dark
          color="teal lighten-2"
          @click="$router.push({ name: 'Login' })"
          >로그인</v-btn
        >
      </div>
      <div>
        <div id="app">
          <h2>For Kakao Vue</h2>
          <a :href="kakaoLoginLink" alt="kakao login">
            <img alt="kakao logo" src="@/assets/kakao/kakao_login_large_wide.png" />
          </a>
      </div>
      </div>
      <div>
        <v-btn
          rounded
          depressed
          outlined
          color="teal"
          @click="$router.push({ path: '/nonMember/perparation' })"
        >
          비회원
        </v-btn>
      </div>
      <div @click="$router.push({ name: 'Signup' })">회원가입 하러가기</div>
    </div>
  </div>
</template>

<script>
import "@/assets/css/views/home.scss";

export default {
  name: "Home",
    data: () => ({
    client_id: "640e256a4bda90ce12ff1962580001da",  // 앱 생성 시 발급 받은 Rest Api 키 넣기
    redirect_uri: "http://localhost:3000/perparation",
  }),
  computed: {
    kakaoLoginLink() {
      return `https://kauth.kakao.com/oauth/authorize?client_id=${this.client_id}&redirect_uri=${this.redirect_uri}&response_type=code`;
    },
  },
};
</script>
