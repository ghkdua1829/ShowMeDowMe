package com.ssafy.SMDM.service;

import com.ssafy.SMDM.dto.DailyProduct;
import com.ssafy.SMDM.repository.DailyProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DailyProductService {
    @Autowired
    private DailyProductRepository dailyProductRepository;

    //id와 categoryid를 통해 찾기
    public Optional<DailyProduct> findByUseridAndCategoryid(String UserId,String CategoryId){
        Optional<DailyProduct> dailyProduct1 = Optional.ofNullable(
                dailyProductRepository.findByUseridAndCategoryid(UserId,CategoryId)
        );
        return dailyProduct1;
    }

    //update date
    public void updateDate(Optional<DailyProduct> d, String date){
        if(d.isPresent()){
            d.get().setDate(date);
            dailyProductRepository.save(d.get());
        }
    }

    //delete
    @Transactional
    public void deleteByUseridAndCategoryid(String UserId,String CategoryId){
        dailyProductRepository.deleteByUseridAndCategoryid(UserId,CategoryId);
    }

    //add product by ids
    public DailyProduct addproduct(String UserId, String CategoryID,String Date){

        //현재시간으로 초기설정?

        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        java.util.Date time = new Date();
        String time1 = format1.format(time);

        DailyProduct d = new DailyProduct();
        d.setUserid(UserId);
        d.setCategoryid(CategoryID);
        d.setDate(time1);
        dailyProductRepository.save(d);
        return d;
    }

    //read lists
    public List<String> findByUserid(String UserId){
        List<String> list = new ArrayList<>();
        list = dailyProductRepository.findByUser(UserId);
        return list;
    }
}
