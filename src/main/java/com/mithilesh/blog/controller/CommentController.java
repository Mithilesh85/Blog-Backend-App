package com.mithilesh.blog.controller;

import com.mithilesh.blog.payload.ApiResponse;
import com.mithilesh.blog.payload.CommentDto;
import com.mithilesh.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId,@PathVariable int userId){
        CommentDto createdComment = this.commentService.createComment(commentDto, postId,userId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable int commentId){
        this.commentService.deleteComment(commentId);
        ApiResponse apiResponse=new ApiResponse("Comment is deleted successfully",true);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
