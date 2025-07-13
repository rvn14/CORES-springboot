package com.rvn14.cores.repository;

import com.rvn14.cores.model.Post;
import com.rvn14.cores.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthorOrderByCreatedAtDesc(User author);
}
