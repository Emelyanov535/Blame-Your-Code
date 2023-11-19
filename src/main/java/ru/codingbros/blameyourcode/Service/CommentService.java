package ru.codingbros.blameyourcode.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.codingbros.blameyourcode.Controller.DTO.CommentDTO;
import ru.codingbros.blameyourcode.Model.Comment;
import ru.codingbros.blameyourcode.Model.Post;
import ru.codingbros.blameyourcode.Model.User;
import ru.codingbros.blameyourcode.Repository.CommentRepository;
import ru.codingbros.blameyourcode.Service.NotFoundException.CommentNotFoundException;

import java.util.Objects;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    @Transactional(readOnly = true)
    public Comment findCommentById(Long id){
        final Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElseThrow(() -> new CommentNotFoundException(id));
    }

    @Transactional
    public Comment createComment(String content, Long postId){
        final Comment comment = new Comment(content);
        comment.setPost(postService.findPostById(postId));
        return commentRepository.save(comment);
    }

    @Transactional
    public Comment deleteComment(Long id){
        User principalUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = findCommentById(id);
        if(Objects.equals(principalUser.getId(), findCommentById(id).getPost().getUser().getId())) {
            commentRepository.delete(comment);
        }
        return comment;
    }

    @Transactional
    public Comment updateComment(CommentDTO commentDTO){
        User principalUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = findCommentById(commentDTO.getId());
        if(Objects.equals(principalUser.getId(), findCommentById(comment.getId()).getPost().getUser().getId())) {
            if(commentDTO.getContent() != null){
                comment.setContent(commentDTO.getContent());
            }
        }
        return commentRepository.save(comment);
    }
}
