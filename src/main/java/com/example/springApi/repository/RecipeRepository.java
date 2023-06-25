package com.example.springApi.repository;

import com.example.springApi.domain.recipe.Recipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipes, Integer> {

    @Query(value = "SELECT t.* \n" +
            "FROM recipe.recipes t\n" +
            "where recipe_id>:recipe_id\n" +
            "and recipe_nm LIKE %:keyword%\n" +
            "order by recipe_id\n" +
            "limit :count",nativeQuery = true)
    Optional<List<Recipes>> paginate(@Param(value = "recipe_id")int recipe_id, @Param(value = "count")int count,
                                    @Param(value = "keyword")String keyword);

    @Query(value = "SELECT r.* \n" +
            "FROM recipe.recipes r\n" +
            "where recipe_nm LIKE %:keyword%\n" +
            "order by recipe_id desc\n" +
            "limit 1",nativeQuery = true)
    int getFinalId(@Param(value="keyword")String keyword);
}
