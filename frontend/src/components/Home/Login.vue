<template>
  <v-container>
    <!-- <h1>로그인</h1> -->
    <v-img
      src="https://user-images.githubusercontent.com/60081201/96660569-e562cb80-1384-11eb-9b19-107de574a592.PNG"
      alt="쇼우미도우미 로고"
    />
    <div class="login">
      <v-text-field
        v-model="loginData.userid"
        label="아이디"
        required
      ></v-text-field>
      <v-text-field
        v-model="loginData.userpw"
        type="password"
        label="비밀번호"
        @keypress.enter="login(loginData)"
        required
      ></v-text-field>
      <v-btn
        class="login-btn"
        width="60%"
        rounded
        color="teal lighten-2"
        @click="login(loginData)"
        :disabled="!(this.loginData.userid && this.loginData.userpw)"
        >로그인</v-btn
      >
      <v-btn
        color="primary"
        text
        @click="
          dialog = false;
          $router.push({ name: 'Signup' });
        "
        >회원가입하러가기</v-btn
      >
    </div>
  </v-container>
</template>

<script>
import "@/assets/css/components/Home/login.scss";
import { mapActions } from "vuex";

export default {
  name: "Login",
  methods: {
    ...mapActions(["login"]),
  },
  data() {
    return {
      loginData: {
        userid: "",
        userpw: "",
      },
    };
  },
};
</script>

<style>
</style>