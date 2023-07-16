package com.example.springApi.domain.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto{


    private String creator;

    private String content;

    private int after;

    private int count;

    private Date createDate;

}
