package com.ssafy.SMDM.service;

import com.ssafy.SMDM.dto.DailyProductPK;
import com.ssafy.SMDM.dto.DailyProduct;
import com.ssafy.SMDM.repository.DailyProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DailyProductService {
    @Autowired
    private DailyProductRepository dailyProductRepository;

    //id와 categoryid를 통해 찾기
    public Optional<DailyProduct> findByUseridAndCategoryid(String UserId,String CategoryId){
        DailyProductPK pk = new DailyProductPK();
        pk.setUserid(UserId);
        pk.setCategoryid(CategoryId);
        Optional<DailyProduct> dailyProduct = Optional.ofNullable(
                dailyProductRepository.findByDailProductPK(pk));
        return dailyProduct;
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
        DailyProductPK pk = new DailyProductPK();
        pk.setUserid(UserId);
        pk.setCategoryid(CategoryId);
        dailyProductRepository.deleteByDailProductPK(pk);
    }

    //add product by ids
    public DailyProduct addproduct(String UserId, String CategoryID,String Date){

        //현재시간으로 초기설정?
        /*
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        String time1 = format1.format(time);
         */
        DailyProduct d = new DailyProduct();
        DailyProductPK pk = new DailyProductPK();
        pk.setUserid(UserId);
        pk.setCategoryid(CategoryID);
        d.setDailyproductpk(pk);
        dailyProductRepository.save(d);
        return d;
    }

    //read lists
    public List<String> findByUserid(String UserId){
        List<String> list = new ArrayList<>();
        System.out.println(dailyProductRepository.findByDailProductPK_Userid(UserId));
        return list;
    }
}
