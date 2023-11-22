package com.kingprice.insurance.springworkassessment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("health")
    private String checkHealth() {
        return "200 OK";
    }

}
