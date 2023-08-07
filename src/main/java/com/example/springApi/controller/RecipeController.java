package com.example.springApi.controller;

import com.example.springApi.domain.comment.Comment;
import com.example.springApi.domain.dto.CategoryRecipeRequestDto;
import com.example.springApi.domain.dto.CommentDto;
import com.example.springApi.domain.dto.MetaDto;
import com.example.springApi.domain.dto.RecipeRequestDto;
import com.example.springApi.domain.member.Clip;
import com.example.springApi.domain.member.Member;
import com.example.springApi.domain.recipe.DetailRecipe;
import com.example.springApi.domain.recipe.IngredientsRecipe;
import com.example.springApi.domain.recipe.Recipes;
import com.example.springApi.service.member.MemberService;
import com.example.springApi.service.recipe.RecipeService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.springApi.utils.SecurityUtils.TOKEN_HEADER_PREFIX;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class RecipeController {
    private final MemberService memberService;
    private final RecipeService recipeService;

    @GetMapping("/all")
    public Map<String,Object> paginate(@ModelAttribute RecipeRequestDto requestDto){
        System.out.println("요청 변수:"+requestDto.toString());
        Map<String, Object> map = recipeService.allRecipesV2(requestDto);
        return map;
    }

    @GetMapping("/allV2")
    public Map<String,Object> paginateV2(@ModelAttribute CategoryRecipeRequestDto requestDto){
        System.out.println("요청 변수:"+requestDto.toString());
        Map<String, Object> map = recipeService.allRecipesV3(requestDto);
        return map;
    }

    @GetMapping("/{id}")
    public Map<String,Object> getDetail(@PathVariable("id") String id){
        Recipes recipe = recipeService.getRecipe(Integer.parseInt(id));
        List<DetailRecipe> details = recipeService.getDetail(id);
        List<IngredientsRecipe> ingredientsRecipeList = recipeService.getIngredients(id);
        int count = 0;
        Map<String,Object> map = new HashMap<>();
        map.put("data",details);
        map.put("ingredients",ingredientsRecipeList);
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
        //댓글 개수 추가
        if(recipe.getCommentList().size()>0){
            count+=recipe.getCommentList().size();
            for (Comment comment : recipe.getCommentList()) {
                count+=comment.getCommentList().size();
            }
            map.put("commentCount",count);
        }else{
            map.put("commentCount",0);
        }

        return map;
    }

    //즐겨찾기 정보 다 가져오기
    @GetMapping("/clip")
    public Map<String, Object> Clips(HttpServletRequest request, @ModelAttribute RecipeRequestDto requestDto){
        String authroizationHeader = request.getHeader(AUTHORIZATION);
        if(authroizationHeader == null || !authroizationHeader.startsWith(TOKEN_HEADER_PREFIX)){
            throw new RuntimeException("JWT Token이 존재하지 않습니다.");
        }

        String accessToken = authroizationHeader.substring(TOKEN_HEADER_PREFIX.length());
        Member member= memberService.getMe(accessToken);
        System.out.println(member.getMemberId()+"멤버아이");
        List<Clip> clips =  memberService.getClips(member.getMemberId());

        if(clips==null){
            Map<String,Object> map = new HashMap<>();
            MetaDto metaDto = new MetaDto();
            metaDto.setCount(20);
            metaDto.setHasMore(false);
            map.put("meta",metaDto);
            List<Recipes> recipesList = new ArrayList<>();
            map.put("data",recipesList);

            return map;
        }

        Map<String, Object> map = recipeService.getClipRecipes(clips,requestDto);
        return map;
    }

    @GetMapping("/addClip/{id}")
    public void AddClip(@PathVariable("id") String recipe_id, HttpServletRequest request){
        System.out.println("클립추가요청 "+recipe_id);
        String authroizationHeader = request.getHeader(AUTHORIZATION);
        if(authroizationHeader == null || !authroizationHeader.startsWith(TOKEN_HEADER_PREFIX)){
            throw new RuntimeException("JWT Token이 존재하지 않습니다.");
        }

        String accessToken = authroizationHeader.substring(TOKEN_HEADER_PREFIX.length());
        Member member= memberService.getMe(accessToken);
        recipeService.addClip(recipe_id,member.getMemberId());
    }

    @GetMapping("/deleteClip/{id}")
    public void DeleteClip(@PathVariable("id") String recipe_id,HttpServletRequest request){
        String authroizationHeader = request.getHeader(AUTHORIZATION);
        if(authroizationHeader == null || !authroizationHeader.startsWith(TOKEN_HEADER_PREFIX)){
            throw new RuntimeException("JWT Token이 존재하지 않습니다.");
        }

        String accessToken = authroizationHeader.substring(TOKEN_HEADER_PREFIX.length());
        Member member= memberService.getMe(accessToken);
        recipeService.deleteClip(recipe_id, member.getMemberId());
    }

}
