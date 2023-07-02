package com.example.springApi.domain.member;

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
public class Clip {
    @Id
    @GeneratedValue
    private Long id;

    private String memberId;

    private String recipe_id;



}
