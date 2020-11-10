package com.ssafy.SMDM.repository;

import com.ssafy.SMDM.dto.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DateTimeException;
import java.util.Date;
import java.util.List;

@Repository("Calendar")
public interface CalendarRepository extends JpaRepository<Calendar,String>{
    @Query("SELECT a FROM Calendar AS a WHERE a.YEAR(receiptdate)=:YEAR(Date) AND a.MONTH(receiptdate)=:MONTH(Date) AND a.DAY(receiptdate)=:a.DAY(Date)")
    public Calendar findByReceiptdateAndUserid(Date receiptdate,String userId);

    public void deleteByReceiptdate(Date receiptdate);
    public Calendar findByUserid(String userId);

    @Query("SELECT a FROM Calendar AS a WHERE a.YEAR(receiptdate)=:YEAR(Date) AND a.MONTH(receiptdate)=:MONTH(Date)")
    public List<Calendar> findByUseridAndReceiptdate(String userId, Date receiptdate);
}
