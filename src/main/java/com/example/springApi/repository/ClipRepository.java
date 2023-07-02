package com.example.springApi.repository;

import com.example.springApi.domain.member.Clip;
import com.example.springApi.domain.recipe.DetailRecipe;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.crypto.Cipher;
import java.util.List;
import java.util.Optional;

public interface ClipRepository extends JpaRepository<Clip,Long> {
    @Query(value = "SELECT * \n" +
            "FROM recipe.Clip\n" +
            "where memberId=:memberId\n" +
            "order by recipe_id desc",nativeQuery = true)
    List<Clip> getClips(String memberId);

    @Modifying
    @Transactional
    @Query(value = "delete from recipe.Clip \n" +
            "where memberId = :memberId\n" +
            "and recipe_id = :recipe_id",nativeQuery = true)
    void deleteClip(int recipe_id,String memberId);
}
