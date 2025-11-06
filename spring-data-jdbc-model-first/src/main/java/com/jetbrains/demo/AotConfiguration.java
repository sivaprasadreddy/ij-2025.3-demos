package com.jetbrains.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.dialect.JdbcDialect;
import org.springframework.data.jdbc.core.dialect.JdbcPostgresDialect;

@Configuration
class AotConfiguration {
    @Bean
    JdbcDialect dialect() {
        return JdbcPostgresDialect.INSTANCE;
    }
}
