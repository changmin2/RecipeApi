package com.example.springApi.controller;

import com.example.springApi.domain.comment.Comment;
import com.example.springApi.domain.dto.CommentDto;
import com.example.springApi.domain.dto.CommentRequestDto;
import com.example.springApi.service.recipe.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping("/{id}")
    public Comment createComment(@PathVariable("id")String recipe_id, @RequestBody CommentDto commentDto) throws ParseException {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 포맷팅 적용
        Date formatedNow = formatter.parse(formatter.format(now));
        commentDto.setCreateDate(formatedNow);

        System.out.println(commentDto.toString());
        return commentService.createComment(recipe_id,commentDto);
    }

    //댓글 조회
    @GetMapping("/{id}")
    public Map<String,Object> getComments(@PathVariable("id")String recipe_id, @ModelAttribute CommentRequestDto commentRequestDto){
        System.out.println(recipe_id+"레시피 아이디");
        System.out.println(commentRequestDto.toString());
        return commentService.getComments(recipe_id,commentRequestDto);
    }
}
