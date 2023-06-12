package com.example.springApi.repository;

import com.example.springApi.domain.recipe.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipes, Integer> {

    @Query(value = "SELECT t.* \n" +
            "FROM recipe.recipes t\n" +
            "where recipe_id>:recipe_id\n" +
            "order by recipe_id\n" +
            "limit :count",nativeQuery = true)
    List<Recipes> paginate(@Param(value = "recipe_id")int recipe_id,@Param(value = "count")int count);
}
