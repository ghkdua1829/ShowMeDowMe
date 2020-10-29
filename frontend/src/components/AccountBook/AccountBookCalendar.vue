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

export default {
  name: "AccountBookCalendar",
  mounted() {
    this.$refs.calendar.checkChange();
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
        const sdates = this.event_data[ev_n]["sDate"];
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
        const eventname = this.event_data[ev_n]["expense"];

        events.push({
          pk_num: Number(ev_n),
          name: eventname,
          start: first,
          end: second,
          color: this.colors[Number(this.event_data[ev_n]["grade"])],
          details: event_data_detail["shopList"],
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
        "deep-purple",
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
      names: [
        "Meeting",
        "Holiday",
        "PTO",
        "Travel",
        "Event",
        "Birthday",
        "Conference",
        "Party",
      ],
      event_data: [
        {
          grade: "1",
          expense: "19,000",
          shopList: "test1",
          sDate: "202010300923",
        },
        {
          grade: "2",
          expense: "23,000",
          shopList: "test2",
          sDate: "202010211001",
        },
      ],
    };
  },
};
</script>

<style>
</style>