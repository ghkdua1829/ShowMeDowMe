<template>
  <div class="report">
    <h1 class="report-title">장보기 Report</h1>
    <h1 class="text-left">
      오늘
      <div class="content">
        장본 시간은
        <v-sheet
          color="gray darken-2"
          elevation="2"
          height="50"
          outlined
          rounded
          width="120"
        ></v-sheet>
      </div>
      <div class="content">
        결재한 금액은
        <v-sheet
          color="gray darken-2"
          elevation="2"
          height="50"
          outlined
          rounded
          width="120"
        ></v-sheet>
      </div>
    </h1>
    <h5 class="content">예상금액보다 원을 사용하셨습니다.</h5>
    <br />
    <v-btn rounded dark @click="$router.push({ path: '/mypage' })">확인</v-btn>
  </div>
</template>

<script>
import "@/assets/css/views/report.scss";

export default {
  name: "Report",
};
</script>

<style>
</style>