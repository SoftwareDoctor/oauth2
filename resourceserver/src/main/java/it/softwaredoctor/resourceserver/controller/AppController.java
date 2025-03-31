package it.softwaredoctor.resourceserver.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class AppController {

    @GetMapping("/hello")
    public String helloWorld(@AuthenticationPrincipal Jwt principal) {
        return "Hello " + principal.getSubject() + ", this is the Resource Server!";
    }
}
