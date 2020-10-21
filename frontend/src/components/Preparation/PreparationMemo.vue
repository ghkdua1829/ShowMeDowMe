<template>
  <div class="preparationMemo">
    <div class="memo">
      <h1>메모장</h1>
      <div class="text-center">
        <v-btn
          v-if="!chip1 && !chip2 && !chip3 && !chip4"
          close
          color="primary"
          dark
          @click="
            (chip1 = true), (chip2 = true), (chip3 = true), (chip4 = true)
          "
        >
          Reset Chips
        </v-btn>

        <v-chip v-if="chip1" class="ma-2" close @click:close="chip1 = false">
          Closable
        </v-chip>

        <v-chip
          v-if="chip2"
          class="ma-2"
          close
          color="red"
          text-color="white"
          @click:close="chip2 = false"
        >
          Remove
        </v-chip>

        <v-chip
          v-if="chip3"
          class="ma-2"
          close
          color="green"
          outlined
          @click:close="chip3 = false"
        >
          Success
        </v-chip>

        <v-chip
          v-if="chip4"
          class="ma-2"
          close
          color="orange"
          label
          outlined
          @click:close="chip4 = false"
        >
          Complete
        </v-chip>
      </div>
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
    remove(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },
  },
  data() {
    return {
      chip1: true,
      chip2: true,
      chip3: true,
      chip4: true,
    };
  },
};
</script>

<style>
</style>