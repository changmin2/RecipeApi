package com.example.springApi.domain.comment;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue
    private Long comment_id;

    private int recipe_id;
    private String creator;

    private String content;

    private Date createDate;

}
