<template>
  <div>
    <div>회원 전용 페이지</div>
    <router-link to="/accountbook">가계부</router-link> |
    <router-link to="/shop/preparation">장보기 준비 화면</router-link> |
    <router-link to="/mypage">마이페이지</router-link>
  </div>
</template>

<script>
export default {
  name: "User",
};
</script>

<style>
</style>