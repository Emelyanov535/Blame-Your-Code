package ru.codingbros.blameyourcode.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.codingbros.blameyourcode.Controller.DTO.PostDTO;
import ru.codingbros.blameyourcode.Controller.DTO.UserDTO;
import ru.codingbros.blameyourcode.Model.Post;
import ru.codingbros.blameyourcode.Model.User;
import ru.codingbros.blameyourcode.Repository.PostRepository;
import ru.codingbros.blameyourcode.Service.NotFoundException.PostNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post findPostById(Long id){
        final Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> new PostNotFoundException(id));
    }

    @Transactional
    public Post createPost(String language, String code, String title, String comment){
        User principalUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final Post post = new Post(language, code, title, comment);
        post.setUser(userService.findUser(principalUser.getId()));
        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id){
        User principalUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(Objects.equals(principalUser.getId(), findPostById(id).getUser().getId())){
            postRepository.delete(findPostById(id));
        }
    }

    @Transactional
    public List<Post> findPostsByLanguage(String language){
        return postRepository.findAllByLanguage(language);
    }

    @Transactional
    public void updatePost(PostDTO postDTO){
        User principalUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(Objects.equals(principalUser.getId(), findPostById(postDTO.getId()).getUser().getId())){
            Post post = findPostById(postDTO.getId());
            if(postDTO.getCode() != null){
                post.setCode(postDTO.getCode());
            }
            if(postDTO.getComment() != null){
                post.setComment(postDTO.getComment());
            }
            if(postDTO.getLanguage() != null){
                post.setLanguage(postDTO.getLanguage());
            }
            if(postDTO.getTitle() != null){
                post.setTitle(postDTO.getTitle());
            }
            postRepository.save(post);
        }
    }
}
