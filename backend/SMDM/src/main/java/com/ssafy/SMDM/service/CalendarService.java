package com.ssafy.SMDM.service;// package com.ssafy.SMDM.service;

import com.ssafy.SMDM.dto.Calendar;
import com.ssafy.SMDM.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.desktop.SystemSleepEvent;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
 public class CalendarService {
     @Autowired
     private CalendarRepository calendarRepository;


    //receiptdate를 통해 찾기
    public List<Calendar> searchDayReceiptdate(String year, String month,String day, String userId) {
        List<Calendar> Calendar1 = calendarRepository.findByReceiptdateAndUserid(year, month,day, userId);
        return Calendar1;
    }

    // 달별로 영수증 뽑기
    public List<Calendar> searchMonthReceiptdate(String year, String month, String userId) {
        List<Calendar> Calendar1 = calendarRepository.findByUseridAndReceiptdate(year,month,userId);
        return Calendar1;
    }

    //time, money를 통해 grade 결정
    public int updateGrade(String timecheck, String moneycheck) {
        if (timecheck.equals("T") && moneycheck.equals("T")) {
            return 1;
        } else if ((timecheck.equals("F") && moneycheck.equals("T")) || (timecheck.equals("T") && moneycheck.equals("F"))) {
            return 2;
        } else if (timecheck.equals("F") && moneycheck.equals("F")) {
            return 3;
        } else if (timecheck.equals("X") && moneycheck.equals("X")){
            return 1;
        }
        else {
            if (moneycheck.equals("T") || timecheck.equals("T")) {
                return 1;
            } else {
                return 3;
            }
        }
    }

    public Calendar saveCalendar(Calendar calendar) {
        calendarRepository.save(calendar);
        return calendar;
    }

    //delete
    @Transactional
    public void deleteByReceiptdate(String receiptdate){
        calendarRepository.deleteByReceiptdate(receiptdate);
    }



    public Calendar searchDelete(String year, String month,String day,String hour,String min ,String userId) {
        Calendar calendar1 = calendarRepository.searchdelete(year,month,day,hour,min,userId);
        return calendar1;
    }

}
