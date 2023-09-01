package com.example.demo.Service;

import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Repository.IPostRepository;
import com.example.demo.Service.NotFoundException.PostNotFoundException;
import com.example.demo.Service.NotFoundException.UserNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PostService {
    private final IPostRepository postRepository;

    public PostService(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public Post findPostById(Long id){
        final Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> new PostNotFoundException(id));
    }

    @Transactional
    public Post createPost(String language, String code, String title, String comment){
        final Post post = new Post(language, code, title, comment);
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
}
