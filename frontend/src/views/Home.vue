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
        <v-btn rounded depressed>소셜로그인</v-btn>
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
};
</script>
