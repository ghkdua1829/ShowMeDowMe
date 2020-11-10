<template>
  <div>
    <Navigation />
    <div class="mypage">
      <h1>마이페이지</h1>
      <div class="btn-logout">
        <v-col>
          <v-icon @click="logout">mdi-logout</v-icon>
        </v-col>
        <small @click="logout">로그아웃</small>
      </div>
      <div class="mt-3">
        <MypageNotice />
      </div>
      <div class="mypage-update-button mt-5">
        <MypageUpdate />
      </div>
    </div>
  </div>
</template>

<script>
import Navigation from "@/components/Navigation";
import MypageUpdate from "@/components/Mypage/MypageUpdate";
import MypageNotice from "@/components/Mypage/MypageNotice";
import { mapActions } from "vuex";
import "@/assets/css/views/mypage.scss";

export default {
  name: "Mypage",
  components: {
    Navigation,
    MypageUpdate,
    MypageNotice,
  },
  methods: {
    ...mapActions(["logout"]),
  },
};
</script>
