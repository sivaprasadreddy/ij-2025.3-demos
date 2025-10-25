package com.jetbrains.myapp;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange(url = "https://jsonplaceholder.typicode.com")
public interface PostServiceClient {

    @GetExchange(url = "/posts")
    List<Post> fetchPosts();

    @GetExchange(url = "/posts/{id}")
    Post fetchPostById(@PathVariable Long id);

    @PostExchange(url = "/posts")
    Post createPost(@RequestBody Post post);

}
