import axios from "axios";
import router from "@/router/index";
import SERVER from "@/api/spring";

export default {
  postuserData({ commit }, info) {
      axios
        .post(info.location, info.data)
        .then((res) => {
          // sessionStorage에 유저의 정보를 저장
          if (res.data.userid) {     
            commit("SET_USERID", res.data.userid);
            sessionStorage.setItem("userid", res.data.userid);
          } else {
            commit("SET_USERID", res.data);
            sessionStorage.setItem("userid", res.data);
          }
          router.push({ path: "/perparation" });
        })
        .catch(() => alert("아이디 또는 비밀번호가 틀렸습니다"));
  },
  signup({ dispatch }, signupData) {
      const info = {
        location: SERVER.URL + SERVER.ROUTES.users,
        data: signupData,
      };
      dispatch("postuserData", info);
  },
  logout({ commit }) {
    commit("SET_USERID", null);
    alert("로그아웃 되었습니다.")
    sessionStorage.removeItem("userid");
    router.push({path: "/"})
  },
  login({ dispatch }, loginData) {
    const info = {
      location: SERVER.URL + SERVER.ROUTES.login,
      data: loginData,
    };
    dispatch("postuserData", info);
  },
  setShop({ commit }, setData) {
    commit("SET_ENVIRONMENT", setData);
    router.push({ path: "/shop" });
  },
  saveCameraData({ commit }, saveData) {
    commit("SAVE_SHOPDATA", saveData)
    router.push({ path: "/shop" });
  },
  editShopItem({ commit }, editData, editIndex) {
    const editInfo = {
      editData: editData,
      editIndex: editIndex
    }
    commit("EDIT_SHOPDATA", editInfo)
  }
  // save
}