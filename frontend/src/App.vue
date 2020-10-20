<template>
  <v-app>
    <div id="nav">
      <router-link to="/shop">장보기 화면 보기</router-link> |
      <router-link to="/user">유저화면</router-link>
    </div>
    <router-view />
  </v-app>
</template>

<script>
export default {
  name: "App",

  data: () => ({
    //
  }),
};
</script>
