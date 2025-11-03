package com.jetbrains.bookstore;

import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.ApiVersionInserter;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
//@ImportHttpServices(value = BooksClient.class)
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

@Configuration
class ApiVersioningInserterConfig implements RestClientCustomizer {
    @Override
    public void customize(RestClient.Builder restClientBuilder) {
        restClientBuilder
                .apiVersionInserter(ApiVersionInserter.useHeader("API-Version"))
                .defaultApiVersion("1.0");
    }
}