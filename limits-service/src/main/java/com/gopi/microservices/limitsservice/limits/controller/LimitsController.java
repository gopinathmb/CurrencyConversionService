package com.gopi.microservices.limitsservice.limits.controller;

import com.gopi.microservices.limitsservice.limits.bean.Limits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @GetMapping("/limits")
    public Limits retriveLimits() {

        return new Limits(1, 100);
    }
}
