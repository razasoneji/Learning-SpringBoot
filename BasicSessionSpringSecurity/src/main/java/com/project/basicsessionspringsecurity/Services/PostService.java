package com.project.basicsessionspringsecurity.Services;

import com.project.basicsessionspringsecurity.Dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
    void deletePost(Long id);
}
