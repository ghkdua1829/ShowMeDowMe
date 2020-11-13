package com.ssafy.SMDM.controller;

import com.ssafy.SMDM.service.CategoryService;
import com.ssafy.SMDM.service.NaverShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    NaverShoppingService naverShoppingService;

    @PostMapping
    public Object saveCategory(@RequestBody Map<String, String> param){
        categoryService.addCategory(naverShoppingService.findCategory(param.get("categoryid")).get(1),Integer.parseInt(param.get("period")));
        return new ResponseEntity(HttpStatus.OK);
    }
}
