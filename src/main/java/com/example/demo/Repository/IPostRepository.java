package com.example.demo.Repository;

import com.example.demo.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByLanguage(String language);
}
