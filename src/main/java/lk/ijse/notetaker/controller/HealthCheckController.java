package lk.ijse.notetaker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthtest")
public class HealthCheckController {
    @GetMapping
    public String HealthCheck(){ //application eka hariyata run wenwada balaganna tamai healthcheck ekk dnwa.
        return "Note controller app run successfully";

    }
}
