package com.jetbrains.myapp;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/posts")
class PostController {
    private final PostServiceClient client;

    PostController(PostServiceClient client) {
        this.client = client;
    }

    @GetMapping(value="")
    List<Post> getPosts() {
        return client.fetchPosts();
    }

    @GetMapping(value="/{id}")
    Post getPostById(@PathVariable Long id) {
        return client.fetchPostById(id);
    }

    @PostMapping(value="")
    @ResponseStatus(code = CREATED)
    Post createPost(@RequestBody Post post) {
        return client.createPost(post);
    }
}
