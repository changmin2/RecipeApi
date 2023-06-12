package com.example.springApi.service.recipe;

import com.example.springApi.domain.dto.RecipeRequestDto;
import com.example.springApi.domain.recipe.Recipes;
import com.example.springApi.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private  final RecipeRepository recipeRepository;

    public List<Recipes> allRecipes(RecipeRequestDto requestDto){
        return  recipeRepository.paginate(requestDto.getRecipe_id(), requestDto.getCount());

    }
}
