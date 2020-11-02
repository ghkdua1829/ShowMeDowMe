<template>
  <v-container fluid>
    <div class="signup">
      <h1>회원가입</h1>
      <!-- 아이디(중복검사 필요) -->
      <v-row>
        <v-col>
          <v-text-field
            v-model="signupData.userId"
            label="아이디"
            required
          ></v-text-field>
        </v-col>
        <v-col>
          <v-btn @click="checkId" rounded dark color="teal lighten-2" outlined
            >중복 체크</v-btn
          >
        </v-col>
      </v-row>
      <!-- 비밀번호 -->
      <v-text-field
        v-model="password"
        :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
        :rules="[rules.required, rules.min]"
        :type="show1 ? 'text' : 'password'"
        name="input-10-1"
        label="비밀번호"
        hint="최소 8자로 입력해주세요."
        @click:append="show1 = !show1"
      ></v-text-field>
      <v-text-field
        v-model="signupData.password"
        :rules="[rulescheck.required, rulescheck.match]"
        name="input-10-1"
        label="비밀번호 확인"
        type="password"
      ></v-text-field>
      <!-- 생일 -->
      <v-menu
        ref="menu"
        v-model="birthCalendar"
        :close-on-content-click="false"
        transition="scale-transition"
        offset-y
        min-width="290px"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-text-field
            v-model="signupData.birth"
            label="생일"
            prepend-icon="mdi-calendar"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker
          ref="picker"
          v-model="signupData.birth"
          :max="new Date().toISOString().substr(0, 10)"
          min="1950-01-01"
          @change="save"
        ></v-date-picker>
      </v-menu>
      <!-- 성별 -->
      <v-radio-group v-model="signupData.gender" :mandatory="false">
        <template v-slot:label>
          <div>성별</div>
        </template>
        <v-radio label="남성" value="남성"></v-radio>
        <v-radio label="여성" value="여성"></v-radio>
      </v-radio-group>
      <v-btn
        width="100%"
        rounded
        dark
        color="teal lighten-2"
        @click="$router.push({ path: '/perparation' })"
        >완료</v-btn
      >
    </div>

    <!-- 백에 보낼 데이터 {{ signupData }} -->
  </v-container>
</template>

<script>
import "@/assets/css/components/Home/signup.scss";
import SERVER from "@/api/spring";
import axios from "axios";

export default {
  name: "Signup",
  watch: {
    menu(val) {
      val && setTimeout(() => (this.$refs.picker.activePicker = "YEAR"));
    },
  },
  methods: {
    save(date) {
      this.$refs.menu.save(date);
    },
    checkId() {
      const URL = SERVER.URL + SERVER.ROUTES.idvalidity;
      const data = {
        id: this.signupData.userId,
      };
      axios.post(URL, data, null).then((res) => {
        console.log(res);
      });
      // if (this.signupData.userId) {
      //   alert("사용가능한 아이디입니다.");
      // } else {
      //   this.signupData.userId = "";
      //   alert("중복된 아이디입니다. 새로운 아이디를 입력해주세요.");
      // }
    },
  },
  data() {
    return {
      rulescheck: {
        required: (value) => !!value || "입력해주세요.",
        match: (v) => v === this.password || "일치하지 않습니다.",
      },
      rules: {
        required: (value) => !!value || "입력해주세요.",
        min: (v) => v.length >= 8 || "최소 8자로 입력해주세요.",
      },
      password: "",
      show1: false,
      birthCalendar: false,
      signupData: {
        userId: "",
        password: "",
        birth: null,
        gender: "",
      },
    };
  },
};
</script>

<style>
</style>