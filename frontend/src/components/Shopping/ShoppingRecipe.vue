<template>
  <div>
    쇼핑 레시피 확인
    <v-app>
      <v-content>
        <v-container>
          <h1>Fixed columns in v-data-table demo</h1>
          <p>
            Note: fixed columns and grouping tables below are added only in
            <a target="_blank" href="https://github.com/lzhoucs/vuetify"
              >my vuetify folk</a
            >, which is published on
            <a
              target="_blank"
              href="https://www.npmjs.com/package/@lzhoucs/vuetify"
              >npm</a
            >. Also checkout
            <a target="_blank" href="https://codepen.io/lzhoucs/pen/aadaJx"
              >more detailed grouping table demo</a
            >
          </p>

          <div class="section">
            <h2>0. Regular Table(without fixed columns)</h2>
            <v-data-table
              :headers="headers0"
              :items="desserts"
              item-key="name"
              v-model="selected0"
              fixed-header
              select-all
            >
              <template slot="items" slot-scope="props">
                <tr>
                  <td>
                    <v-checkbox
                      v-model="props.selected"
                      hide-details
                    ></v-checkbox>
                  </td>
                  <td>{{ props.item.name }}</td>
                  <td>{{ props.item.calories }}</td>
                  <td>{{ props.item.fat }}</td>
                  <td>{{ props.item.carbs }}</td>
                  <td>{{ props.item.protein }}</td>
                  <td>{{ props.item.nprotein }}</td>
                  <td>{{ props.item.iron }}</td>
                  <td>{{ props.item.niron }}</td>
                </tr>
              </template>
            </v-data-table>
          </div>

          <div class="section">
            <h2>1. Regular Table with fixed columns</h2>
            <p>
              Fixed columns applied onto a regular table. grouping and
              checkboxes are not involved.
            </p>
            <v-data-table :headers="headers" :items="desserts" item-key="name">
              <template slot="items" slot-scope="props">
                <tr>
                  <td>{{ props.item.name }}</td>
                  <td>{{ props.item.calories }}</td>
                  <td>{{ props.item.fat }}</td>
                  <td>{{ props.item.carbs }}</td>
                  <td>{{ props.item.protein }}</td>
                  <td>{{ props.item.nprotein }}</td>
                  <td>{{ props.item.iron }}</td>
                  <td>{{ props.item.niron }}</td>
                </tr>
              </template>
            </v-data-table>
          </div>

          <div class="section">
            <h2>2. Selection(checkbox) Table with fixed columns</h2>
            <p>
              Fixed column applied onto a regular table with a checkbox column.
              No select all header. It expects an empty header with width to be
              specified which is used to render a regular empty header for
              checkbox column:
              <code
                >{ text: '', fixed: true, width: '100px', sortable: false
                }</code
              >. Note: <code>sortable:false</code> is required
            </p>
            <v-data-table
              :headers="headers2"
              :items="desserts"
              item-key="name"
              v-model="selected2"
            >
              <template slot="items" slot-scope="props">
                <tr>
                  <td>
                    <v-checkbox
                      v-model="props.selected"
                      hide-details
                    ></v-checkbox>
                  </td>
                  <td>{{ props.item.name }}</td>
                  <td>{{ props.item.calories }}</td>
                  <td>{{ props.item.fat }}</td>
                  <td>{{ props.item.carbs }}</td>
                  <td>{{ props.item.protein }}</td>
                  <td>{{ props.item.nprotein }}</td>
                  <td>{{ props.item.iron }}</td>
                  <td>{{ props.item.niron }}</td>
                </tr>
              </template>
            </v-data-table>
          </div>

          <div class="section">
            <h2>
              3. Selection(checkbox) Table with fixed columns and select all
            </h2>
            <p>
              Fixed column applied onto a regular table with a checkbox column.
              Has select all header. Similar to #2, it also expects an empty
              header with width to be specified which is used to render a
              'select all' header for checkbox column:
              <code>{ text: '', fixed: true, width: '100px'}</code>. Note:
              <code>sortable:false</code> is no longer required
            </p>
            <v-data-table
              :headers="headers3"
              :items="desserts"
              item-key="name"
              v-model="selected3"
              select-all
            >
              <template slot="items" slot-scope="props">
                <tr>
                  <td>
                    <v-checkbox
                      v-model="props.selected"
                      hide-details
                    ></v-checkbox>
                  </td>
                  <td>{{ props.item.name }}</td>
                  <td>{{ props.item.calories }}</td>
                  <td>{{ props.item.fat }}</td>
                  <td>{{ props.item.carbs }}</td>
                  <td>{{ props.item.protein }}</td>
                  <td>{{ props.item.nprotein }}</td>
                  <td>{{ props.item.iron }}</td>
                  <td>{{ props.item.niron }}</td>
                </tr>
              </template>
            </v-data-table>
          </div>

          <div class="section">
            <h2>4. Grouping Table with fixed columns</h2>
            <v-data-table
              :headers="headers"
              :items="desserts"
              item-key="name"
              group-key="category"
            >
              <template slot="group" slot-scope="props">
                <span class="font-weight-bold">
                  Group {{ props.groupIndex + 1 }} - {{ props.groupName }}
                </span>
              </template>

              <template slot="items" slot-scope="props">
                <tr>
                  <td>{{ props.item.name }}</td>
                  <td>{{ props.item.calories }}</td>
                  <td>{{ props.item.fat }}</td>
                  <td>{{ props.item.carbs }}</td>
                  <td>{{ props.item.protein }}</td>
                  <td>{{ props.item.nprotein }}</td>
                  <td>{{ props.item.iron }}</td>
                  <td>{{ props.item.niron }}</td>
                </tr>
              </template>
            </v-data-table>
          </div>

          <div class="section">
            <h2>5. Grouping Table with fixed columns and select all</h2>
            <v-data-table
              :headers="headers5"
              :items="desserts"
              item-key="name"
              group-key="category"
              v-model="selected5"
              select-all
            >
              <template slot="group" slot-scope="props">
                <span class="font-weight-bold">
                  Group {{ props.groupIndex + 1 }} - {{ props.groupName }}
                </span>
              </template>

              <template slot="items" slot-scope="props">
                <tr>
                  <td>
                    <v-checkbox
                      v-model="props.selected"
                      hide-details
                    ></v-checkbox>
                  </td>
                  <td>{{ props.item.name }}</td>
                  <td>{{ props.item.calories }}</td>
                  <td>{{ props.item.fat }}</td>
                  <td>{{ props.item.carbs }}</td>
                  <td>{{ props.item.protein }}</td>
                  <td>{{ props.item.nprotein }}</td>
                  <td>{{ props.item.iron }}</td>
                  <td>{{ props.item.niron }}</td>
                </tr>
              </template>
            </v-data-table>
          </div>
        </v-container>
      </v-content>
    </v-app>
  </div>
</template>

<script>
export default {
  name: "ShoppingRecipe",
  data() {
    return {
      selected0: [],
      selected2: [],
      selected3: [],
      selected5: [],
      headers: [
        {
          text: "Dessert (100g serving)",
          value: "name",
          width: "200px",
          fixed: true,
        },
        { text: "Calories", value: "calories", width: "200px", fixed: true },
        { text: "Fat (g)", value: "fat", width: "300px" },
        { text: "Carbs (g)", value: "carbs", width: "300px" },
        { text: "Protein (g)", value: "protein", width: "300px" },
        { text: "New Protein (g)", value: "nprotein", width: "300px" },
        { text: "Iron (%)", value: "iron", width: "300px" },
        { text: "New Iron (%)", value: "niron", width: "300px" },
      ],
      desserts: [
        {
          value: false,
          name: "Orange Juice",
          category: "Beverage",
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0,
          nprotein: 6.0,
          iron: "7%",
          niron: "7%",
        },
        {
          value: false,
          name: "Larabar",
          category: "Snack",
          calories: 408,
          fat: 3.2,
          carbs: 87,
          protein: 6.5,
          nprotein: 6.5,
          iron: "45%",
          niron: "45%",
        },
        {
          value: false,
          name: "Donut",
          category: "Breakfast",
          calories: 452,
          fat: 25.0,
          carbs: 51,
          protein: 4.9,
          nprotein: 4.9,
          iron: "22%",
          niron: "22%",
        },

        {
          value: false,
          name: "Bagel",
          category: "Breakfast",
          calories: 999,
          fat: 28.0,
          carbs: 151,
          protein: 2.9,
          nprotein: 2.9,
          iron: "29%",
          niron: "29%",
        },
        {
          value: false,
          name: "KitKat",
          category: "Snack",
          calories: 518,
          fat: 26.0,
          carbs: 65,
          protein: 7,
          nprotein: 7,
          iron: "6%",
          niron: "6%",
        },
      ],
    };
  },
  computed: {
    headers0() {
      return this.headers.map((header) =>
        Object.assign({}, header, { fixed: false })
      );
    },
    headers2() {
      return [
        { text: "", fixed: true, width: "100px", sortable: false },
      ].concat(this.headers);
    },
    headers3() {
      return [{ text: "", fixed: true, width: "100px" }].concat(this.headers);
    },
    headers5() {
      return this.headers3;
    },
  },
};
</script>

<style>
table > thead > tr > th:nth-child(1) {
  position: sticky !important;
}
.section {
  margin-top: 50px;
}
.v-tablewidth {
  max-width: 100px;
}
</style>