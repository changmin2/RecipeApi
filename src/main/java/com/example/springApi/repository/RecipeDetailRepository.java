package com.example.springApi.repository;

import com.example.springApi.domain.dto.RecipeDetailDto;
import com.example.springApi.domain.recipe.DetailRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeDetailRepository extends JpaRepository<DetailRecipe,Long> {

    @Query(value = "SELECT t.* FROM recipe.DetailRecipe t\n" +
            "where recipe_id=:recipe_id\n" +
            "order by cooking_no asc;",nativeQuery = true)
    List<DetailRecipe> getDetail(String recipe_id);
}
