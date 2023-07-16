package com.example.springApi.domain.recipe;

import com.example.springApi.domain.comment.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@Entity
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

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(referencedColumnName = "recipe_id")
    private List<DetailRecipe> detailRecipeList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(referencedColumnName = "recipe_id")
    private List<Comment> commentList = new ArrayList<>();
}
