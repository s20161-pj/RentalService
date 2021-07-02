package com.rental.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SpringFoxConfiguration {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo(){
        return new ApiInfo("RentalService REST API",
                "Rest API for Rental Service",
                "1.0",
                null,
                new Contact("PJATK",  "www.xxx.com", "xxx@xxx.com"), null, null, Collections.emptyList());
     }

}
