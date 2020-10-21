<template>
  <v-container>
    <!-- <h1>로그인</h1> -->
    <v-img
      src="https://user-images.githubusercontent.com/60081201/96660569-e562cb80-1384-11eb-9b19-107de574a592.PNG"
      alt="쇼우미도우미 로고"
    />
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
    <v-btn
      width="100%"
      rounded
      dark
      color="teal"
      @click="$router.push({ path: '/perparation' })"
      >로그인</v-btn
    >
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