package com.ssafy.SMDM.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="category")
@Data
@Builder
@NoArgsConstructor
public class Category {
    @Id
    @Column(name="category_id")
    private String categoryid;

    @Column(name = "period")
    private Integer period;

    @Builder
    public Category(String categoryid,Integer period){
        this.categoryid = categoryid;
        this.period = period;
    }
}
