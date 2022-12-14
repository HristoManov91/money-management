package com.example.money_management_be.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
            new Info()
                .title("Our money management API")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Hristo Manov")
                    .email("manoraev@gmail.com"))
                .description("Our money management API"));
    }
}
