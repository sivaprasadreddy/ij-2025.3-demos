package com.jetbrains.demo;

import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/posts")
class PostController {

    private final PostRepository postRepository;

    PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping(version = "1.0")
    List<PostV1Dto> getAllV1() {
        return postRepository.findAll(Sort.by(DESC, "createdAt"))
                .stream()
                .map(this::toPostV1Dto)
                .toList();
    }

    @GetMapping(version = "2.0")
    List<PostV2Dto> getAllV2() {
        return postRepository.findAll(Sort.by(DESC, "createdAt"))
                .stream()
                .map(this::toPostV2Dto)
                .toList();
    }

    @GetMapping(value = "/{id}", version = "1.0")
    ResponseEntity<PostV1Dto> getByIdV1(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(this::toPostV1Dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}", version = "2.0")
    ResponseEntity<PostV2Dto> getByIdV2(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(this::toPostV2Dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    Post create(@RequestBody @Valid Post post) {
        post.setId(null);
        if (post.getStatus() == null) {
            post.setStatus(PostStatus.DRAFT);
        }
        return postRepository.save(post);
    }

    private PostV1Dto toPostV1Dto(Post post) {
        return new PostV1Dto(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getUserId());
    }

    private PostV2Dto toPostV2Dto(Post post) {
        return new PostV2Dto(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getStatus(),
                post.getUserId());
    }

}
