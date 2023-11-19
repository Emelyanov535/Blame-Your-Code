package ru.codingbros.blameyourcode.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.codingbros.blameyourcode.Model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByLanguage(String language);
}
