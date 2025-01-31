package com.project.basicsessionspringsecurity.Services;


import com.project.basicsessionspringsecurity.Dto.PostDto;
import com.project.basicsessionspringsecurity.Entities.PostEntity;
import com.project.basicsessionspringsecurity.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostDto> getAllPosts() {
        List<PostEntity> posts = postRepository.findAll();
        return posts.stream()
                .map(post -> new PostDto(post.getId(), post.getTitle(), post.getContent()))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Optional<PostEntity> post = postRepository.findById(id);
        return post.map(value -> new PostDto(value.getId(), value.getTitle(), value.getContent()))
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
