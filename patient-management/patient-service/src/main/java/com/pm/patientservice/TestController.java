package com.pm.patientservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private Environment env;

    public TestController() {
        System.out.println("TestController initialized");
    }

    @GetMapping("/test")
    public String test() {
        String enabled = env.getProperty("spring.h2.console.enabled");
        String path = env.getProperty("spring.h2.console.path");
        return "Application is running. H2 enabled: " + enabled + ", path: " + path;
    }
}
