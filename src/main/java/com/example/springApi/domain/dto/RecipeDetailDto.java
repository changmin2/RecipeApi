package com.example.springApi.domain.dto;

import lombok.Data;

@Data
public class RecipeDetailDto {
    private int cooking_no;
    private String cooking_dc;
    private String cooking_img;
    private String step_tip;
}
