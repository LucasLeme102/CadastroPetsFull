package com.projetocadastro.pets.cadastro_pets.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Cadastro de Pets")
                        .description("Documentação da API para gerenciamento de pets e tutores")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Lucas Leme")
                                .email("lukinha@gmailexample.com")
                                .url("https://github.com/LucasLeme102"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))

                ).externalDocs(new ExternalDocumentation())
                .components(
                        new Components()
                                .addSecuritySchemes("bearer-key",
                                        new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer").bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"));
    }
}
