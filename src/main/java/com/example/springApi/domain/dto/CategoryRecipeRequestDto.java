package com.example.springApi.domain.dto;

import lombok.Data;

@Data
public class CategoryRecipeRequestDto {

    private int after;
    private int count;
    private String keyword;

    private String nm;

    private String level;

    //default 생성자
    public CategoryRecipeRequestDto() {
        this.after = 0;
        this.count = 20;
        this.keyword = "";
        this.nm="";
        this.level="";
    }
}
