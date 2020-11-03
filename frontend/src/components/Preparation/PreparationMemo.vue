<template>
  <div class="preparationMemo">
    <div class="memo">
      <span v-if="modalCheck" class="back-btn">
        <v-icon @click="$router.push({ path: '/perparation' })">
          mdi-arrow-left
        </v-icon>
      </span>
      <h1>메모장</h1>
      <v-text-field
        label="장보기 목록에 추가하기"
        hide-details="auto"
        class="mb-2"
        v-model="memoInput"
        @keyup.enter="plusMemo()"
        @keyup.space="plusMemo()"
      ></v-text-field>
      <span v-for="(memo, index) in memoList" :key="index">
        <v-chip
          class="ma-2"
          :color="`${colors[index % 6]} lighten-3`"
          close
          @click:close="deleteMemo(index)"
          >{{ memo }}</v-chip
        >
      </span>
    </div>
    <div v-if="modalCheck">
      <Navigation />
    </div>
  </div>
</template>

<script>
import Navigation from "@/components/Navigation";
import "@/assets/css/components/Preparation/preparationMemo.scss";

export default {
  name: "PreparationMemo",
  components: {
    Navigation,
  },
  created() {
    if (this.$route.name.name === "PreparationMemo") {
      this.modalCheck = true;
    } else {
      this.modalCheck = false;
    }
  },
  methods: {
    deleteMemo(index) {
      this.memoList.splice(index, 1);
    },
    plusMemo() {
      if (this.memoInput !== " ") {
        if (this.memoList.includes(this.memoInput) === false) {
          this.memoList.push(this.memoInput);
        }
        this.memoInput = "";
      } else {
        alert("추가할 물품을 입력해주세요.");
      }
    },
  },
  data() {
    return {
      memoInput: "",
      memoList: ["렌즈세정액", "우유", "당근"], // 사용자가 미리 작성한 리스트
      colors: ["green", "purple", "indigo", "cyan", "teal", "orange"],
      modalCheck: false,
    };
  },
};
</script>
