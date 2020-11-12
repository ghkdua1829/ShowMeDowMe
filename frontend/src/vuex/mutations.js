export default {
    SET_USERID(state, userid) {
        state.userid = userid
    },
    SET_ENVIRONMENT(state, setData) {
        state.aimTime = setData.value
        state.aimExpense = setData.amount
    },
    SAVE_SHOPDATA(state, saveData) {
        state.recentItem = saveData
        console.log(state.recentItem.price, state.recentItem)
        state.shoppingList.push(state.recentItem)
        // state.nowExpense = 0
        // for (let i = 0; i < state.shoppingList.length; i++) {
        //     state.nowExpense = parseInt(state.nowExpense) + parseInt(state.shoppingList[i].price)
        // }
    },
    EDIT_SHOPDATA(state, editInfo) {
        state.shoppingList[editInfo.editIndex] = editInfo.editData
        // this.SUM_PRICE()
        // state.nowExpense = 0
        // for (let i = 0; i < state.shoppingList.length; i++) {
        //     state.nowExpense = parseInt(state.nowExpense) + parseInt(state.shoppingList[i].price)
        // }
        // console.log("dhkte", editInfo.editIndex, state.shoppingList)
    },
    SUM_PRICE(state) {
        console.log("왓다")
        state.nowExpense = 0
        for (let i = 0; i < state.shoppingList.length; i++) {
            state.nowExpense = parseInt(state.nowExpense) + parseInt(state.shoppingList[i].price)
        }
    }
}