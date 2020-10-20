<template>
  <div>
    마이페이지
    <MypageUpdate />
    <MypageNotice />
  </div>
</template>

<script>
import MypageUpdate from "@/components/Mypage/MypageUpdate";
import MypageNotice from "@/components/Mypage/MypageNotice";

export default {
  name: "Mypage",
  components: {
    MypageUpdate,
    MypageNotice,
  },
};
</script>

<style>
</style>