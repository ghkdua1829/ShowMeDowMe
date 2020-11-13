export default {
    SET_USERID(state, userid) {
        state.userid = userid
    },
    SET_ENVIRONMENT(state, setData) {
        state.aimTime = setData.value
        state.aimExpense = setData.amount
        state.isSkipTime = setData.isSkipTime
        state.isSkipMoney = setData.isSkipMoney
        if (state.isSkipMoney) {
            state.check.moneycheck = "X"
        } else {
            state.check.moneycheck = "T"
        }
        if (state.isSkipTime) {
            state.check.timecheck = "X"
        } else {
            state.check.timecheck = "T"
        }
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
        if ((state.isSkipMoney === false) && (state.nowExpense > state.aimExpense) && (state.check.moneycheck === "T")) {
            alert("현재 목표 금액을 초과했습니다.")
            state.check.moneycheck = "F"
        }
    },
    ADD_USER_SHOPDATA(state, editItem) {
        state.shoppingList.push(editItem)
        // console.log(,editItem)
    },
    FAIL_TIME(state) {
        state.check.timecheck = "F"
    },
    SAVE_GRADE(state, grade) {
        state.reportGrade = grade
    },
    REMOVE_SHOPDATA(state) {
        state.aimTime= 0,
        state.aimExpense= 0,
        state.isSkipTime= false,
        state.isSkipMoney= false,
        state.shoppingList= [],
        state.recentItem= [],
        state.nowExpense= 0,
        state.check.moneycheck = "",
        state.check.timecheck= "",
        state.reportGrade= 0
    }
}