<template>
  <div class="preparationMemo">
    <div class="memo">
      <h1>메모장</h1>
      <div v-for="(memo, index) in memoList" :key="index">
        <v-chip
          :color="`${colors[nonce - 1]} lighten-3`"
          close
          @click:close="deleteMemo(index)"
          >{{ memo }}</v-chip
        >
      </div>
      {{ memoList }}
    </div>
    <Navigation />
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
  methods: {
    deleteMemo(index) {
      console.log(index);
      this.memoList.splice(index, 1);
    },
  },
  data() {
    return {
      memoList: ["렌즈세정액", "우유", "당근"],
      colors: ["green", "purple", "indigo", "cyan", "teal", "orange"],
    };
  },
};
</script>
