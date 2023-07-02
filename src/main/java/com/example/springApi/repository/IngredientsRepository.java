package com.example.springApi.repository;

import com.example.springApi.domain.recipe.IngredientsRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientsRepository extends JpaRepository<IngredientsRecipe,Integer> {
    @Query(value = "SELECT T.* , (select code from TypeCode where type = T.ingredientType) as code\n" +
            "FROM recipe.IngredientsRecipe T\n" +
            "where recipe_code= :recipe_id\n" +
            "order by code;",nativeQuery = true)
    List<IngredientsRecipe> getIngredients(String recipe_id);
}
