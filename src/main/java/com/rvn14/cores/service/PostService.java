package com.rvn14.cores.service;

import com.rvn14.cores.model.Post;
import com.rvn14.cores.model.User;
import com.rvn14.cores.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void createPost(String content, User user) {
        Post post = Post.builder()
                .content(content)
                .createdAt(LocalDateTime.now())
                .author(user)
                .build();
        postRepository.save(post);
    }

    public List<Post> getPostsByUser(User user) {
        return postRepository.findByAuthorOrderByCreatedAtDesc(user);
    }
}
