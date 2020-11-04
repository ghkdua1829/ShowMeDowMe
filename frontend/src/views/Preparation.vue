<template>
  <div>
    <PreparationEnvironment />
    <span @click="$router.push({ path: '/memo' })"
      ><v-icon large style="text-align: left">
        mdi-file-document-edit-outline</v-icon
      >
      <h4>구매 목록 메모</h4>
      <!-- {{ userid }} -->
    </span>
    <Navigation />
  </div>
</template>

<script>
import Navigation from "@/components/Navigation";
import PreparationEnvironment from "@/components/Preparation/PreparationEnvironment";
import { mapState } from "vuex";
export default {
  name: "Preparation",
  components: {
    PreparationEnvironment,
    Navigation,
  },
  computed: {
    ...mapState(["userid"]),
  },
};
</script>

<style>
</style>