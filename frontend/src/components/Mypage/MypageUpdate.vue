<template>
  <div>
    <v-dialog
      v-model="dialog"
      fullscreen
      hide-overlay
      transition="dialog-bottom-transition"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn v-bind="attrs" v-on="on" outlined> 개인 정보 설정 </v-btn>
      </template>
      <v-card class="mypageUpdate">
        <v-toolbar dark color="primary">
          <v-toolbar-title>
            <v-icon>mdi-wrench</v-icon> 개인 정보 설정</v-toolbar-title
          >
          <v-spacer></v-spacer>
          <v-btn icon dark @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-toolbar>
        <v-list three-line subheader>
          <v-subheader>회원 정보 변경</v-subheader>
          <v-list-item>
            <v-list-item-content>
              <h2>비밀번호 변경</h2>
              <v-list-item-subtitle>
                <v-text-field
                  v-model="beforePW"
                  name="input-10-1"
                  label="현재 비밀번호"
                  type="password"
                ></v-text-field>
                <v-text-field
                  v-model="afterPW"
                  :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                  :rules="[rules.required, rules.min]"
                  :type="show1 ? 'text' : 'password'"
                  name="input-10-1"
                  label="새로운 비밀번호"
                  hint="최소 8자로 입력해주세요."
                  @click:append="show1 = !show1"
                  @keyup="checkSamePW()"
                ></v-text-field>
                <v-text-field
                  v-model="checkPWfdsf"
                  :rules="[rulescheck.required, rulescheck.match]"
                  name="input-10-1"
                  label="새비밀번호 확인"
                  type="password"
                ></v-text-field>
              </v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-btn class="update-btn" rounded color="primary" @click="changePW()"
            >변경하기</v-btn
          >
        </v-list>
        <v-divider></v-divider>
        <v-list three-line subheader>
          <v-subheader>회원 탈퇴</v-subheader>
          <v-list-item>
            <v-list-item-content>
              <h3>앞으로 벌어질 일</h3>
              <v-list-item-subtitle>
                <div>회원님의 가계부 정보가 사라지게 됩니다.</div>
                <div>회원님이 정말 보고싶을 겁니다.</div>
              </v-list-item-subtitle>
              <h5 class="mt-3">현재 비밀번호 입력 후</h5>
              <h5>회원 탈퇴 버튼을 누르면 탈퇴가 완료됩니다.</h5>
              <v-text-field
                v-model="deletePW"
                name="input-10-1"
                label="현재 비밀번호"
                type="password"
              ></v-text-field>
            </v-list-item-content>
          </v-list-item>
          <v-btn
            class="delete-btn"
            :disabled="!deletePW"
            color="red lighten-2"
            rounded
            @click="deleteUser()"
            >회원 탈퇴</v-btn
          >
        </v-list>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import "@/assets/css/components/Mypage/mypageUpdate.scss";
import SERVER from "@/api/spring";
import axios from "axios";

export default {
  name: "MypageUpdate",
  methods: {
    checkSamePW() {
      if (this.afterPW == this.beforePW) {
        alert("새로운 비밀번호를 입력해주세요.");
      }
    },
    deleteUser() {
      const checkURL = SERVER.URL + SERVER.ROUTES.login;
      const data = {
        userid: sessionStorage.userid,
        userpw: this.deletePW,
      };
      axios
        .post(checkURL, data)
        .then((res) => {
          if (res.status === 200) {
            const URL =
              SERVER.URL +
              SERVER.ROUTES.users +
              "?userid=" +
              sessionStorage.userid;
            axios
              .delete(URL)
              .then((res) => {
                if (res.status === 200) {
                  alert("탈퇴가 완료되었습니다.");
                  sessionStorage.removeItem("userid");
                  this.$router.push({ path: "/" });
                }
              })
              .catch((err) => {
                console.err(err);
              });
          }
        })
        .catch(() => {
          alert("비밀번호를 올바르게 입력해주세요.");
          this.deletePW = "";
        });
    },
    changePW() {
      const changeURL = SERVER.URL + SERVER.ROUTES.update;
      const updateData = {
        userid: sessionStorage.userid,
        userpw: this.beforePW,
        updatepw: this.afterPW,
      };
      axios
        .post(changeURL, updateData)
        .then((res) => {
          if (res.status === 200) {
            alert("비밀번호 수정이 완료되었습니다.");
            this.$router.push({ path: "/mypage" });
          }
        })
        .catch(() => {
          alert("비밀번호를 확인해주세요.");
          this.beforePW = "";
          this.afterPW = "";
        });
    },
  },
  data() {
    return {
      dialog: false,
      beforePW: "",
      afterPW: "",
      checkPW: "",
      deletePW: "",
      show1: false,
      rulescheck: {
        required: (value) => !!value || "입력해주세요.",
        match: (v) => v === this.afterPW || "일치하지 않습니다.",
      },
      rules: {
        required: (value) => !!value || "입력해주세요.",
        min: (v) => v.length >= 8 || "최소 8자로 입력해주세요.",
      },
    };
  },
};
</script>

<style>
</style>