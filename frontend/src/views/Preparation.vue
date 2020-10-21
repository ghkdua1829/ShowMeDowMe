<template>
  <div>
    <PreparationEnvironment />
    <PreparationMemo />
    <Navigation />
  </div>
</template>

<script>
import Navigation from "@/components/Navigation";
import PreparationEnvironment from "@/components/Preparation/PreparationEnvironment";
import PreparationMemo from "@/components/Preparation/PreparationMemo";

export default {
  name: "Preparation",
  components: {
    PreparationEnvironment,
    PreparationMemo,
    Navigation,
  },
};
</script>

<style>
</style>