package com.ssafy.SMDM.controller;

import com.ssafy.SMDM.dto.Calendar;
import com.ssafy.SMDM.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
    @Autowired
    CalendarService calendarService;

    @PostMapping
    public Object saveCalendar(@RequestBody Map<String, String> t) {
        Calendar calendar = new Calendar();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date time = new Date();
        String time1 = format1.format(time);
        calendar.setReceiptdate(time1);
        calendar.setShoppinglist(t.get("shoppinglist"));
        calendar.setUserid(t.get("userId"));
        calendar.setMoney(Integer.parseInt(t.get("money")));
//        Optional<Calendar> u = calendarService.findByUserId(t.get("userid"));
        int grade = calendarService.updateGrade(t.get("timecheck"), t.get("moneycheck"));
        calendar.setGrade(grade);
        return new ResponseEntity<Calendar>(calendarService.saveCalendar(calendar), HttpStatus.OK);
    }

    @GetMapping
    public Object getCalendarByMonth(@RequestBody Map<String, String> t) {
        Calendar calendar = new Calendar();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date time = new Date();
        try {
            Date time1 = format1.parse(t.get("date"));

            System.out.println(time1);
            List<Calendar> list = calendarService.searchMonthReceiptdate(t.get("userId"), time1);

            return new ResponseEntity<List>(list, HttpStatus.OK);
        } catch (ParseException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/detail")
    public Object GetCalendar(@RequestBody Map<String, String> t) {

        System.out.println();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date time1 = format1.parse(t.get("date"));

            System.out.println(time1 + " : " + t.get("userId"));
            List<Calendar> list = calendarService.findByReceiptdateAndUserid(String.valueOf(time1.getYear() + 1900), String.valueOf(time1.getMonth() + 1), t.get("userId"));

            return new ResponseEntity<List>(list, HttpStatus.OK);
        } catch (ParseException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
