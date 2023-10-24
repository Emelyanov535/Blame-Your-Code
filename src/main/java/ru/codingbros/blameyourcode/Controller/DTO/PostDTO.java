package ru.codingbros.blameyourcode.Controller.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.codingbros.blameyourcode.Model.Post;

import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "Информация о посте")
@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String language;
    private String code;
    private String title;
    private String comment;
    private List<CommentDTO> comments;

    public PostDTO(Post post){
        this.id = post.getId();
        this.language = post.getLanguage();
        this.code = post.getCode();
        this.title = post.getTitle();
        this.comment = post.getComment();
        this.comments = post.getComments().stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }
}
