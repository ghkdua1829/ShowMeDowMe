<template>
  <div class="accountbook-analysis">
    <h2>{{ todayMonth }}월 장보기 분석</h2>
    <!-- {{ datacollection }} -->
    <doughnut-chart :chart-data="datacollection"></doughnut-chart>
  </div>
</template>

<script>
import "@/assets/css/components/AccountBook/accountBookAnalysis.scss";
import DoughnutChart from "./DoughnutChart.js";

export default {
  name: "AccountBookAnalysis",
  components: {
    DoughnutChart,
  },
  mounted() {
    this.fillData();
  },
  created() {
    let todate = new Date();
    this.todayMonth = this.dateToString(todate).substring(4, 6);
  },
  methods: {
    dateToString(date) {
      const year = date.getUTCFullYear();
      let month = date.getUTCMonth() + 1;
      let day = date.getDate();
      let hours = date.getHours();
      let minutes = date.getMinutes();
      month = month > 9 ? month : "0" + month;
      day = day > 9 ? day : "0" + day;
      hours = hours > 9 ? hours : "0" + hours;
      minutes = minutes > 9 ? minutes : "0" + minutes;

      return year + "" + month + "" + day + "" + hours + "" + minutes;
    },
    fillData() {
      this.datacollection = {
        labels: ["생필품", "화장지", "갈비", "생선"],
        datasets: [
          {
            label: "통계내역",
            backgroundColor: [
              "#eb9f9f",
              "#f1bbba",
              "#f8ecc9",
              "#a79c8e",
              "#9DC8C8",
              "#D1B6E1",
              "#E0E3DA",
              "#84B1ED",
              "#D4DFE6",
              "#f8ecc9",
              "#b87978",
            ],
            data: [20, 50, 10, 20], // 42, 19 (61)/ 21,13 (34)
          },
        ],
      };
    },
    getRandomInt() {
      return Math.floor(Math.random() * (50 - 5 + 1)) + 5;
    },
  },
  data() {
    return {
      datacollection: null,
      todayMonth: "",
    };
  },
};
</script>

