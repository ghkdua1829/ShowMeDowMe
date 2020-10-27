<template>
  <div>
    <v-row justify="center">
      <div @click.stop="dialog = true">
        <v-icon>mdi-clipboard-outline</v-icon>
      </div>

      <v-dialog v-model="dialog" max-width="290">
        <v-card>
          <PreparationMemo />

          <v-btn class="close-btn" text @click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import PreparationMemo from "@/components/Preparation/PreparationMemo";

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