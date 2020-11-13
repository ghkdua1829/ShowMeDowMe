<template>
  <div class="report">
    <h1 class="report-title">오늘의 Report</h1>
    <v-avatar size="95" class="mb-3">
      <img alt="user" :src="gradeImage[reportGrade]" />
    </v-avatar>
    <div>
      <small>{{ reportSentence[reportGrade] }}</small>
    </div>

    <h2 class="content">결재한 금액은 {{ nowExpense }}원 입니다.</h2>
    <div v-if="diffExpense">
      <h3 class="content">
        {{ diffExpense }}
      </h3>
    </div>
    <div v-if="check.timecheck !== 'X'">
      <h2 class="content">{{ diffTime }}</h2>
    </div>
    <br />
    <div v-if="member">
      <v-btn
        width="60%"
        rounded
        dark
        color="teal lighten-2"
        @click="completeReport()"
        >확인</v-btn
      >
    </div>
    <div v-else>
      <v-btn
        width="60%"
        rounded
        dark
        color="teal lighten-2"
        @click="$router.push({ path: '/' })"
        >확인</v-btn
      >
    </div>
    <div
      style="padding: 5px"
      v-if="!member"
      @click="$router.push({ name: 'Signup' })"
    >
      회원가입 하러가기
    </div>
  </div>
</template>

<script>
import "@/assets/css/views/report.scss";
import { mapState, mapActions } from "vuex";

export default {
  name: "Report",
  created() {
    if (sessionStorage.length === 0) {
      this.member = false;
    }
    if (this.check.moneycheck !== "X") {
      this.diffExpense = Math.abs(this.nowExpense - this.aimExpense);
      if (this.nowExpense > this.aimExpense) {
        this.diffExpense =
          "목표 금액보다 " + String(this.diffExpense) + "원을 소비하셨습니다.";
      } else if (this.nowExpense < this.aimExpense) {
        this.diffExpense =
          "목표 금액보다 " +
          String(this.diffExpense) +
          "원 적게 장을 보았습니다. good~";
      }
    }
    if (this.check.timecheck !== "X") {
      if (this.check.timecheck === "T") {
        this.diffTime = "목표 시간을 지켰습니다.";
      } else {
        this.diffTime = "목표 시간을 지키지 못했습니다.";
      }
    }
  },
  computed: {
    ...mapState([
      "reportGrade",
      "gradeImage",
      "check",
      "aimTime",
      "aimExpense",
      "nowExpense",
    ]),
  },
  methods: {
    ...mapActions(["removeData"]),
    completeReport() {
      this.removeData();
      this.$router.push({ path: "/mypage" });
    },
  },
  data() {
    return {
      member: true,
      reportSentence: [
        "",
        "합리적인 쇼핑 완료! 집 조심히 가세요~",
        "즐거운 장보기가 되셨나요? 당신의 옆에 든든한 도우미가 되겠습니다.",
        "과소비 주의!",
      ],
      diffExpense: 0,
      diffTime: 0,
    };
  },
};
</script>

<style>
</style>