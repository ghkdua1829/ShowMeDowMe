<template>
  <div class="preparationMemo">
    <div class="memo">
      <h1>메모장</h1>
        <v-container fluid>
          <v-row>
            <v-col cols="12">
              <v-combobox
                v-model="select"
                :list="list"
                label="구매할 목록"
                multiple
                chips
              ></v-combobox>
            </v-col>
          </v-row>
        </v-container>
        <v-container fluid>
        <v-combobox
          v-model="model"
          :filter="filter"
          :hide-no-data="!search"
          :items="items"
          :search-input.sync="search"
          hide-selected
          label="Search for an option"
          multiple
          small-chips
          solo
        >
          <template v-slot:no-data>
            <v-list-item>
              <span class="subheading">Create</span>
              <v-chip
                :color="`${colors[nonce - 1]} lighten-3`"
                label
                small
              >
                {{ search }}
              </v-chip>
            </v-list-item>
          </template>
          <template v-slot:selection="{ attrs, item, parent, selected }">
            <v-chip
              v-if="item === Object(item)"
              v-bind="attrs"
              :color="`${item.color} lighten-3`"
              :input-value="selected"
              label
              small
              
            >
              <span class="pr-2">
                {{ item.text }}
              </span>
              <v-icon
                small
                @click="parent.selectItem(item)"
              >
                mdi-close-circle-outline
              </v-icon>
            </v-chip>
          </template>
          <template v-slot:item="{ index, item }">
            <v-text-field
              v-if="editing === item"
              v-model="editing.text"
              autofocus
              flat
              background-color="transparent"
              hide-details
              solo
              @keyup.enter="edit(index, item)"
            ></v-text-field>
            <v-chip
              v-else
              :color="`${item.color} lighten-3`"
              dark
              label
              small
            >
              {{ item.text }}
            </v-chip>
            <v-spacer></v-spacer>
            <v-list-item-action @click.stop>
              <v-btn
                icon
                @click.stop.prevent="edit(index, item)"
              >
                <v-icon>{{ editing !== item ? 'mdi-pencil' : 'mdi-check' }}</v-icon>
              </v-btn>
            </v-list-item-action>
          </template>
        </v-combobox>
      </v-container>
    </div>
    <Navigation />
  </div>
</template>

<script>
import Navigation from "@/components/Navigation";
import "@/assets/css/components/Preparation/preparationMemo.scss";

export default {
  name: "PreparationMemo",
  components: {
    Navigation,
  },
  watch: {
      model (val, prev) {
        if (val.length === prev.length) return

        this.model = val.map(v => {
          if (typeof v === 'string') {
            v = {
              text: v,
              color: this.colors[this.nonce - 1],
            }

            this.items.push(v)

            this.nonce++
          }

          return v
        })
      },
    },
  methods: {
    remove(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },
    ///
    edit (index, item) {
        if (!this.editing) {
          this.editing = item
          this.editingIndex = index
        } else {
          this.editing = null
          this.editingIndex = -1
        }
      },
      filter (item, queryText, itemText) {
        if (item.header) return false

        const hasValue = val => val != null ? val : ''

        const text = hasValue(itemText)
        const query = hasValue(queryText)

        return text.toString()
          .toLowerCase()
          .indexOf(query.toString().toLowerCase()) > -1
      },
  },
  data() {
    return {
      select: ['당근', '렌즈세정액'],
      list: [
        '렌즈세정액',
        '휴지',
        '두부',
        '당근',
      ],
      ///
      activator: null,
      attach: null,
      colors: ['green', 'purple', 'indigo', 'cyan', 'teal', 'orange'],
      editing: null,
      editingIndex: -1,
      items: [
        { header: '이전 내역을 참고하세요.' },
        // {
        //   text: 'Foo',
        //   color: 'blue',
        // },
        // {
        //   text: 'Bar',
        //   color: 'red',
        // },
      ],
      nonce: 1,
      menu: false,
      model: [
        {
          text: 'Foo',
          color: 'blue',
        },
      ],
      x: 0,
      search: null,
      y: 0,
    };
  },
};
</script>

<style>
</style>