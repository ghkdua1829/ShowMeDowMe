<template>
  <div class="navigation">
    <v-bottom-navigation v-model="value" :background-color="color" dark shift>
      <v-btn @click="$router.push({ path: '/perparation' })">
        <span>장보기</span>
        <v-icon>mdi-cart</v-icon>
      </v-btn>
      <v-btn @click="$router.push({ path: '/accountbook' })">
        <span>가계부</span>
        <v-icon>mdi-notebook-edit-outline</v-icon>
      </v-btn>
      <v-btn @click="$router.push({ path: '/mypage' })">
        <span>마이페이지</span>
        <v-icon>mdi-account</v-icon>
      </v-btn>
    </v-bottom-navigation>
  </div>
</template>

<script>
import "@/assets/css/components/navigation.scss";

export default {
  name: "Navigation",
  created() {
    this.select = this.$route.name.name;
    if (this.select === "Preparation" || this.select == "PreparationMemo") {
      this.value = 0;
    } else if (this.select === "AccountBook") {
      this.value = 1;
    } else {
      this.value = 2;
    }
  },
  computed: {
    color() {
      switch (this.value) {
        case 0:
          return "teal lighten-2";
        case 1:
          return "teal";
        case 2:
          return "teal darken-2";
        default:
          return "blue-grey";
      }
    },
  },
  data() {
    return {
      value: 0,
      select: "",
    };
  },
};
</script>

<style>
</style>