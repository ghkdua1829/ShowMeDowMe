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
      <v-card>
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
          <v-btn color="primary">변경하기</v-btn>
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
            </v-list-item-content>
          </v-list-item>
          <v-btn color="red lighten-2" dark>회원 탈퇴</v-btn>
        </v-list>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
  name: "MypageUpdate",
  methods: {
    checkSamePW() {
      if (this.afterPW == this.beforePW) {
        alert("새로운 비밀번호를 입력해주세요.");
      }
    },
  },
  data() {
    return {
      dialog: false,
      beforePW: "",
      afterPW: "",
      checkPW: "",
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