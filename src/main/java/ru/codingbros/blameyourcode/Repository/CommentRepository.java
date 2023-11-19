package ru.codingbros.blameyourcode.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.codingbros.blameyourcode.Model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
