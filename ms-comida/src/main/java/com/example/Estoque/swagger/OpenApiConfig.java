package com.example.Estoque.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Microservice de Estoque")
                                .description("Microserviço para gestão de Estoque")
                                .version("v1")
                                .contact(new Contact()
                                        .email("coutinho.dev17@gmail.com")
                                        .name("Gabriel Coutinho")
                                        .url("mailto:coutinho.dev17@gmail.com"))
                );
    }
}
