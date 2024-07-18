package com.example.SpringBootLearning.request;

public class CategoryCreationRequest {
    private String name;

    public CategoryCreationRequest(String name) {
        this.name = name;
    }

    public CategoryCreationRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
