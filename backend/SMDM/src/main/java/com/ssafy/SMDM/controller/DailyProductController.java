package com.ssafy.SMDM.controller;

import com.ssafy.SMDM.dto.DailyProduct;
import com.ssafy.SMDM.service.DailyProductService;
import com.ssafy.SMDM.service.NaverShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/users/dailyproducts")
public class DailyProductController {
    @Autowired
    DailyProductService dailyProductService;

    @Autowired
    NaverShoppingService naverShoppingService;

    //update date
    @PutMapping
    public Object UpdateDate(@RequestParam("userid") String id,
                             @RequestParam("categoryid") String categoryid){

        String s = naverShoppingService.findCategory(categoryid).get(1);
        Optional<DailyProduct> d = dailyProductService.findByUseridAndCategoryid(id,s);
        if(d.isPresent()) {
            dailyProductService.updateDate(id,s);
            return new ResponseEntity<DailyProduct>(d.get(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<DailyProduct>(HttpStatus.NOT_FOUND);
    }

    //카테고리조회
    @GetMapping("/category")
    public Object GetCategory(@RequestParam("userid") String id){
        List<String> list = dailyProductService.findByUser(id);
        return new ResponseEntity<List>(list,HttpStatus.OK);
    }

    //조회
    @GetMapping
    public Object Getdatas(@RequestParam("userid") String id){

        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
        java.util.Date time = new Date();
        String time1 = format1.format(time);
        String time2 = new String();
        try {
            Date date1 = format1.parse(time1);
            List<DailyProduct> list = dailyProductService.findByUserid(id);
            for(DailyProduct m : list){

                Date date2 = format1.parse(m.getDate());
                long calDate = date1.getTime() - date2.getTime();
                long calDateDays = calDate / ( 24*60*60*1000);
                if(calDateDays >= m.getPeriod()){
                    dailyProductService.updateAlarm(id,m.getCategoryid(),1);
                }
                else
                    dailyProductService.updateAlarm(id,m.getCategoryid(),0);
            }
            List<DailyProduct> list2 = dailyProductService.findByUserid(id);
            return new ResponseEntity<List>(list2,HttpStatus.OK);
        }catch (ParseException e){
            return new ResponseEntity<String>("1",HttpStatus.NOT_FOUND);
        }

    }

    //삭제
    @DeleteMapping
    public Object DeleteCategory(@RequestParam("userid") String id,
                                 @RequestParam("categoryid") String categoryid){
        Optional<DailyProduct> d = dailyProductService.findByUseridAndCategoryid(id,categoryid);
        if(d.isPresent()){
            dailyProductService.deleteByUseridAndCategoryid(id,categoryid);
            return new ResponseEntity<String>("Delete",HttpStatus.OK);
        }
        else
            return new ResponseEntity<String>("not found category",HttpStatus.NOT_FOUND);

    }

    //상품추가
    @PostMapping
    public Object AddProduct(@RequestBody List<Map<String,String>> param){
        for(Map<String,String> m : param){
            String navercategory = naverShoppingService.findCategory(m.get("categoryid")).get(1);
            if(m.get("check").equals("Y")) {
                dailyProductService.addproduct(m.get("userid")
                        , navercategory);
            }
            else{
                dailyProductService.deleteByUseridAndCategoryid(m.get("userid"),navercategory);
            }
        }
        return new ResponseEntity<String>("ok",HttpStatus.OK);
    }

    //알람바꾸기
    @PostMapping("/updatealarm")
    public Object UpdateAlarm(@RequestBody Map<String,String> param){
        String navercategory = naverShoppingService.findCategory(param.get("categoryid")).get(1);
        dailyProductService.updateAlarm2(param.get("userid"),navercategory,Integer.parseInt(param.get("alarm")));
        return new ResponseEntity<String>("ok",HttpStatus.OK);
    }


}
