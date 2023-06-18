package com.example.springApi.controller;

import com.example.springApi.domain.dto.RecipeRequestDto;
import com.example.springApi.domain.recipe.Recipes;
import com.example.springApi.service.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/all")
    public Map<String,Object> paginate(@ModelAttribute RecipeRequestDto requestDto){
        System.out.println("요청 변수:"+requestDto.toString());
        Map<String, Object> map = recipeService.allRecipesV2(requestDto);
        return map;
    }
}
