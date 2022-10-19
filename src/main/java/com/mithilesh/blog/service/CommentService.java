package com.mithilesh.blog.service;

import com.mithilesh.blog.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,int postId,int userId);
    void deleteComment(int commentId);

}
