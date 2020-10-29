<template>
  <div>
    <v-row justify="center">
      <div @click.stop="dialog = true">
        <v-icon>mdi-clipboard-outline</v-icon>
      </div>

      <v-dialog v-model="dialog" max-width="290">
        <v-card>
          <PreparationMemo />

          <div class="close-btn" @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </div>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import PreparationMemo from "@/components/Preparation/PreparationMemo";
import "@/assets/css/components/Shopping/shoppingMemo.scss";

export default {
  name: "ShoppingMemo",
  components: {
    PreparationMemo,
  },
  data() {
    return {
      dialog: false,
    };
  },
};
</script>

<style>
</style>