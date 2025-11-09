package ar.edu.iua.TruckTeck.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
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
public class OpenApiConfig {
 ///Doc: https://springdoc.org/#Introduction
   @Bean
   public OpenAPI openApi() {
       return new OpenAPI()
               .info(new Info()
                       .title("TruckTeck - Ing Web 3")
                       .description("API Backend - Ing Web 3")
                       .version("v1.0")
                       .contact(new Contact()
                               .name("MasterxDual")
                               .url("https://github.com/MasterxDual")
                               .email("agustinbram@gmail.com"))
                        .contact(new Contact()
                            .name("TOB1EH")
                            .url("https://github.com/TOB1EH")
                            .email("tobiasfunes@hotmail.com.ar"))
                        .contact(new Contact()
                            .name("GastZeta")
                            .url("https://github.com/GastZeta")
                            .email("gzaragosi@gmail.com"))
                       .termsOfService("TOC")
                       .license(new License().name("License").url("#"))
               );
   }
}
