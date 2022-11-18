package one.digitalinnovation.parking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class HelloControler {

    @Operation(hidden = true)
    @GetMapping
    public String hello(){
        return "Hello ALVARO.";
    }
}
