package ar.edu.iua.TruckTeck.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@OpenAPIDefinition(
    security = @SecurityRequirement(name = "Bearer Authentication")
    )
    @SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
		)
@Configuration
public class OpenApiConfig {
 ///Doc: https://springdoc.org/#Introduction
   @Bean
   public OpenAPI openApi() {
       String contributors = "Contributors:\n" +
           "- MasterxDual (https://github.com/agusbram, agustinbram@gmail.com)\n" +
           "- TOB1EH (https://github.com/TOB1EH, tobiasfunes@hotmail.com.ar)\n" +
           "- GastZeta (https://github.com/GastZeta, gzaragosi@gmail.com)\n";

       return new OpenAPI()
           .info(new Info()
               .title("TruckTeck - Ing Web 3")
               .description("API Backend - Ing Web 3\n\n" + contributors)
               .version("v1.0")
               .license(new License().name("License").url("https://github.com/agusbram/TruckTeck-Backend"))
           );
   }
}
