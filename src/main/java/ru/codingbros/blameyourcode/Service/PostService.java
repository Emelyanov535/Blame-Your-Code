package ru.codingbros.blameyourcode.Service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.codingbros.blameyourcode.Model.Post;
import ru.codingbros.blameyourcode.Model.User;
import ru.codingbros.blameyourcode.Repository.PostRepository;
import ru.codingbros.blameyourcode.Service.NotFoundException.PostNotFoundException;

import java.util.List;
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
    public Post updatePost(Long id, String language, String code, String title, String comment){
        final Post currentPost = findPostById(id);
        currentPost.setLanguage(language);
        currentPost.setCode(code);
        currentPost.setTitle(title);
        currentPost.setComment(comment);
        return postRepository.save(currentPost);
    }

    @Transactional
    public void deletePost(Long id){
        postRepository.delete(findPostById(id));
    }

    @Transactional
    public List<Post> findPostsByLanguage(String language){
        return postRepository.findAllByLanguage(language);
    }
}
