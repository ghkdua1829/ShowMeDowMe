<template>
  <div>
    쇼핑 준비화면
    <PreparationEnvironment />
    <PreparationMemo />
  </div>
</template>

<script>
import PreparationEnvironment from "@/components/Preparation/PreparationEnvironment";
import PreparationMemo from "@/components/Preparation/PreparationMemo";

export default {
  name: "Preparation",
  components: {
    PreparationEnvironment,
    PreparationMemo,
  },
};
</script>

<style>
</style>