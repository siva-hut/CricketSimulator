package com.circketApplication.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TeamController {
    @GetMapping("/getAllTeams")
    public void getAllTeams(){

    }
    @GetMapping("/getTeamDetails")
    public void getTeamDetails(@RequestParam String teamName){

    }
}
