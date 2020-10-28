<template>
  <div>
    <Navigation />
    <div class="mypage">
      <h1>마이페이지</h1>
      <MypageNotice />
      <div class="mypage-update-button">
        <MypageUpdate />
      </div>
    </div>
  </div>
</template>

<script>
import Navigation from "@/components/Navigation";
import MypageUpdate from "@/components/Mypage/MypageUpdate";
import MypageNotice from "@/components/Mypage/MypageNotice";
import "@/assets/css/views/mypage.scss";

export default {
  name: "Mypage",
  components: {
    Navigation,
    MypageUpdate,
    MypageNotice,
  },
};
</script>
