<template>
  <div class="preparation-environment">
    <h2 class="ready-title">장보기 준비</h2>
    <v-row>
      <v-col cols="4" class="preparation-type">
        <h3>목표시간</h3>
        <div>
          <v-btn x-small depressed> skip </v-btn>
        </div>
      </v-col>
      <v-col cols="7">
        <v-text-field
          :value="time"
          v-model="aim_time"
          type="time"
          @click="calTime()"
          outlined
        ></v-text-field>
        <!-- <p>
          현재 시각에서 {{ diff_time }}분 뒤 쇼핑을 끝날 것을 목표로 합니다.
        </p> -->
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="4" class="preparation-type"
        ><h3>목표금액</h3>
        <div>
          <v-btn x-small depressed> skip </v-btn>
        </div>
      </v-col>
      <v-col cols="6">
        <v-text-field
          type="text"
          oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
          v-model="amount"
          outlined
          dense
        ></v-text-field>
      </v-col>
      <v-col cols="1" class="preparation-type"><h4>원</h4></v-col>
    </v-row>

    <v-btn
      class="ma-2 m-3 0 4"
      outlined
      @click="$router.push({ path: '/shop' })"
    >
      <!-- color="indigo" -->
      <h4>장보러 가기</h4>
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
      amount: 0,
      number: "",
      hour: "",
      minute: "",
      time: "",
      // diff_time: 0,
      aim_time: "",
    };
  },
};
</script>
