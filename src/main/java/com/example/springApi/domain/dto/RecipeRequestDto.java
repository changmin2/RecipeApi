package com.example.springApi.domain.dto;

import lombok.Data;

@Data
public class RecipeRequestDto {

    private int recipe_id;
    private int count;

    //default 생성자
    public RecipeRequestDto() {
        this.recipe_id = 0;
        this.count = 20;
    }
}
