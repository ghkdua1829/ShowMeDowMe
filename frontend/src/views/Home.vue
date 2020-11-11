<template>
  <div>
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-icon class="info-btn" v-bind="attrs" v-on="on"
          >mdi-alert-circle-outline</v-icon
        >
      </template>

      <v-card>
        <v-card-title class="headline"> <h4>쇼우미도우미란?</h4> </v-card-title>
        <v-img
          src="https://user-images.githubusercontent.com/60081201/98492722-15163c80-227c-11eb-80d7-8fa9e375e98d.PNG"
        />
        <v-divider></v-divider>
        <h2 class="mt-3 mb-3">소개</h2>
        <v-img
          src="https://user-images.githubusercontent.com/60081201/98491324-d088a200-2277-11eb-9c93-f52b27e4c565.PNG"
          alt="Show me 도우미는 직역하면 “도우미 보여줘”라는 의미로 장 보는 사람들을 위한 도우미의 역할을 톡톡히 할 것을 보여준다는 포부를 의미합니다. "
        />
        <v-divider class="mt-3 mb-3"></v-divider>
        <h2 class="mt-3 mb-3">핵심 기능 3가지</h2>
        <v-img
          src="https://user-images.githubusercontent.com/60081201/98494354-23665780-2280-11eb-8d3d-f9a54a489057.PNG"
          alt="쇼우미 도우미 핵심기능 설명 1. 합리적인 소비생활 2. 자동 가계부 작성 및 분석 기능 3. 구입이 필요한 생필품 알람기능"
        />

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="
              dialog = false;
              $router.push({ name: 'Signup' });
            "
            >회원가입하러가기</v-btn
          >
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-img
      src="https://user-images.githubusercontent.com/60081201/96660569-e562cb80-1384-11eb-9b19-107de574a592.PNG"
      alt="쇼우미도우미 로고"
    />
    <div class="text-center homepage">
      <div>
        <v-btn
          rounded
          dark
          color="teal lighten-2"
          @click="$router.push({ name: 'Login' })"
          >로그인</v-btn
        >
      </div>
      <div>
        <v-btn
          rounded
          depressed
          outlined
          color="teal"
          @click="$router.push({ path: '/nonMember/perparation' })"
        >
          비회원
        </v-btn>
      </div>
      <div @click="$router.push({ name: 'Signup' })">회원가입 하러가기</div>
    </div>
  </div>
</template>

<script>
import "@/assets/css/views/home.scss";

export default {
  name: "Home",
  data: () => ({
    dialog: false,
  }),
};
</script>
