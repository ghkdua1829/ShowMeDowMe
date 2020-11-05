<template>
  <div class="shopping">
    <v-icon class="" @click="$router.push({ path: '/perparation' })"> mdi-arrow-left </v-icon>
    <h1>장보는 중</h1>
    <v-row>
      <v-col cols="2">
        <ShoppingMemo />
      </v-col>
      <v-col>
        <v-row>
          <v-col>
            <h5>남은시간</h5>
          </v-col>
          <v-col>
            <section id="app">
              <div class="grid-container">
                <div class="grid-x">
                  <div class="cell flex flex-column">
                    <div id="timer">
                      <span id="minutes">{{ hoursLeft }}</span
                      ><span id="middle">:</span
                      ><span id="seconds">{{ minutesLeft }}</span
                      >:<span id="seconds">{{ secondsLeft }}</span>
                    </div>
                  </div>
                </div>
              </div>
            </section>
          </v-col>
        </v-row>
        <v-progress-linear
          color="light-green darken-4"
          height="10"
          value="20"
          striped
        ></v-progress-linear>
      </v-col>
    </v-row>
    <ShoppingList />
    <!-- <ShoppingReceipe /> -->

    <v-btn
      class="mt-5"
      dark
      color="teal"
      @click="$router.push({ path: '/camera' })"
    >
      <v-icon>mdi-camera </v-icon>
      사진으로 제품 등록
    </v-btn>
    <v-row class="mt-3">
      <v-col>
        <h4>현재 결재 예상 금액 28000원</h4>
      </v-col>
      <v-col>
        <v-btn dark @click="$router.push({ path: '/report' })"> 완료 </v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import "@/assets/css/views/shopping.scss";
import ShoppingList from "@/components/Shopping/ShoppingList";
import ShoppingMemo from "@/components/Shopping/ShoppingMemo";
// import ShoppingReceipe from "@/components/Shopping/ShoppingRecipe";

export default {
  name: "Shopping",
  components: {
    ShoppingMemo,
    // ShoppingReceipe,
    ShoppingList,
  },
  props: {
    hours: {
      default: 2,
    },

    minutes: {
      default: 30,
    },

    seconds: {
      default: 5,
    },

    endpoint: {},
  },

  data() {
    return {
      hoursLeft: this.hours,
      minutesLeft: this.minutes,
      secondsLeft: this.seconds,
    };
  },

  methods: {
    resetTimer() {
      this.hoursLeft = this.hours;
      this.minutesLeft = this.minutes;
      this.secondsLeft = this.seconds;
    },

    zeroPad(input, length) {
      return (Array(length + 1).join("0") + input).slice(-length);
    },
  },

  mounted() {
    this.resetTimer();

    this.$nextTick(function () {
      window.setInterval(() => {
        if (this.secondsLeft > 0) {
          this.secondsLeft--;
        } else if (this.secondsLeft == 0 && this.minutesLeft > 0) {
          this.secondsLeft = 59;
          this.minutesLeft--;
        } else if (
          this.secondsLeft == 0 &&
          this.minutesLeft == 0 &&
          this.hoursLeft > 0
        ) {
          this.secondsLeft = 59;
          this.minutesLeft = 59;
          this.hoursLeft--;
        } else if (
          this.secondsLeft == 0 &&
          this.minutesLeft == 0 &&
          this.hoursLeft == 0
        ) {
          alert("쇼핑시간이 초과하였습니다");
          this.secondsLeft--;
        }
      }, 1000);
    });
  },
  computed: {
    timeLeft: function () {
      if (this.hours !== 0) {
        return (
          this.hoursLeft +
          ":" +
          this.zeroPad(this.minutesLeft, 2) +
          ":" +
          this.zeroPad(this.secondsLeft, 2)
        );
      } else if (this.minutes !== 0) {
        return this.minutesLeft + ":" + this.zeroPad(this.secondsLeft, 2);
      } else {
        return this.secondsLeft;
      }
    },
  },
};
</script>

<style>
</style>