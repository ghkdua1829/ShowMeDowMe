<template>
  <div>
    <h3 class="text-center blue--text">장보기 Report </h3>
    <h1>
      오늘 
      <div class= "d-flex ">
        <br>장본 시간은
          <v-sheet
            color="gray darken-2"
            elevation="2"
            height="50"
            outlined
            rounded
            width="150"
          ></v-sheet>
      </div>
      <div class='d-flex'>
      <br>결재한 금액은
          <v-sheet
            color="gray darken-2"
            elevation="2"
            height="50"
            outlined
            rounded
            width="150"
          ></v-sheet>
        </div>
    </h1>
    <h5> 예상금액보다 원을 사용하셨습니다.</h5>
    <router-link :to="{ path: '/user' }">확인</router-link>
  </div>
</template>

<script>
export default {
  name: "Report",
};
</script>

<style>
</style>