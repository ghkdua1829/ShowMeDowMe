// package com.ssafy.SMDM.repository;

// import com.ssafy.SMDM.dto.Calendar;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import java.time.DateTimeException;
// import java.util.Date;

// @Repository("Calendar")
// public interface CalendarRepository extends JpaRepository<Calendar,String>{
//     public Calendar findByReceiptDate(Date receiptdate);
//     public void deleteByReceiptdate(Date receiptdate);
//     public Calendar findByUserid(String userId);
// }