package com.example.springApi.service.recipe;

import com.example.springApi.domain.dto.MetaDto;
import com.example.springApi.domain.dto.RecipeRequestDto;
import com.example.springApi.domain.recipe.Recipes;
import com.example.springApi.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private  final RecipeRepository recipeRepository;

    public List<Recipes> allRecipes(RecipeRequestDto requestDto){
        return  recipeRepository.paginate(requestDto.getAfter(), requestDto.getCount());
    }

    public Map<String,Object> allRecipesV2(RecipeRequestDto requestDto){
        int count;
        boolean hasMore = true;
        List<Recipes> recipesList = recipeRepository.paginate(requestDto.getAfter(), requestDto.getCount());
        count = recipesList.size();
        if(recipesList.get(count-1).getRecipe_id()==recipeRepository.getFinalId()){
         hasMore = false;
        }

        MetaDto metaDto =new MetaDto();
        metaDto.setCount(count);
        metaDto.setHasMore(hasMore);
        Map<String,Object> map = new HashMap<>();
        map.put("meta",metaDto);
        map.put("data",recipesList);

        return map;
    }
}
