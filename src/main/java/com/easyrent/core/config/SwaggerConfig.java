package com.easyrent.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket easyRentApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(Stream.of("http", "https").collect(Collectors.toSet()))
                .apiInfo(apiInfo())
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.easyrent.core"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "EasyRent",
                "This is a web application where renter meets rentees to provide their goods or properties on lease for a certain period. Anyone can register themselves as a renter and advertise their properties to rent them or simply register as a rentee and find what they are searching for. The system will allow rentee to send their rent request to the renter and the renter can accept or reject the request. Both of them can rate and comment on goods they have rented or check the reviews of any other items.",
                "1.0",
                "https://easyrent.com",
                new Contact("EasyRent Team", "https://easyrent.com", "info@easyrent.com"),
                "",
                "",
                Collections.emptyList());
    }
}
