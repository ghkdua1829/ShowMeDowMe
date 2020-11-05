<template>
  <v-app>
    <div class="mobile-size">
      <router-view />
    </div>
    <div class="web-size">
      <v-img
        src="https://user-images.githubusercontent.com/60081201/97841020-64aeb280-1d28-11eb-80a1-cb26086f08f8.PNG"
        alt="쇼우미도우미는 모바일 환경에서 서비스를 지원합니다."
      />
    </div>
  </v-app>
</template>

<script>
import "@/assets/css/app.scss";

export default {
  name: "App",

  data: () => ({
    //
  }),
};
</script>
