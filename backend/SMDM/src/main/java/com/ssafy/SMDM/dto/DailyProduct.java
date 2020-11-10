package com.ssafy.SMDM.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity(name="daily_products")
@Data
@NoArgsConstructor
public class DailyProduct {

    @Id
    @Column(name="pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(name="user_id")
    private String userid;

    @Column(name="category_id")
    private String categoryid;

    @Column(name="date")
    private String date;

    @Column(name="period")
    private Integer period;

    @Column(name="alarm")
    private Integer alarm;

    @Column(name="alarm2")
    private Integer alarm2;

    @Builder
    public DailyProduct(Integer pid,String userid,String categoryid,String date,Integer period,Integer alarm,Integer alarm2){
        this.pid = pid;
        this.userid = userid;
        this.categoryid = categoryid;
        this.date = date;
        this.period = period;
        this.alarm = alarm;
        this.alarm2 = alarm2;
    }
}
