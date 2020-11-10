// package com.ssafy.SMDM.service;

// import com.ssafy.SMDM.dto.Calendar;
// import com.ssafy.SMDM.dto.DailyProduct;
// import com.ssafy.SMDM.repository.CalendarRepository;
// import com.ssafy.SMDM.repository.DailyProductRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import javax.transaction.Transactional;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class CalendarService {
//     @Autowired
//     private CalendarRepository calendarRepository;

//     public Optional<Calendar> findByUserId(String userId){
//         Optional<Calendar> Calendar1 =Optional.ofNullable(
//                 calendarRepository.findByUserid(userId)
//         );
//         return Calendar1;
//     }


//     //receiptdate를 통해 찾기
//     public Optional<Calendar> findByReceiptDate(Date receiptdate){
//         Optional<Calendar> Calendar1 = Optional.ofNullable(
//                 calendarRepository.findByReceiptDate(receiptdate)
//         );
//         return Calendar1;
//     }

//     //time, money를 통해 grade 결정
//     public int updateGrade( Integer timecheck, Integer moneycheck){
//         if (timecheck == 1 && moneycheck == 1) {
//             return 1;
//         } else if ((timecheck == 2 && moneycheck == 1) || (timecheck == 1 && moneycheck == 2)) {
//             return 2;
//         } else if (timecheck == 2 && moneycheck == 2) {
//             return 3;
//         } else {
//             if (timecheck == 1 || moneycheck == 1) {
//                 return 1;
//             } else {
//                 return 3;
//             }
//         }
//     }

//     public Calendar saveCalendar(Calendar calendar){
//         calendarRepository.save(calendar);
//         return calendar;
//     }

//     //delete
//     @Transactional
//     public void deleteByReceiptDate(Date receiptdate){
//         calendarRepository.deleteByReceiptdate(receiptdate);
//     }

// }
