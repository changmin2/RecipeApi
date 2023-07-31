package com.example.springApi.service.recipe;

import com.example.springApi.domain.comment.Comment;
import com.example.springApi.domain.comment.ReComment;
import com.example.springApi.domain.dto.CommentDto;
import com.example.springApi.domain.dto.CommentRequestDto;
import com.example.springApi.domain.dto.MetaDto;
import com.example.springApi.domain.recipe.Recipes;
import com.example.springApi.repository.CommentRepository;
import com.example.springApi.repository.ReCommentRepository;
import com.example.springApi.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;
    private final ReCommentRepository reCommentRepository;
    @Transactional
    public Comment createComment(String recipe_id, CommentDto commentDto){
        Comment comment = new Comment();
        comment.setCreator(commentDto.getCreator());
        comment.setContent(commentDto.getContent());
        comment.setCreateDate(commentDto.getCreateDate());
        Recipes recipe = recipeRepository.findById(Integer.parseInt(recipe_id)).get();
        List<Comment> commentList = recipe.getCommentList();
        commentList.add(comment);
        return commentList.get(commentList.size()-1);

    }

    public Map<String,Object> getComments(String recipe_id, CommentRequestDto commentRequestDto){
        int count;
        boolean hasMore = true;

        Optional<List<Comment>> comments = commentRepository.paginate(Integer.parseInt(recipe_id), commentRequestDto.getAfter(), commentRequestDto.getCount());
        List<Comment> commentList = comments.get();
        if(commentList.size()==0){
            return null;
        }

        count = commentList.size();
        if(commentList.get(count-1).getComment_id()==commentRepository.getFinalId(Integer.parseInt(recipe_id))){
            System.out.println("ok");
            hasMore = false;
        }

        MetaDto metaDto =new MetaDto();
        metaDto.setCount(count);
        metaDto.setHasMore(hasMore);
        Map<String,Object> map = new HashMap<>();
        map.put("meta",metaDto);
        map.put("data",commentList);

        return map;
    }

    public void deleteComment(String comment_id){
        commentRepository.deleteById(Long.parseLong(comment_id));
    }

    @Transactional
    public ReComment createReComment(String commentId, CommentDto commentDto) {
        ReComment reComment = new ReComment();
        reComment.setCreator(commentDto.getCreator());
        reComment.setContent(commentDto.getContent());
        reComment.setCreateDate(commentDto.getCreateDate());
        Comment comment = commentRepository.findById(Long.parseLong(commentId)).get();
        List<ReComment> commentList = comment.getCommentList();
        commentList.add(reComment);
        return commentList.get(commentList.size()-1);
    }
}
