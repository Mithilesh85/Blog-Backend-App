package com.mithilesh.blog.service.impl;

import com.mithilesh.blog.entity.Comment;
import com.mithilesh.blog.entity.Post;
import com.mithilesh.blog.entity.User;
import com.mithilesh.blog.exception.ResourceNotFoundException;
import com.mithilesh.blog.payload.CommentDto;
import com.mithilesh.blog.repository.CommentRepository;
import com.mithilesh.blog.repository.PostRepository;
import com.mithilesh.blog.repository.UserRepository;
import com.mithilesh.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, int postId, int userId) {

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId));

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);
        comment.setUser(user);

        Comment savedComment = this.commentRepository.save(comment);

        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(int commentId) {

        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment ID", commentId));
        this.commentRepository.delete(comment);
    }
}
