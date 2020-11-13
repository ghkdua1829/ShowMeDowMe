package com.ssafy.SMDM.service;

import com.ssafy.SMDM.dto.DailyProduct;
import com.ssafy.SMDM.repository.CategoryRepository;
import com.ssafy.SMDM.repository.DailyProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DailyProductService {
    @Autowired
    private DailyProductRepository dailyProductRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NaverShoppingService naverShoppingService;

    //id와 categoryid를 통해 찾기
    public Optional<DailyProduct> findByUseridAndCategoryid(String UserId,String CategoryId){
        Optional<DailyProduct> dailyProduct1 = Optional.ofNullable(
                dailyProductRepository.findByUseridAndCategoryid(UserId,CategoryId)
        );
        return dailyProduct1;
    }

    //update date
    public void updateDate(String Userid, String CategoryId){

        Optional<DailyProduct> d = Optional.ofNullable(dailyProductRepository.findByUseridAndCategoryid(Userid, CategoryId));
        if(d.isPresent()){
            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
            java.util.Date time = new Date();
            String time1 = format1.format(time);
            String time2 = d.get().getDate();
            d.get().setDate(time1);
            try {
                Date date1 = format1.parse(time1);
                Date date2 = format1.parse(time2);
                long calDate = date1.getTime() - date2.getTime();
                long calDateDays = calDate / ( 24*60*60*1000);
                d.get().setPeriod((int)calDateDays);
            }catch (ParseException e){
            }
            dailyProductRepository.save(d.get());
        }
    }

    //delete
    @Transactional
    public void deleteByUseridAndCategoryid(String UserId,String CategoryId){
        Optional<DailyProduct> d = Optional.ofNullable(dailyProductRepository.findByUseridAndCategoryid(UserId, CategoryId));
        if(d.isPresent())
            dailyProductRepository.deleteByUseridAndCategoryid(UserId,CategoryId);
    }

    //add product by ids
    public String addproduct(String UserId, String CategoryID){

        //현재시간으로 초기설정?

        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        java.util.Date time = new Date();
        String time1 = format1.format(time);


        Optional<DailyProduct> d = Optional.ofNullable((dailyProductRepository.findByUseridAndCategoryid(UserId, CategoryID)));
        if(d.isPresent()) {
            d.get().setUserid(UserId);
            d.get().setCategoryid(CategoryID);
            d.get().setAlarm(0);
            d.get().setAlarm2(1);
            dailyProductRepository.save(d.get());
        }
        else{
            DailyProduct d2 = new DailyProduct();
            d2.setUserid(UserId);
            d2.setCategoryid(CategoryID);
            d2.setDate(time1);
            //system alarm
            Integer n = categoryService.findByCategoryId(CategoryID);
            d2.setPeriod(n);
            d2.setAlarm(0);
            //user alarm
            d2.setAlarm2(1);
            dailyProductRepository.save(d2);
        }
        return "1";
    }

    //read lists
    public List<String> findByUser(String UserId){
        List<String> list = new ArrayList<>();
        list = dailyProductRepository.findByUser(UserId);
        return list;
    }

    //조회
    public List<DailyProduct> findByUserid(String UserId){
        List<DailyProduct> d = dailyProductRepository.findByUserid(UserId);
        return d;
    }

    //알람바꾸기
    public void updateAlarm(String UserId, String CategoryID, Integer alarm){
        Optional<DailyProduct> d = Optional.ofNullable(dailyProductRepository.findByUseridAndCategoryid(UserId, CategoryID));
        if(d.isPresent()){
            d.get().setAlarm(alarm);
            dailyProductRepository.save(d.get());
        }
    }

    //알람바꾸기
    public void updateAlarm2(String UserId, String CategoryID, Integer alarm){
        Optional<DailyProduct> d = Optional.ofNullable(dailyProductRepository.findByUseridAndCategoryid(UserId, CategoryID));
        if(d.isPresent()){
            d.get().setAlarm2(alarm);
            dailyProductRepository.save(d.get());
        }
    }
}
