<template>
  <div>
    <h2 style="text-align:center;">목표시간</h2>
  <v-form>
    <v-container>
      <v-row>
        <v-col
          cols="6"
          sm="6"
        >
          <v-text-field
            v-model="first"
            label="시간"
            outlined
          ></v-text-field>
        </v-col>

        <v-col
          cols="6"
          sm="6"
        >
          <v-text-field
            v-model="last"
            label="분"
            outlined
          ></v-text-field>
        </v-col>
      </v-row>
    </v-container>
  </v-form>

    <h2 style="text-align:center;">목표금액</h2>
      <v-col class = 'd-flex'
          cols="11"
          sm="6"
          md="4"
        >
          <v-text-field
            label="금액을 입력하세요."
            outlined
            dense
          ></v-text-field>
            <div class = "d-flex xx-large ml-2 " >원</div>
        </v-col>
    <router-link :to="{ path: '/shop' }">
      <div class="text-center">
        <v-btn
          rounded
          color="primary"
          dark
        >
          장보러 가기
        </v-btn>
     </div>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "PreparationEnvironment",
};
</script>

<style>
.xx-large{
  font-size: 2em;
}
</style>