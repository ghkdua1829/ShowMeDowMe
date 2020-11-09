package com.ssafy.SMDM.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Builder
    public DailyProduct(Integer pid,String userid,String categoryid,String date){
        this.pid = pid;
        this.userid = userid;
        this.categoryid = categoryid;
        this.date = date;
    }
}
