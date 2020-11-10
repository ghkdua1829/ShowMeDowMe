<template>
  <div class="report">
    <h1 class="report-title">오늘의 Report</h1>
    <h2 class="content">장본 시간은 1시간 2분</h2>
    <h2 class="content">결재한 금액은 54000원</h2>

    <h5 class="content">예상금액보다 12000원을 사용하셨습니다.</h5>
    <br />
    <v-btn
      width="60%"
      rounded
      dark
      color="teal lighten-2"
      @click="$router.push({ path: '/mypage' })"
      >확인</v-btn
    >
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