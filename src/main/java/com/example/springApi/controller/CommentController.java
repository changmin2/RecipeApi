package com.example.springApi.controller;

import com.example.springApi.domain.comment.Comment;
import com.example.springApi.domain.comment.ReComment;
import com.example.springApi.domain.dto.CommentDto;
import com.example.springApi.domain.dto.CommentRequestDto;
import com.example.springApi.service.recipe.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
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

        Calendar cal = Calendar.getInstance();
        cal.setTime(formatedNow);
        cal.add(Calendar.HOUR,9);

        commentDto.setCreateDate(cal.getTime());

        return commentService.createComment(recipe_id,commentDto);
    }

    //댓글 조회
    @GetMapping("/{id}")
    public Map<String,Object> getComments(@PathVariable("id")String recipe_id, @ModelAttribute CommentRequestDto commentRequestDto){
        System.out.println(recipe_id+"레시피 아이디");
        System.out.println(commentRequestDto.toString());
        return commentService.getComments(recipe_id,commentRequestDto);
    }

    //댓글 삭제
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id")String comment_id){
        commentService.deleteComment(comment_id);
    }

    //대댓글 생성
    @PostMapping("/recomment/{id}")
    public ReComment createReComment(@PathVariable("id")String comment_id, @RequestBody CommentDto commentDto) throws ParseException {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // 포맷팅 적용
        Date formatedNow = formatter.parse(formatter.format(now));

        Calendar cal = Calendar.getInstance();
        cal.setTime(formatedNow);
        cal.add(Calendar.HOUR,9);

        commentDto.setCreateDate(cal.getTime());

        System.out.println(commentDto.toString());
        return commentService.createReComment(comment_id,commentDto);
    }

}
