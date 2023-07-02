package com.example.springApi.service.recipe;

import com.example.springApi.domain.dto.MetaDto;
import com.example.springApi.domain.dto.RecipeDetailDto;
import com.example.springApi.domain.dto.RecipeRequestDto;
import com.example.springApi.domain.member.Clip;
import com.example.springApi.domain.member.Member;
import com.example.springApi.domain.recipe.DetailRecipe;
import com.example.springApi.domain.recipe.IngredientsRecipe;
import com.example.springApi.domain.recipe.Recipes;
import com.example.springApi.repository.ClipRepository;
import com.example.springApi.repository.IngredientsRepository;
import com.example.springApi.repository.RecipeDetailRepository;
import com.example.springApi.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private  final RecipeRepository recipeRepository;
    private final RecipeDetailRepository detailRepository;
    private final IngredientsRepository ingredientsRepository;
    private final ClipRepository clipRepository;

    private int seq = 5;

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

    public List<IngredientsRecipe> getIngredients(String id) {
        List<IngredientsRecipe> ingredientsRecipes = ingredientsRepository.getIngredients(id);
        return ingredientsRecipes;
    }

    public Map<String,Object> getClipRecipes(List<Clip> clips,RecipeRequestDto requestDto) {
        int count;
        boolean hasMore = true;
        List<String> getClips = new ArrayList<>();
        for (Clip clip : clips) {
            getClips.add(clip.getRecipe_id());
        }
        Optional<List<Recipes>> recipesList = recipeRepository.getClipRecipes(getClips,requestDto.getCount(),requestDto.getAfter());
        List<Recipes> lists = recipesList.get();
        if(lists.size()==0){
            return null;
        }

        count = lists.size();
        if(lists.get(count-1).getRecipe_id()==recipeRepository.getClipFinalId(getClips)){
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

    @Transactional
    public void addClip(String id,String memberId){
//        clipRepository.addClip(Integer.parseInt(id),memberId,++seq);
        clipRepository.save(
                Clip.builder()
                        .memberId(memberId)
                        .recipe_id(id)
                        .build()
        );
    }

    @Transactional
    public void deleteClip(String id,String memberId){
        clipRepository.deleteClip(Integer.parseInt(id),memberId);
    }

}
