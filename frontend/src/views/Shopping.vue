<template>
  <div class="shopping">
    <h1>장보는 중</h1>
    <v-row>
      <v-col cols="2">
        <ShoppingMemo />
      </v-col>
      <v-col> 소요시간: </v-col>
    </v-row>
    <ShoppingList />
    <!-- <ShoppingReceipe /> -->

    <v-btn
      class="mt-5"
      dark
      color="teal"
      @click="$router.push({ path: '/camera' })"
    >
      <v-icon>mdi-camera </v-icon>
      사진으로 제품 등록
    </v-btn>
    <v-row class="mt-3">
      <v-col>
        <h4>현재 결재 예상 금액 28000원</h4>
      </v-col>
      <v-col>
        <v-btn dark @click="$router.push({ path: '/report' })"> 완료 </v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import "@/assets/css/views/shopping.scss";
import ShoppingList from "@/components/Shopping/ShoppingList";
import ShoppingMemo from "@/components/Shopping/ShoppingMemo";
// import ShoppingReceipe from "@/components/Shopping/ShoppingRecipe";

export default {
  name: "Shopping",
  components: {
    ShoppingMemo,
    // ShoppingReceipe,
    ShoppingList,
  },
};
</script>

<style>
</style>