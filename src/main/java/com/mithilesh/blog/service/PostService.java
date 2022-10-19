package com.mithilesh.blog.service;

import com.mithilesh.blog.entity.Post;
import com.mithilesh.blog.payload.PostDto;
import com.mithilesh.blog.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,int userId, int categoryId);

    PostDto updatePost(PostDto postDto, int postId);

    PostDto getPost(int postId);

    PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDirection);

    void deletePost(int postId);


    //get all posts by category id
    List<PostDto> getPostByCategoryId(int categoryId);

    //get all posts by user id
    List<PostDto> getPostByUserId(int userId);

    //search posts
    List<PostDto> searchPosts(String keyword);
}
