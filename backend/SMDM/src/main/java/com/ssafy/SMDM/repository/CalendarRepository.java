package com.ssafy.SMDM.repository;

import com.ssafy.SMDM.dto.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("Calendar")
public interface CalendarRepository extends JpaRepository<Calendar, String> {
    @Query("SELECT a FROM calendar AS a WHERE SUBSTRING(a.receiptdate,1,4) = :year and SUBSTRING(a.receiptdate,6,2) = :month and a.userid =:userId")
    public List<Calendar> findByReceiptdateAndUserid(String year,String month, String userId);

    public void deleteByReceiptdate(Date receiptdate);

    public Calendar findByUserid(String userId);

    //    @Query("SELECT a FROM Calendar AS a WHERE YEAR(a.receiptdate)=:YEAR(receiptdate) AND MONTH(a.receiptdate)=:MONTH(receiptdate) AND a.userid = :userId")
    public List<Calendar> findByUseridAndReceiptdate(String userId, Date receiptdate);
}
