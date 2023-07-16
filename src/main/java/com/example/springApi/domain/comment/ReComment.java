package com.example.springApi.domain.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "ReComment")
public class ReComment {

    @Id
    @GeneratedValue
    private Long id;

    private int recipe_id;

    private String creator;

    private String content;

    private Date createDate;
}
