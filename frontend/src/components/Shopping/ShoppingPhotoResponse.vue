<template>
  <div class="photo-response">
    <h1>이 제품을 담으셨나요?</h1>
    <h2>상품명</h2>
    {{ productName }}
    <h2>가격</h2>
    {{ productPrice }}
    <v-row justify="center">
      <v-dialog v-model="dialog" scrollable max-width="300px">
        <template v-slot:activator="{ on, attrs }">
          <v-btn class="mr-2" color="primary lighten-2" @click="plusProduct()"
            >네
            <v-icon> mdi-emoticon-happy-outline </v-icon>
          </v-btn>
          <v-btn color="primary darken-2" v-bind="attrs" v-on="on">
            아니요 <v-icon>mdi-emoticon-confused-outline</v-icon>
          </v-btn>
        </template>
        <v-card>
          <v-card-title>올바른 제품 이름을 선택해주세요.</v-card-title>
          <v-divider></v-divider>
          <v-card-text style="height: 300px">
            <v-radio-group v-model="dialogm1" column>
              <div v-for="(name, idx) in nameList" :key="idx">
                <v-radio
                  :label="name"
                  :value="name"
                  @click="changeProduct(name)"
                ></v-radio>
              </div>
              <!-- <v-radio label="Bahrain" value="bahrain"></v-radio>
              <v-radio label="Bangladesh" value="bangladesh"></v-radio>
              <v-radio label="Barbados" value="barbados"></v-radio>
              <v-radio label="Belarus" value="belarus"></v-radio>
              <v-radio label="Belgium" value="belgium"></v-radio>
              <v-radio label="Belize" value="belize"></v-radio>
              <v-radio label="Benin" value="benin"></v-radio>
              <v-radio label="Bhutan" value="bhutan"></v-radio>
              <v-radio label="Bolivia" value="bolivia"></v-radio>
              <v-radio label="Bosnia and Herzegovina" value="bosnia"></v-radio>
              <v-radio label="Botswana" value="botswana"></v-radio>
              <v-radio label="Brazil" value="brazil"></v-radio>
              <v-radio label="Brunei" value="brunei"></v-radio>
              <v-radio label="Bulgaria" value="bulgaria"></v-radio>
              <v-radio label="Burkina Faso" value="burkina"></v-radio>
              <v-radio label="Burma" value="burma"></v-radio>
              <v-radio label="Burundi" value="burundi"></v-radio> -->
            </v-radio-group>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-btn text @click="dialog = false"> 닫기 </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import "@/assets/css/components/Shopping/shoppingPhotoResponse.scss";

export default {
  name: "ShoppingPhotoResponse",
  methods: {
    plusProduct() {
      this.$router.push({ path: "/shop" });
    },
    changeProduct(name) {
      this.productName = name;
      this.$router.push({ path: "/shop" });
    },
  },
  data() {
    return {
      productName: "test",
      productPrice: 0,
      dialogm1: "",
      dialog: false,
      nameList: ["사과", "국산"],
    };
  },
};
</script>

<style>
</style>