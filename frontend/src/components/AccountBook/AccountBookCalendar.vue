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
                <h2>결제 금액 {{ selectedEvent.name }}</h2>
              </v-toolbar>
              <v-card-text>
                {{ gradeImgae[selectedEvent.grade] }}
                <v-img :src="gradeImgae[selectedEvent.grade]" />
                <small>이모티콘 </small>
                <v-btn color="error lighten-2" @click="deleteBill()"
                  >삭제</v-btn
                >
                <v-simple-table>
                  <template v-slot:default>
                    <thead>
                      <tr>
                        <th class="text-left">제품명</th>
                        <th class="text-left">가격</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr
                        v-for="(deatil, index) in selectedEvent.details"
                        :key="index"
                      >
                        <td>{{ deatil.name }}</td>
                        <td>{{ deatil.price }}</td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
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
import { mapState } from "vuex";

export default {
  name: "AccountBookCalendar",
  mounted() {
    this.$refs.calendar.checkChange();
  },
  created() {
    let todate = new Date();
    const DATE = this.dateToString(todate).substring(0, 6);
    const URL = SERVER.URL + SERVER.ROUTES.getCalendar + "/date/" + DATE;

    axios
      .post(URL, {
        userId: sessionStorage.userid,
      })
      .then((res) => {
        this.event_data = res.data;
        this.updateRange();
      })
      .catch((err) => {
        console.err(err);
      });
  },
  computed: {
    ...mapState(["gradeImgae"]),
  },
  methods: {
    dateToString(date) {
      const year = date.getUTCFullYear();
      let month = date.getUTCMonth() + 1;
      let day = date.getDate();
      let hours = date.getHours();
      let minutes = date.getMinutes();
      month = month > 9 ? month : "0" + month;
      day = day > 9 ? day : "0" + day;
      hours = hours > 9 ? hours : "0" + hours;
      minutes = minutes > 9 ? minutes : "0" + minutes;

      return year + "" + month + "" + day + "" + hours + "" + minutes;
    },

    deleteBill() {
      const thisDate = this.selectedEvent.end;
      const stringDate = this.dateToString(thisDate);
      const URL = SERVER.URL + SERVER.ROUTES.getCalendar + "/" + stringDate;

      axios
        .delete(URL, {
          data: {
            userId: sessionStorage.userid,
          },
        })
        .then(() => {
          alert("삭제되었습니다.");
          this.selectedOpen = false;
          let todate = new Date();
          const DATE = this.dateToString(todate).substring(0, 6);
          const URL = SERVER.URL + SERVER.ROUTES.getCalendar + "/date/" + DATE;

          axios
            .post(URL, {
              userId: sessionStorage.userid,
            })
            .then((res) => {
              this.event_data = res.data;
              this.updateRange();
            })
            .catch((err) => {
              console.err(err);
            });
        })
        .catch((err) => {
          alert("죄송합니다. 시스템 오류입니다.");
          console.err(err);
        });
    },
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
      this.selectedEvent = event;
      let splitList = this.selectedEvent.details.split(",");
      let tableList = [];
      for (let i = 0; i < splitList.length; i += 2) {
        tableList.push({ name: splitList[i], price: splitList[i + 1] });
      }
      this.selectedEvent.details = tableList;
      const open = () => {
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
        sdates = sdates.replace(/[^0-9]/g, "");
        const syear = sdates.substring(0, 4);
        const smonth = sdates.substring(4, 6);
        const sday = sdates.substring(6, 8);
        const shour = sdates.substring(8, 10);
        const sminute = sdates.substring(10, 12);
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
          name: String(eventname),
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
      event_data: [],
    };
  },
};
</script>

<style>
</style>