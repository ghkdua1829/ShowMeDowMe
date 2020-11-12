<template>
  <div class="accountBook-calendar">
    <v-row>
      <v-col>
        <v-btn fab text small color="grey darken-2" @click="prev">
          <v-icon small> mdi-chevron-left </v-icon>
        </v-btn>
      </v-col>
      <v-col cols="7">
        <v-toolbar-title v-if="$refs.calendar" class="calendar-date">
          {{ $refs.calendar.title }}
        </v-toolbar-title>
      </v-col>
      <v-col>
        <v-btn fab text small color="grey darken-2" @click="next">
          <v-icon small> mdi-chevron-right </v-icon>
        </v-btn>
      </v-col>
    </v-row>
    <v-row class="fill-height">
      <v-col>
        <v-sheet height="64">
          <v-toolbar flat>
            <v-btn
              outlined
              class="mr-4"
              color="grey darken-2"
              @click="setToday"
            >
              Today
            </v-btn>
            <v-spacer></v-spacer>
          </v-toolbar>
        </v-sheet>
        <v-sheet height="600">
          <v-calendar
            ref="calendar"
            v-model="focus"
            color="primary"
            :events="events"
            :event-color="getEventColor"
            :type="type"
            @click:event="showEvent"
            @click:more="viewDay"
            @click:date="viewDay"
            @change="updateRange"
          ></v-calendar>
          <v-menu
            v-model="selectedOpen"
            :close-on-content-click="false"
            :activator="selectedElement"
            offset-x
          >
            <v-card color="grey lighten-4" min-width="350px" flat>
              <v-toolbar :color="selectedEvent.color" dark>
                <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <span v-html="selectedEvent.details"></span>
              </v-card-text>
              <v-card-actions>
                <v-btn text color="secondary" @click="selectedOpen = false">
                  Cancel
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-menu>
        </v-sheet>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import "@/assets/css/components/AccountBook/accountBookCalendar.scss";
import SERVER from "@/api/spring";
import axios from "axios";
// import vueMoment from 'vue-moment'

export default {
  name: "AccountBookCalendar",
  mounted() {
    this.$refs.calendar.checkChange();
  },
  created() {
    let todate = new Date();
    let month = todate.getUTCMonth() + 1;
    let year = todate.getUTCFullYear();
    // console.log("date : " + year + month);
    const DATE = year + "" + month;
    const URL = SERVER.URL + SERVER.ROUTES.getCalendar + "/date/" + DATE;

    axios.post(URL, {
      userId : sessionStorage.userid
    })
    .then((res) => {
      this.event_data = res.data;
      // this.event_data = [
      //   {
      //     grade: "1",
      //     money: "19,000",
      //     shoppinglist: "test1",
      //     sDate: "2020-11-09 13:43:03.0",
      //   },
      //   {
      //     grade: "2",
      //     money: "23,000",
      //     shoppinglist: "test2",
      //     sDate: "2020-11-13 13:43:03.0",
      //   },
      // ],
      // console.log(this.event_data)
      console.log(res)
      // console.log(this.billList)
    })
    .catch((err) => {
      console.err(err)
    })

  },
  methods: {
    getEventColor(event) {
      return event.color;
    },
    setToday() {
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        setTimeout(() => {
          this.selectedOpen = true;
        }, 10);
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        setTimeout(open, 10);
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },
    updateRange() {
      const events = [];
      for (let ev_n in this.event_data) {
        const event_data_detail = this.event_data[ev_n];
        let sdates = this.event_data[ev_n]["receiptdate"];
        sdates = sdates.replace(/[^0-9]/g,'');
        const syear = sdates.substring(0, 4);
        const smonth = sdates.substring(4, 6);
        const sday = sdates.substring(6, 8);
        const shour = sdates.substring(8, 10);
        const sminute = sdates.substring(10, 12);
        console.log("test")
        const s_date =
          syear +
          "/" +
          smonth +
          "/" +
          sday +
          "/" +
          shour +
          ":" +
          sminute +
          ":00";

        const first = new Date(s_date);
        const second = new Date(s_date);
        const eventname = this.event_data[ev_n]["money"];

        events.push({
          pk_num: Number(ev_n),
          name: eventname,
          start: first,
          end: second,
          color: this.colors[Number(this.event_data[ev_n]["grade"])],
          details: event_data_detail["shoppinglist"],
        });
      }
      this.events = events;
    },
    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
  },
  data() {
    return {
      focus: "",
      type: "month",
      selectedEvent: {},
      selectedElement: null,
      selectedOpen: false,
      events: [],
      colors: [
        "blue",
        "indigo",
        "purple",
        "cyan",
        "green",
        "orange",
        "grey darken-1",
      ],
      eventcategory: {
        1: "greate",
        2: "good",
        3: "general",
        4: "bad",
        5: "worst",
      },
      billList: [],
      event_data: [
      //   {
      //     grade: "1",
      //     money: "19,000",
      //     shoppinglist: "test1",
      //     receiptdate: "2020-11-09 13:43:03.0",
      //   },
      //   {
      //     grade: "2",
      //     money: "23,000",
      //     shoppinglist: "test2",
      //     receiptdate: "2020-11-13 13:43:03.0",
      //   },
      ],
    };
  },
};
</script>

<style>
</style>