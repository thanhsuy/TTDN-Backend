package com.example.SpringBootLearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.SpringBootLearning.dto.respone.ApiReponse;
import com.example.SpringBootLearning.dto.request.CategoryCreationRequest;
import com.example.SpringBootLearning.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ApiReponse createCategory(@RequestBody CategoryCreationRequest request) {
        return categoryService.createCategory(request);
    }
}
