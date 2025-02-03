package com.example.erudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiDocConfig {

    @Bean
    OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Open Api Documentation Person")
                        .version("v1")
                        .description("Some Description about API"));

    }

}
