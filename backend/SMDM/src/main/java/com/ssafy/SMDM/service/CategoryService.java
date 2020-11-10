package com.ssafy.SMDM.service;

import com.ssafy.SMDM.dto.Category;
import com.ssafy.SMDM.dto.DailyProduct;
import com.ssafy.SMDM.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    //categoryid로 period 찾기
    public Integer findByCategoryId(String CategoryId){
        Category c = categoryRepository.findByCategoryid(CategoryId);

        return c.getPeriod();
    }

    public void addCategory(String CategoryId, Integer Period){
        Category c = new Category();
        c.setCategoryid(CategoryId);
        c.setPeriod(Period);
        categoryRepository.save(c);
    }
}
