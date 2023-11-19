package ru.codingbros.blameyourcode.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "_post")
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String language;
    @Column(length = 5000)
    private String code;
    @Column(length = 5000)
    private String title;
    @Column(length = 5000)
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    private List<Comment> comments;

    public Post(String language, String code, String title, String comment){
        this.language = language;
        this.code = code;
        this.title = title;
        this.comment = comment;
    }
}
