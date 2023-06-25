package com.example.springApi.domain.dto;

import lombok.Data;

@Data
public class RecipeRequestDto {

    private int after;
    private int count;
    private String keyword;

    //default 생성자
    public RecipeRequestDto() {
        this.after = 0;
        this.count = 20;
        this.keyword = "";
    }
}
