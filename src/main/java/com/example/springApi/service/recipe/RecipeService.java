package com.example.springApi.service.recipe;

import com.example.springApi.domain.dto.MetaDto;
import com.example.springApi.domain.dto.RecipeDetailDto;
import com.example.springApi.domain.dto.RecipeRequestDto;
import com.example.springApi.domain.recipe.DetailRecipe;
import com.example.springApi.domain.recipe.Recipes;
import com.example.springApi.repository.RecipeDetailRepository;
import com.example.springApi.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private  final RecipeRepository recipeRepository;
    private final RecipeDetailRepository detailRepository;

    public Recipes getRecipe(int id){
        return recipeRepository.findById(id).get();
    }

    public Map<String,Object> allRecipesV2(RecipeRequestDto requestDto){
        int count;
        boolean hasMore = true;
        Optional<List<Recipes>> recipesList = recipeRepository.paginate(requestDto.getAfter(), requestDto.getCount(), requestDto.getKeyword());
        List<Recipes> lists = recipesList.get();
        if(lists.size()==0){
            return null;
        }

        count = recipesList.get().size();
        if(recipesList.get().get(count-1).getRecipe_id()==recipeRepository.getFinalId(requestDto.getKeyword())){
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

    public List<DetailRecipe> getDetail(String id){
        System.out.println("id: "+id);
        List<DetailRecipe> details = detailRepository.getDetail(id);
        return  details;
    }

}
