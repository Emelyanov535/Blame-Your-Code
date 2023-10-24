package ru.codingbros.blameyourcode.Controller.REST;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.codingbros.blameyourcode.Controller.DTO.PostDTO;
import ru.codingbros.blameyourcode.Service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/Post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/GetAll")
    public List<PostDTO> getAllPost(){
        return postService.getAllPost().stream()
                .map(PostDTO :: new)
                .toList();
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id){
        return new PostDTO(postService.findPostById(id));
    }

    @PostMapping("/Create")
    public PostDTO createPost(@RequestBody PostDTO postDTO){
        return new PostDTO(postService.createPost(postDTO.getLanguage(), postDTO.getCode(), postDTO.getTitle(), postDTO.getComment()));
    }

    @GetMapping("/{language}")
    public List<PostDTO> findPostsByLanguage(@PathVariable String language){
        return postService.findPostsByLanguage(language).stream()
                .map(PostDTO :: new)
                .toList();
    }

    @DeleteMapping("/Delete/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }

    @PutMapping("/Update")
    public void updatePost(@RequestBody PostDTO postDTO){
        postService.updatePost(postDTO);
    }
}
