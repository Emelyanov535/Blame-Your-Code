package ru.codingbros.blameyourcode.Controller.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.codingbros.blameyourcode.Model.Comment;

@Schema(description = "Информация о комментарии")
@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
    private long id;
    private String content;
    private Long postId;

    public CommentDTO(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.postId = comment.getPost().getId();
    }
}
