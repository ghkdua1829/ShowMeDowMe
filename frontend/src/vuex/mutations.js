import axios from "axios";
import SERVER from "@/api/spring";
export default {
    SET_USERID(state, userid) {
        state.userid = userid
    },
    SET_ENVIRONMENT(state, setData) {
        state.aimTime = setData.value
        state.aimExpense = setData.amount
    },
    SAVE_SHOPDATA(state, saveData) { // 카메라로 찍은 쇼핑 데이터 저장
        state.recentItem = saveData
        state.shoppingList.push(state.recentItem)
    },
    EDIT_SHOPDATA(state, editInfo) { // 입력된 쇼핑 데이터 수정
        // console.log(editInfo)
        // console.log("before", state.shoppingList)
        state.shoppingList[editInfo.editIndex] = editInfo.editData
        // console.log("after", state.shoppingList)

    },
    SUM_PRICE(state) { // 현재 결제 금액 계산
        state.nowExpense = 0
        for (let i = 0; i < state.shoppingList.length; i++) {
            state.nowExpense = parseInt(state.nowExpense) + (parseInt(state.shoppingList[i].price) * parseInt(state.shoppingList[i].amount))
        }
    },
    ADD_USER_SHOPDATA(state, editItem) {
        state.shoppingList.push(editItem)
        // console.log(,editItem)
    },
    COMPLETE_SHOPPING(state, timeout) {
        if (timeout) {
            state.check.timecheck = "F"
        } else if (state.aimTime !== 0) {
            state.check.timecheck = "T"
        }
        const URL = SERVER.URL + SERVER.ROUTES.saveShopData
        const sendData = {
            userId : sessionStorage.userId,
            money : state.nowExpense,
            moneycheck : state.check.moneycheck,
            timecheck : state.check.timecheck,
            shoppingList : state.shoppingList // string으로 고치기
        }
        axios.post(URL, sendData)
            .then((res) => {
                console.log(res)
                this.$router.push({ path: "/report" });
            })
    }
}