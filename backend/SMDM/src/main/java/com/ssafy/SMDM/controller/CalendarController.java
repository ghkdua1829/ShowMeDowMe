 package com.ssafy.SMDM.controller;

import com.ssafy.SMDM.dto.Calendar;
import com.ssafy.SMDM.service.CalendarService;
import com.ssafy.SMDM.service.DailyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController

@RequestMapping("/api/calendar")
public class CalendarController {
    @Autowired
    CalendarService calendarService;

    @Autowired
    DailyProductService dailyProductService;

    @PostMapping
    public Object saveCalendar(@RequestBody Map<String, String> t) {
        Calendar calendar = new Calendar();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date time = new Date();
        String time1 = format1.format(time);
        calendar.setReceiptdate(time1);
        calendar.setShoppinglist(t.get("shoppinglist"));
        String []s = t.get("shoppinglist").split(",");
        for(String i : s){
            dailyProductService.updateDate(t.get("userid"),i);
        }
        calendar.setUserid(t.get("userId"));
        calendar.setMoney(Integer.parseInt(t.get("money")));
//        Optional<Calendar> u = calendarService.findByUserId(t.get("userid"));
        System.out.println(t.get("timecheck")+' '+t.get("moneycheck"));
        int grade = calendarService.updateGrade(t.get("timecheck"), t.get("moneycheck"));
        calendar.setGrade(grade);
        calendarService.saveCalendar(calendar);
        return new ResponseEntity<Calendar>(calendarService.saveCalendar(calendar), HttpStatus.OK);
    }

    @PostMapping("/date/{date}")
    public Object getCalendarByMonth(@RequestBody Map<String, String> t , @PathVariable String date) {
        try {
            List<Calendar> list = calendarService.searchMonthReceiptdate(date.substring(0,4), date.substring(4,6),t.get("userId"));

            return new ResponseEntity<List>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/detail/{date}")
    public Object getCalendarByDay(@RequestBody Map<String, String> t, @PathVariable String date) {
        try {
            List<Calendar> list = calendarService.searchDayReceiptdate(date.substring(0,4), date.substring(4,6),date.substring(6,8), t.get("userId"));

            return new ResponseEntity<List>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{date}")
    public Object deleteCalendar(@RequestBody Map<String,String> t, @PathVariable String date){
//        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
//            Date time1 = format1.parse(t.get("date"));
            Calendar calendar2 = calendarService.searchDelete(date.substring(0,4), date.substring(4,6),date.substring(6,8),date.substring(8,10),date.substring(10,12), t.get("userId"));
            calendarService.deleteByReceiptdate(calendar2.getReceiptdate());
            return new ResponseEntity<List>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
