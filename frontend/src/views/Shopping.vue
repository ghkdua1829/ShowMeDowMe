<template>
  <div>
    <h1>장보기 화면</h1>
    <ShoppingMemo />
    <ShoppingReceipe />
    소요시간:
    <ShoppingList />
    <v-btn @click="$router.push({ path: '/camera'})">
    물건등록
    </v-btn>
    결재금액:
    <router-link :to="{ path: '/report' }">완료</router-link>
  </div>
</template>

<script>
import ShoppingList from "@/components/Shopping/ShoppingList";
import ShoppingMemo from "@/components/Shopping/ShoppingMemo";
import ShoppingReceipe from "@/components/Shopping/ShoppingRecipe";

export default {
  name: "Shopping",
  components: {
    ShoppingMemo,
    ShoppingReceipe,
    ShoppingList,
  },
};
</script>

<style>
</style>