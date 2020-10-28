<template>
  <div class="notice">
    <div class="mb-3">
      <h3>
        <v-icon color="orange lighten-2">mdi-bell</v-icon> {{ username }}님을
        위한 맞춤 알람
      </h3>
    </div>
    <div v-if="noticeList.length">
      <div v-for="(noticeItem, index) in noticeList" :key="index">
        <v-alert colored-border type="info" elevation="1">
          {{ noticeItem }}
        </v-alert>
      </div>
    </div>
    <div v-else>현재 알림이 없습니다.</div>
  </div>
</template>

<script>
import "@/assets/css/components/Mypage/notice.scss";
export default {
  name: "MypageNotice",
  data() {
    return {
      noticeList: [
        "휴지를 구매한지 한달이 넘었습니다.",
        "렌즈세정액을 구입을 잊지 않으셨나요?",
      ],
      username: "싸피",
    };
  },
};
</script>
