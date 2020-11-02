package com.ssafy.SMDM.dto;

import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
@Data
public class DailyProductPK implements Serializable {
    @Column(name="user_id")
    private String userid;

    @Column(name="category_id")
    private String categoryid;

    @Override
    public boolean equals(Object  o){
        return true;
    }

    @Override
    public int hashCode(){
        return 0;
    }
}
