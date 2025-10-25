package com.jetbrains.myapp;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
class PostControllerTests {
    @Autowired
    MockMvcTester mvc;

    @Test
    void testGetPosts() {
        MvcTestResult testResult = mvc.get().uri("/api/posts").exchange();

        assertThat(testResult)
                .hasStatusOk()
                .bodyJson()
                .convertTo(InstanceOfAssertFactories.list(Post.class))
                .satisfies(posts -> assertThat(posts).isNotEmpty());
    }

    @Test
    void testGetPostById() {
        MvcTestResult testResult = mvc.get().uri("/api/posts/1").exchange();

        assertThat(testResult)
                .hasStatusOk()
                .bodyJson().convertTo(Post.class)
                .extracting("id").isEqualTo(1L);
    }

    @Test
    void testCreatePost() {
        MvcTestResult testResult = mvc.post().uri("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "userId": 1,
                            "title": "my test title",
                            "body": "my test body"
                        }
                        """)
                .exchange();

        assertThat(testResult)
                .hasStatus(HttpStatus.CREATED)
                .bodyJson()
                .convertTo(Post.class)
                .satisfies(post -> {
                    assertThat(post).isNotNull();
                    assertThat(post.id()).isNotNull();
                    assertThat(post.userId()).isEqualTo(1);
                    assertThat(post.title()).isEqualTo("my test title");
                    assertThat(post.body()).isEqualTo("my test body");
                });
    }

}