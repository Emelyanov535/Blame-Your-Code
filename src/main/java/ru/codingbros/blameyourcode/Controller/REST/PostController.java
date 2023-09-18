package ru.codingbros.blameyourcode.Controller.REST;


import org.springframework.web.bind.annotation.*;
import ru.codingbros.blameyourcode.Controller.DTO.PostDTO;
import ru.codingbros.blameyourcode.Service.PostService;
import ru.codingbros.blameyourcode.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public List<PostDTO> getAllPost(){
        return postService.getAllPost().stream()
                .map(PostDTO :: new)
                .toList();
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id){
        return new PostDTO(postService.findPostById(id));
    }

    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO postDTO){
        return new PostDTO(postService.createPost(postDTO.getLanguage(), postDTO.getCode(), postDTO.getTitle(), postDTO.getComment(), userService.findUser(postDTO.getUserId())));
    }

    @GetMapping("/findByLanguage/{language}")
    public List<PostDTO> findPostsByLanguage(@PathVariable String language){
        return postService.findPostsByLanguage(language).stream()
                .map(PostDTO :: new)
                .toList();
    }
}
