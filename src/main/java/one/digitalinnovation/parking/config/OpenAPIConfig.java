package one.digitalinnovation.parking.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
