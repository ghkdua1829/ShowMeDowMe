package com.ssafy.SMDM.repository;

import com.ssafy.SMDM.dto.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("Calendar")
public interface CalendarRepository extends JpaRepository<Calendar, String> {
    @Query("SELECT a FROM Calendar AS a WHERE EXTRACT(YEAR FROM a.receiptdate)=: EXTRACT(YEAR FROM receiptdate) AND EXTRACT(MONTH FROM a.receiptdate)=:EXTRACT(MONTH FROM receiptdate) AND EXTRACT(DAY FROM a.receiptdate)=:EXTRACT(DAY FROM receiptdate) AND a.userid = :userId")
    public List<Calendar> findByReceiptdateAndUserid(Date receiptdate, String userId);

    public void deleteByReceiptdate(Date receiptdate);

    public Calendar findByUserid(String userId);

    //    @Query("SELECT a FROM Calendar AS a WHERE YEAR(a.receiptdate)=:YEAR(receiptdate) AND MONTH(a.receiptdate)=:MONTH(receiptdate) AND a.userid = :userId")
    public List<Calendar> findByUseridAndReceiptdate(String userId, Date receiptdate);
}
