package com.ssafy.SMDM.controller;

import com.ssafy.SMDM.dto.Calendar;
import com.ssafy.SMDM.dto.DailyProduct;
import com.ssafy.SMDM.dto.User;
import com.ssafy.SMDM.service.CalendarService;
import com.ssafy.SMDM.service.DailyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
    @Autowired
    CalendarService calendarService;

    @PostMapping
    public Object saveCalendar(@RequestBody Map<String,String> t) {
        Calendar calendar= new Calendar();
        String Date1 = t.get("date");
        calendar.setReceiptdate(Date1);
        calendar.setShoppinglist(t.get("shoppinglist"));
        calendar.setUserid(t.get("userId"));
        calendar.setMoney(t.get("money"));
//        Optional<Calendar> u = calendarService.findByUserId(t.get("userid"));
        int grade=calendarService.updateGrade(t.get("timecheck"),t.get("moneycheck"));
        calendar.setGrade(grade);
        return new ResponseEntity<Calendar>(calendarService.saveCalendar(calendar),HttpStatus.OK);
    }

    @GetMapping
    public Object getCalendarByMonth(@RequestBody Map<String,String> t){
        List<Calendar> list = calendarService.searchMonthReceiptdate(t.get("userId"),t.get("date"));
        return new ResponseEntity<List>(list,HttpStatus.OK);
    }



    @GetMapping("/detail")
    public Object GetCalendar(@RequestBody Map<String,String> t) {
        Optional<Calendar> u = calendarService.findByReceiptdateAndUserid(t.get("date"),t.get("userId"));
        return new ResponseEntity<Calendar>(u.get(),HttpStatus.OK);
    }

}
