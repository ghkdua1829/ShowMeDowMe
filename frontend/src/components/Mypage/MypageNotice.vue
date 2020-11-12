<template>
  <div>
    <div>
      <v-select
        v-model="selectList"
        :items="productList"
        label="알람을 받고 싶은 물품 선택"
        multiple
        chips
        @change="changeSelect()"
      ></v-select>
      <div>
        <small>이전에 구매 기록이 없는 물품인 경우 </small>
      </div>
      <small>소비자의 평균 주기를 기준으로 제공합니다.</small>
    </div>
    <small>구입 이후 자신의 소비 패턴에 맞춰 제공합니다.</small>

    <div class="notice mt-4">
      <div class="mb-3">
        <h3>
          <v-icon color="orange lighten-2">mdi-bell</v-icon>{{ username }} 님을
          위한 맞춤 알람
        </h3>
        <h5>꾸준한 구매가 필요한 품목을 선택하여</h5>
        <h5>자신만을 위한 알람을 받으세요.</h5>
      </div>
      <div v-if="alarmList.length">
        <div v-for="(alarmItem, index) in alarmList" :key="index">
          <v-alert colored-border type="info" elevation="1">
            {{ alarmItem[0] }} {{ alarmItem[1] }}
            <div class="btn-close">
              <v-icon small @click="offAlarm(alarmItem[0])">mdi-check</v-icon>
            </div>
          </v-alert>
        </div>
      </div>
      <div v-else>현재 알림이 없습니다.</div>
    </div>
  </div>
</template>

<script>
import "@/assets/css/components/Mypage/mypageNotice.scss";
import SERVER from "@/api/spring";
import axios from "axios";

export default {
  name: "MypageNotice",
  created() {
    this.username = sessionStorage.userid;
    this.StartURL =
      SERVER.URL + SERVER.ROUTES.alarm + "?userid=" + this.username;
    axios
      .get(this.StartURL)
      .then((res) => {
        for (let j = 0; j < res.data.length; j++) {
          if (res.data[j].alarm === 1 && res.data[j].alarm2 === 1) {
            this.alarmList.push([
              res.data[j].categoryid,
              this.noticeList[j % 3],
            ]);
          }
          this.selectList.push(res.data[j].categoryid);
        }
      })
      .catch((err) => console.err(err));
  },
  methods: {
    offAlarm(alarmItem) {
      const AlarmURL = SERVER.URL + SERVER.ROUTES.offAlarm;
      const alarmData = {
        userid: this.username,
        categoryid: alarmItem,
        alarm: 0,
      };
      axios.post(AlarmURL, alarmData).then(() => {
        const alertText = alarmItem + "에 대한 알람 확인했습니다.";
        alert(alertText);
        axios
          .get(this.StartURL)
          .then((res) => {
            this.selectList = [];
            this.alarmList = [];
            for (let j = 0; j < res.data.length; j++) {
              if (res.data[j].alarm === 0 && res.data[j].alarm2 === 1) {
                this.alarmList.push([
                  res.data[j].categoryid,
                  this.noticeList[j % 3],
                ]);
              }
              this.selectList.push(res.data[j].categoryid);
            }
          })
          .catch((err) => console.err(err));
      });
      console.log(alarmData, AlarmURL);
    },
    changeSelect() {
      const SelectURL = SERVER.URL + SERVER.ROUTES.alarm;
      const data = [];
      for (let i = 0; i < this.productList.length; i++) {
        if (this.selectList.includes(this.productList[i])) {
          data.push({
            userid: sessionStorage.userid,
            categoryid: this.productList[i],
            check: "Y",
          });
        } else {
          data.push({
            userid: sessionStorage.userid,
            categoryid: this.productList[i],
            check: "N",
          });
        }
      }
      axios.post(SelectURL, data).catch((err) => {
        console.err(err);
      });
    },
  },
  data() {
    return {
      noticeList: [
        " 곧 구입이 필요하지 않으신가요?",
        " 구입해야 합니다.",
        " 구입을 잊지 않으셨나요?",
      ],
      username: "",
      selectList: [],
      productList: [
        "렌즈세정액",
        "칫솔",
        "치약",
        "클렌징비누",
        "린스",
        "샴푸",
        "쌀",
        "생수",
        "화장지",
        "라면",
        "즉석밥",
      ],
      alarmList: [],
      StartURL: "",
    };
  },
};
</script>
