package com.gopi.rest.webservices.restfulwebservices.helloword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello-World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean getHelloWorldBean() {
        return new HelloWorldBean("Hello World From Bean!!!");
    }

    @GetMapping("/hello-world-path-variable/{name}")
    public HelloWorldBean getHelloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World through path variable. Hello, %s ", name));
    }

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized() {
        System.out.println(LocaleContextHolder.getLocale());
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());

    }
}
