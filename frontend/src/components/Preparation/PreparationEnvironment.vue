<template>
  <div>
    <h2 style="text-align: center">목표시간</h2>
    <v-form>
      <v-container>
        <v-row>
          <v-col cols="6" sm="6">
            <v-text-field type="text" 
          oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" v-model="hour" label="시간" outlined></v-text-field>
          </v-col>

          <v-col cols="6" sm="6">
            <v-text-field type="text" 
          oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" v-model="minute" label="분" outlined></v-text-field>
          </v-col>
          <v-btn depressed> skip </v-btn>
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
          type="text" 
          oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
            v-model="amount"
            label="금액을 입력하세요."
            outlined
            dense
          ></v-text-field>
            <h1>원</h1>
      </v-col>
      <v-btn depressed>
        skip
      </v-btn>
    <v-btn
      class="ma-2 m-3 0 4"
      outlined
      color="indigo"
      @click="$router.push({ path: '/shop' })"
    >
      <h2>장보러 가기</h2>
    </v-btn>
  </div>
</template>

<script>
// const BACK_URL = ""
export default {
  name: "PreparationEnvironment",

  data() {
		return{
      number : "",
      hour:"",
      minute:"",
		}
	},
	watch:{
		hour : function(){
			return this.hour = this.hour.replace(/[^0-9]/g, ''); 
    },
    minute : function(){
			return this.minute = this.minute.replace(/[^0-9]/g, '');  
    }
	}
};
</script>
