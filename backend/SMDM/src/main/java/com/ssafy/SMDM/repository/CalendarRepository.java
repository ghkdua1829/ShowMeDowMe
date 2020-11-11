package com.ssafy.SMDM.repository;

import com.ssafy.SMDM.dto.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("Calendar")
public interface CalendarRepository extends JpaRepository<Calendar, String> {
    @Query("SELECT a FROM calendar AS a WHERE SUBSTRING(a.receiptdate,1,4) = :year and SUBSTRING(a.receiptdate,6,2) = :month and SUBSTRING(a.receiptdate,9,2)=:day and a.userid =:userId")
    public List<Calendar> findByReceiptdateAndUserid(String year,String month,String day, String userId);

    @Query("SELECT a  FROM calendar AS a WHERE SUBSTRING(a.receiptdate,1,4) = :year and SUBSTRING(a.receiptdate,6,2) = :month and SUBSTRING(a.receiptdate,9,2)=:day and SUBSTRING(a.receiptdate,12,2)=:hour and SUBSTRING(a.receiptdate,15,2)=:min and a.userid =:userId")
    public Calendar searchdelete(String year, String month,String day,String hour,String min ,String userId);

    public void deleteByReceiptdate(String receiptdate);

    public Calendar findByUserid(String userId);

    @Query("SELECT a FROM calendar AS a WHERE SUBSTRING(a.receiptdate,1,4) = :year and SUBSTRING(a.receiptdate,6,2) = :month and a.userid =:userId")
    public List<Calendar> findByUseridAndReceiptdate(String year, String month, String userId);
}
