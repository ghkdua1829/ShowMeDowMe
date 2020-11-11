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
        <small>이전에 기록이 없는 물품인 경우 </small>
      </div>
      <small>소비자의 평균 주기를 기준으로 제공합니다.</small>
    </div>
    <div class="notice mt-4">
      <div class="mb-3">
        <h3>
          <v-icon color="orange lighten-2">mdi-bell</v-icon>{{ username }} 님을
          위한 맞춤 알람
        </h3>
        <h5>꾸준한 구매가 필요한 품목을 선택하여</h5>
        <h5>자신만을 위한 알람을 받으세요.</h5>
      </div>
      <div v-if="noticeList.length">
        <div v-for="(noticeItem, index) in noticeList" :key="index">
          <v-alert
            @click="offAlarm(noticeItem)"
            colored-border
            type="info"
            elevation="1"
          >
            {{ noticeItem }}
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
    const StartURL =
      SERVER.URL + SERVER.ROUTES.alarm + "?userid=" + this.username;
    axios
      .get(StartURL)
      .then((res) => {
        for (let j = 0; j < res.data.length; j++) {
          this.selectList.push(res.data[j].categoryid);
        }
        console.log(res);
        // this.selectList = res.data;
      })
      .catch((err) => console.log(err));
  },
  methods: {
    offAlarm(notice) {
      console.log(notice);
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
      console.log(data);
      axios.post(SelectURL, data).then((res) => {
        console.log(res);
      });
    },
  },
  data() {
    return {
      noticeList: [
        "휴지를 구매한지 한달이 넘었습니다.",
        "렌즈세정액을 구입을 잊지 않으셨나요?",
      ],
      username: "",
      selectList: [],
      productList: [
        "렌즈세정액",
        "칫솔",
        "치약",
        "마스크",
        "비누",
        "린스",
        "샴푸",
        "쌀",
        "생수",
        "화장지",
        "라면",
        "계란",
        "즉석밥",
        "면도용품",
        "면봉",
      ],
    };
  },
};
</script>
