<template>
  <div>
    <Navigation />
    마이페이지
    <MypageUpdate />
    <MypageNotice />
  </div>
</template>

<script>
import Navigation from "@/components/Navigation";
import MypageUpdate from "@/components/Mypage/MypageUpdate";
import MypageNotice from "@/components/Mypage/MypageNotice";

export default {
  name: "Mypage",
  components: {
    Navigation,
    MypageUpdate,
    MypageNotice,
  },
};
</script>

<style>
</style>