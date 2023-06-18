package com.example.springApi.domain.recipe;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DetailRecipe")
public class DetailRecipe {

    @Id
    private Long id;

    private int recipe_id;

    private int cooking_no;

    private String cooking_dc;

    private String cooking_img;
    private String step_tip;


}
