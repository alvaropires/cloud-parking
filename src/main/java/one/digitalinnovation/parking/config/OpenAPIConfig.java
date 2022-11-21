package one.digitalinnovation.parking.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.apache.tomcat.websocket.BasicAuthenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
//@OpenAPIDefinition(
//        security = {@SecurityRequirement(name = "basicAuth")})
@OpenAPIDefinition(security = @SecurityRequirement(name = "apiKey"))
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "basicAuth", scheme = "basic")

public class OpenAPIConfig {



    @Bean
    public OpenAPI parkinOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("Parking REST API")
                .description("Spring Boot REST API for Parking")
                .version("1.0.0")
                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0\\")));
    }



}
