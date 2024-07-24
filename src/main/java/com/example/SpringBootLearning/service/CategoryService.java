package com.example.SpringBootLearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringBootLearning.entity.Category;
import com.example.SpringBootLearning.repository.CategoryRepository;
import com.example.SpringBootLearning.dto.respone.ApiReponse;
import com.example.SpringBootLearning.dto.request.CategoryCreationRequest;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ApiReponse createCategory(CategoryCreationRequest request) {
        ApiReponse apiReponse = new ApiReponse();
        Category category = new Category();
        category.setName(request.getName());
        apiReponse.setResult(categoryRepository.save(category));
        return apiReponse;
    }
}
