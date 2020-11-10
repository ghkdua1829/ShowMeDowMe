package com.ssafy.SMDM.service;

import com.ssafy.SMDM.dto.Calendar;
import com.ssafy.SMDM.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

//    public List<Calendar> findByUserId(String userId){
//        Optional<Calendar> Calendar1 =Optional.ofNullable(
//                calendarRepository.findByUserid(userId)
//        );
//        return Calendar1;
//    }

    //receiptdate를 통해 찾기
    public List<Calendar> findByReceiptdateAndUserid(String year, String month, String userId) {
        List<Calendar> Calendar1 = calendarRepository.findByReceiptdateAndUserid(year, month, userId);
        return Calendar1;
    }

    // 달별로 영수증 뽑기
    public List<Calendar> searchMonthReceiptdate(String userId, Date receiptdate) {
        List<Calendar> Calendar1 = calendarRepository.findByUseridAndReceiptdate(userId, receiptdate);
        return Calendar1;
    }

    //time, money를 통해 grade 결정
    public int updateGrade(String timecheck, String moneycheck) {
        if (timecheck.equals('T') && moneycheck.equals('T')) {
            return 1;
        } else if ((timecheck.equals('F') && moneycheck.equals('T')) || (timecheck.equals('T') && moneycheck.equals('F'))) {
            return 2;
        } else if (timecheck.equals('F') && moneycheck.equals('T')) {
            return 3;
        } else {
            if (moneycheck.equals('T') || timecheck.equals('T')) {
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
    public void deleteByReceiptDate(Date receiptdate) {
        calendarRepository.deleteByReceiptdate(receiptdate);
    }

}
