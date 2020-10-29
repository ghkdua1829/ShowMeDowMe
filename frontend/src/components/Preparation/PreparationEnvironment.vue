<template>
  <div class="preparation-environment">
    <h2 class="ready-title">장보기 준비</h2>
    <v-row class="mt-10">
      <v-col cols="4" class="preparation-type">
        <h3>목표시간</h3>
      </v-col>
       <v-col cols="7">
        <v-slider
          v-model="value"
          :thumb-size="40"
          thumb-label="always"
          step="30"
          v-bind:max="240"
          :rules="rules"
        >
          <template v-slot:thumb-label="{ value }">
            {{ ticksLabels[Math.floor(value / 30)] }}
          </template>
        </v-slider>
        <div>
          <v-btn  depressed> skip </v-btn>
        </div>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="4" class="preparation-type"
        >
        <h3>목표금액</h3>
      </v-col>
      <v-col cols="6">
        <v-text-field
          type="text"
          oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
          v-model="amount"
          outlined
          dense
        ></v-text-field>
        <div>
          <v-btn depressed> skip </v-btn>
        </div>
      </v-col>
      <v-col cols="1" class="preparation-type"><h4>원</h4></v-col>
    </v-row>

    <v-btn
      large
      class="ma-10 m-3 0 4"
      outlined
      @click="$router.push({ path: '/shop' })"
    >
      <h3>장보러 가기</h3>
    </v-btn>
  </div>
</template>

<script>
import "@/assets/css/components/Preparation/preparationEnvironment.scss";

export default {
  name: "PreparationEnvironment",
  created() {
    let today = new Date();
    this.hours = today.getHours();
    this.minute = today.getMinutes();
    this.time = this.hour + ":" + this.minute + ":00";
  },
  methods: {},
  data() {
    return {
      number: "",
      hour: "",
      minute: "",
      time: "",
      // diff_time: 0,
      aim_time: "",
      value: 0,
      rules: [(v) => v <= 120 || "과도한 쇼핑은 건강에 해롭습니다."],
      ticksLabels: ["0m","30m", "1h", "1h30m", "2h", "2h30m", "3h", "3h30m", "4h",],
    };
  },
};
</script>