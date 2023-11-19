package ru.codingbros.blameyourcode.Controller.REST;

import org.springframework.web.bind.annotation.*;
import ru.codingbros.blameyourcode.Controller.DTO.PostDTO;
import ru.codingbros.blameyourcode.Service.PostService;
import ru.codingbros.blameyourcode.Utils.JwtTokenUtils;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    private final JwtTokenUtils jwtTokenUtils;

    public PostController(PostService postService, JwtTokenUtils jwtTokenUtils) {
        this.postService = postService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @GetMapping("/get")
    public List<PostDTO> getAllPost(){
        return postService.getAllPost().stream()
                .map(PostDTO :: new)
                .toList();
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id){
        return new PostDTO(postService.findPostById(id));
    }

    @PostMapping("/create")
    public PostDTO createPost(@RequestBody PostDTO postDTO){
        return new PostDTO(postService.createPost(postDTO.getLanguage(), postDTO.getCode(), postDTO.getTitle(), postDTO.getComment()));
    }

    @GetMapping("/findByLanguage/{language}")
    public List<PostDTO> findPostsByLanguage(@PathVariable String language){
        return postService.findPostsByLanguage(language).stream()
                .map(PostDTO :: new)
                .toList();
    }
}
