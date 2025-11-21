package com.jetbrains.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
class ClientConfig {

    @Bean
    BooksClient booksClient(RestClient.Builder builder) {
        RestClient restClient = builder.build();
        var factory = HttpServiceProxyFactory
                        .builderFor(RestClientAdapter.create(restClient))
                        .build();
        return factory.createClient(BooksClient.class);
    }
}