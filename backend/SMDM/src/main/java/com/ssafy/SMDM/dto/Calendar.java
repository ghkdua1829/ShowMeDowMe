package com.ssafy.SMDM.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

@Entity(name="calendar")
@Data
@NoArgsConstructor
public class Calendar {
    @Id
    @Column(name="receipt_date")
    private Date receiptdate;

    @Column(name="user_Id")
    private String userid;

    @Column(name="shopping_list")
    private String shoppinglist;

    @Column(name="money")
    private Integer money;

    @Column(name="grade")
    private Integer grade;

    @Builder
    public Calendar(Date receiptDate, String userId, String shoppingList, Integer grade) {
        receiptdate = receiptDate;
        userid = userId;
        shoppinglist = shoppingList;
        money = money;
        this.grade = grade;

    }

}
