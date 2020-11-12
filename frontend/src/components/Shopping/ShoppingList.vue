<template>
  <v-data-table
    :headers="headers"
    :items="shoppingList"
    sort-by="value"
    class="elevation-1"
    :mobile-breakpoint="0"
  >
    <template v-slot:top>
      <v-toolbar flat>
        <v-divider class="mx-4" inset vertical></v-divider>
        <v-spacer></v-spacer>
        <v-dialog v-model="dialog" max-width="500px">
          <template v-slot:activator="{ on, attrs }">
            <v-btn class="mb-2" v-bind="attrs" v-on="on" outlined color="teal"
              >직접추가하기
            </v-btn>
          </template>
          <v-card>
            <v-card-title>
              <span class="headline">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col cols="4">
                    <v-text-field
                      v-model="editedItem.name"
                      label="제품명"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="4">
                    <v-text-field
                      v-model="editedItem.amount"
                      label="수량"
                    ></v-text-field>
                  </v-col>
                  <v-col cols="4">
                    <v-text-field
                      v-model="editedItem.price"
                      label="가격"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn text @click="close"> 닫기 </v-btn>
              <v-btn text @click="save"> 저장 </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title>구매 목록에서 제거할까요?</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn text @click="closeDelete">취소</v-btn>
              <v-btn text @click="deleteItemConfirm">확인</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon small class="mr-2" @click="editItem(item)"> mdi-pencil </v-icon>
      <v-icon small @click="deleteItem(item)"> mdi-delete </v-icon>
    </template>
  </v-data-table>
</template>

<script>
import { mapState } from "vuex";
import { mapActions } from "vuex";

export default {
  name: "ShoppingList",

  computed: {
    ...mapState(["shoppingList"]),
    formTitle() {
      return this.editedIndex === -1 ? "구입할 제품" : "등록된 제품 수정";
    },
  },
  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogDelete(val) {
      val || this.closeDelete();
    },
  },

  methods: {
    ...mapActions(["editShopItem"]),
    editItem(item) {
      this.editedIndex = this.shoppingList.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      this.editedIndex = this.shoppingList.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialogDelete = true;
    },

    deleteItemConfirm() {
      this.purchasedThings.splice(this.editedIndex, 1);
      this.closeDelete();
    },

    close() {
      this.dialog = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    closeDelete() {
      this.dialogDelete = false;
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      });
    },

    save() {
      if (this.editedIndex > -1) {
        // this.editShopItem(
        //   Object.assign(this.shoppingList[this.editedIndex], this.e0ditedItem),
        //   this.editedIndex
        // );
        Object.assign(this.shoppingList[this.editedIndex], this.editedItem);
        // console.log(
        //   Object.assign(this.shoppingList[this.editedIndex], this.editedItem)
        // );
        console.log("dddddd");
      } else {
        this.shoppingList.push(this.editedItem);
      }
      this.close();
    },
  },
  data: () => ({
    dialog: false,
    dialogDelete: false,
    headers: [
      {
        text: "제품",
        align: "start",
        sortable: false,
        value: "name",
      },
      { text: "수량", value: "amount" },
      { text: "가격", value: "price" },
      { text: "편집", value: "actions", sortable: false },
    ],
    purchasedThings: [],
    editedIndex: -1,
    editedItem: {},
    defaultItem: {
      name: "",
      amount: 0,
      price: 0,
    },
  }),
};
</script>

<style></style>
