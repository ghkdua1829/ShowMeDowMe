<template>
  <div>
    가계부
    <AccountBookCalendar />
    <AccountBookAnalysis />
    <Navigation />
  </div>
</template>

<script>
import Navigation from "@/components/Navigation";
import AccountBookCalendar from "@/components/AccountBook/AccountBookCalendar";
import AccountBookAnalysis from "@/components/AccountBook/AccountBookAnalysis";

export default {
  name: "AccountBook",
  components: {
    AccountBookCalendar,
    AccountBookAnalysis,
    Navigation,
  },
};
</script>

<style>
</style>
