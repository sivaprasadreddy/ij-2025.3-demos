package com.jetbrains.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
@AutoConfigureMockMvc
class PostControllerTests {
    @Autowired
    MockMvcTester mvc;

    @Test
    void shouldGetPostUsingV1() {
        MvcTestResult testResult = mvc.get().uri("/api/posts/1")
                .header("X-API-Version", "1.0")
                .exchange();

        assertThat(testResult)
                .hasStatusOk()
                .bodyJson()
                .doesNotHavePath("status");

    }

    @Test
    void shouldGetPostUsingV2() {
        MvcTestResult testResult = mvc.get().uri("/api/posts/1")
                .header("X-API-Version", "2.0")
                .exchange();

        assertThat(testResult)
                .hasStatusOk()
                .bodyJson()
                .extractingPath("status").isNotEmpty();
    }
}