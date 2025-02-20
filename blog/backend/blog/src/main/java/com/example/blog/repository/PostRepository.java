package com.example.blog.repository;

import com.example.blog.model.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT * FROM posts ORDER BY id ASC LIMIT ?1", nativeQuery = true)
    List<Post> getFirstNPosts(long N);

    @Query(value = "SELECT * FROM posts WHERE title LIKE %:name%", nativeQuery = true)
    List<Post> searchPostByName(String name);

}