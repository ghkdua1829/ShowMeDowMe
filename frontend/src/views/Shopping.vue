<template>
  <div>
    <h1>장보기 화면</h1>
    <ShoppingMemo />
    <ShoppingReceipe />
    소요시간:
    <ShoppingList />
    <ShoppingPhotoReg />
    결재금액:
    <router-link :to="{ path: '/report' }">완료</router-link>
  </div>
</template>

<script>
import ShoppingList from "@/components/Shopping/ShoppingList";
import ShoppingPhotoReg from "@/components/Shopping/ShoppingPhotoReg";
import ShoppingMemo from "@/components/Shopping/ShoppingMemo";
import ShoppingReceipe from "@/components/Shopping/ShoppingRecipe";

export default {
  name: "Shopping",
  components: {
    ShoppingMemo,
    ShoppingReceipe,
    ShoppingList,
    ShoppingPhotoReg,
  },
};
</script>

<style>
</style>