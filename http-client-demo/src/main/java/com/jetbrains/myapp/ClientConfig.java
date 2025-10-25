package com.jetbrains.myapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration
//@ImportHttpServices(PostServiceClient.class)
class ClientConfig {

    @Bean
    PostServiceClient postServiceClient() {
        RestClient restClient = RestClient.create();
        HttpServiceProxyFactory factory =
                HttpServiceProxyFactory
                        .builderFor(RestClientAdapter.create(restClient))
                        .build();
        return factory.createClient(PostServiceClient.class);
    }
}
