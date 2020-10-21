<template>
  <v-container>
    <h1>로그인</h1>
    <v-text-field
      v-model="loginData.userId"
      label="아이디"
      required
    ></v-text-field>
    <v-text-field
      v-model="loginData.password"
      type="password"
      label="비밀번호"
      required
    ></v-text-field>
  </v-container>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginData: {
        userId: "",
        password: "",
      },
    };
  },
};
</script>

<style>
</style>