import axios from "axios";
import router from "@/router/index";
import SERVER from "@/api/spring";

export default {
    postuserData({ commit }, info) {
        axios
          .post(info.location, info.data)
          .then((res) => {
            commit("SET_USERID", res.data.userid);
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
}