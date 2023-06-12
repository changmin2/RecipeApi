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
@Table(name = "recipes")
public class Recipes {

    @Id
    private int recipe_id;

    private String recipe_nm;

    private String summary;

    private int nation_code;

    private String nation_nm;

    private String ty_code;

    private String ty_nm;

    private String cooking_time;

    private String calorie;

    private String qnt;

    private String level_nm;

    private String irdnt_code;

    private String pc_nm;

    private String image_url;

    private String detail_url;
}
