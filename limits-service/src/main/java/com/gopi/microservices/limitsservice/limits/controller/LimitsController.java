package com.gopi.microservices.limitsservice.limits.controller;

import com.gopi.microservices.limitsservice.limits.bean.Limits;
import com.gopi.microservices.limitsservice.limits.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public Limits retriveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
        // return new Limits(1, 100);
    }
}
