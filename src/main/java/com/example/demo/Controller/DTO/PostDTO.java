package com.example.demo.Controller.DTO;

import com.example.demo.Model.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long userId;

    public PostDTO(Post post){
        this.id = post.getId();
        this.language = post.getLanguage();
        this.code = post.getCode();
        this.title = post.getTitle();
        this.comment = post.getComment();
        this.userId = post.getUser().getId();
    }
}
