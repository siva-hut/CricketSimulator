package com.CircketSimu.App;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/welcome")
    public String welcomeMessage()
    {
        return "First spring project!";
    }
    public void send()
    {

    }
}
