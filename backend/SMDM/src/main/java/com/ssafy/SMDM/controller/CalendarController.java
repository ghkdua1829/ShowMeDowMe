 package com.ssafy.SMDM.controller;

import com.ssafy.SMDM.dto.Calendar;
import com.ssafy.SMDM.service.CalendarService;
import com.ssafy.SMDM.service.DailyProductService;
import com.ssafy.SMDM.service.NaverShoppingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

 @CrossOrigin
@RestController

@RequestMapping("/api/calendar")
public class CalendarController {
    @Autowired
    CalendarService calendarService;

    @Autowired
    DailyProductService dailyProductService;

    @Autowired
    NaverShoppingService naverShoppingService;
    @PostMapping
    public Object saveCalendar(@RequestBody Map<String, String> t) {
        Calendar calendar = new Calendar();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date time = new Date();
        String time1 = format1.format(time);
        calendar.setReceiptdate(time1);
        calendar.setShoppinglist(t.get("shoppinglist"));
        String []s = t.get("shoppinglist").split(",");
        String dump = "";
        for (int i = 0; i<s.length; i+=2){
            List<String> li = naverShoppingService.findCategory(s[i]);
            dailyProductService.updateDate(t.get("userid"),li.get(1));
            dump=dump+li.get(0)+","+s[i+1]+",";
        }
        calendar.setDumpshoppinglist(dump);
        calendar.setUserid(t.get("userId"));
        calendar.setMoney(Integer.parseInt(t.get("money")));
        int grade = calendarService.updateGrade(t.get("timecheck"), t.get("moneycheck"));
        calendar.setGrade(grade);
        calendarService.saveCalendar(calendar);
        return new ResponseEntity<Calendar>(calendarService.saveCalendar(calendar), HttpStatus.OK);
    }


    @PostMapping("/date/{date}")
    public Object getCalendarByMonth(@RequestBody Map<String, String> t , @PathVariable String date) {
        try {
            Map<String,Integer> buyByMonth = new HashMap<String, Integer>();
            List<Calendar> list = calendarService.searchMonthReceiptdate(date.substring(0,4), date.substring(4,6),t.get("userId"));
            return new ResponseEntity<List>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }




    @PostMapping("/date/analyze/{date}")
    public Object analyzeMonth(@RequestBody Map<String, String> t , @PathVariable String date) {
        try {
            Map<String,Integer> buyByMonth = new HashMap<String, Integer>();
            List<Calendar> list = calendarService.searchMonthReceiptdate(date.substring(0,4), date.substring(4,6),t.get("userId"));
            for(Calendar c : list){
                String []s = c.getDumpshoppinglist().split(",");
                for (int i = 0; i<s.length; i+=2){
                    if(buyByMonth.containsKey(s[i])){
                        Integer total=buyByMonth.get(s[i])+Integer.parseInt(s[i+1]);
                        buyByMonth.put(s[i],total);
                    }else{
                        Integer total = Integer.parseInt(s[i+1]);
                        buyByMonth.put(s[i],total);
                    }
                }
            }
            Integer result = 0;
            for (String key : buyByMonth.keySet()){
                result += buyByMonth.get(key);
            }
            double result1 = (double) result;
            Map<String,String> buyByMonth1 = new HashMap<String, String>();
            for ( String key : buyByMonth.keySet()){
                buyByMonth1.put(key,String.format("%.2f",(buyByMonth.get(key)/result1)*100));
            }
            return new ResponseEntity( buyByMonth1, HttpStatus.OK);
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
