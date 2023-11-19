package ru.codingbros.blameyourcode.Controller.REST;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.codingbros.blameyourcode.Controller.DTO.CommentDTO;
import ru.codingbros.blameyourcode.Service.CommentService;

@RestController
@RequestMapping("api/Comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/Create")
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO){
        return new CommentDTO(commentService.createComment(commentDTO.getContent(), commentDTO.getPostId()));
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        return ResponseEntity.ok(commentService.deleteComment(id));
    }

    @PutMapping("/Update")
    public ResponseEntity<?> updateComment(@RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(commentService.updateComment(commentDTO));
    }
}
