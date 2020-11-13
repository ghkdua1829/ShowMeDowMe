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
        state.shoppingList.push(state.recentItem)
    },
    // EDIT_SHOPDATA(state, editInfo) {
    //     state.shoppingList[editInfo.editIndex] = editInfo.editData
    //     console.log("dhkte", editInfo.editIndex, state.shoppingList)
    // }
}