package com.ssafy.SMDM.controller;

import com.ssafy.SMDM.dto.DailyProduct;
import com.ssafy.SMDM.service.DailyProductService;
import com.ssafy.SMDM.service.NaverShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
                             @RequestParam("categoryid") String categoryid,
                             @RequestParam("date") String date){

        String s = naverShoppingService.findCategory(categoryid);
        Optional<DailyProduct> d = dailyProductService.findByUseridAndCategoryid(id,s);
        if(d.isPresent()) {
            dailyProductService.updateDate(d,date);
            return new ResponseEntity<DailyProduct>(d.get(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<DailyProduct>(HttpStatus.NOT_FOUND);
    }

    //조회
    @GetMapping
    public Object GetCategory(@RequestParam("userid") String id){
        List<String> list = dailyProductService.findByUserid(id);
        return new ResponseEntity<List>(list,HttpStatus.OK);
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
    public Object AddProduct(@RequestBody Map<String,String> param){
        DailyProduct d = dailyProductService.addproduct(param.get("userid")
                    , naverShoppingService.findCategory(param.get("categoryid")), param.get("date"));

        return new ResponseEntity<DailyProduct>(d,HttpStatus.OK);
    }
}
