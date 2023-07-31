package com.example.springApi.domain.comment;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(referencedColumnName = "comment_id")
    private List<ReComment> commentList = new ArrayList<>();

}
