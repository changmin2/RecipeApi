package com.example.springApi.controller;

import com.example.springApi.domain.dto.RecipeRequestDto;
import com.example.springApi.domain.recipe.Recipes;
import com.example.springApi.service.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/all")
    public String paginate(@ModelAttribute RecipeRequestDto requestDto){
        List<Recipes> recipesList = recipeService.allRecipes(requestDto);

        for (Recipes recipes : recipesList) {
            System.out.println(recipes.toString());
        }

        return "test";
    }
}
