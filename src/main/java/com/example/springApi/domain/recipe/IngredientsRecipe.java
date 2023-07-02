package com.example.springApi.domain.recipe;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "IngredientsRecipe")
public class IngredientsRecipe {

    @Id
    private int recipe_no; //재료 순번
    private int recipe_code; // 레시피 코드
    private String ingredientName; // 재료명
    private String quantity; // 재료용량
    private int ingredientCode; // 재료타입 코드
    private String ingredientType; // 재료타입명
}
