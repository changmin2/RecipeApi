package com.example.springApi.controller;

import com.example.springApi.domain.dto.RecipeRequestDto;
import com.example.springApi.domain.recipe.DetailRecipe;
import com.example.springApi.domain.recipe.Recipes;
import com.example.springApi.service.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @GetMapping("/{id}")
    public Map<String,Object> getDetail(@PathVariable("id") String id){
        Recipes recipe = recipeService.getRecipe(Integer.parseInt(id));
        List<DetailRecipe> details = recipeService.getDetail(id);
        Map<String,Object> map = new HashMap<>();
        map.put("data",details);
        map.put("recipe_id",recipe.getRecipe_id());
        map.put("recipe_nm",recipe.getRecipe_nm());
        map.put("summary",recipe.getSummary());
        map.put("nation_code",recipe.getNation_code());
        map.put("nation_nm",recipe.getNation_nm());
        map.put("ty_code",recipe.getTy_code());
        map.put("ty_nm",recipe.getTy_nm());
        map.put("cooking_time",recipe.getCooking_time());
        map.put("calorie",recipe.getCalorie());
        map.put("qnt",recipe.getQnt());
        map.put("level_nm",recipe.getLevel_nm());
        map.put("irdnt_code",recipe.getIrdnt_code());
        map.put("pc_nm",recipe.getPc_nm());
        map.put("image_url",recipe.getImage_url());
        map.put("detail_url",recipe.getDetail_url());
        return map;
    }
}
