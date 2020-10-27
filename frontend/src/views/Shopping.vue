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
    <v-btn @click="$router.push({ path: '/camera' })"> 물건등록 </v-btn>
    결재금액:
    <router-link :to="{ path: '/report' }">완료</router-link>
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