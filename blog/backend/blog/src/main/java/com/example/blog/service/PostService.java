package com.example.blog.service;

import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    public void deletePost(Long id) {
        if(postRepository.existsById(id)){
            postRepository.deleteById(id);
        }
        else throw new RuntimeException("Post with id " + id + " was not found!");
    }

    /**
     * @param id ID of post to patch
     * @param postData content of POST to patch
     */
    public Post updatePost(Long id, Post postData) {
        return postRepository.findById(id).map(post -> {
            // Actualizează câmpurile postării
            post.setTitle(postData.getTitle());
            post.setContent(postData.getContent());
            // Salvează postarea actualizată
            return postRepository.save(post);
        }).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
    }

    public List<Post>getFirstNPosts(long N) {
        return postRepository.getFirstNPosts(N);
    }

    public List<Post>searchPostByName(String name){return postRepository.searchPostByName(name);}

}
