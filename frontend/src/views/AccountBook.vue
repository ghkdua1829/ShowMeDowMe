<template>
  <div>
    가계부
    <AccountBookCalendar />
    <AccountBookAnalysis />
  </div>
</template>

<script>
import AccountBookCalendar from "@/components/AccountBook/AccountBookCalendar";
import AccountBookAnalysis from "@/components/AccountBook/AccountBookAnalysis";

export default {
  name: "AccountBook",
  components: {
    AccountBookCalendar,
    AccountBookAnalysis,
  },
};
</script>

<style>
</style>
