package com.circketApplication.Controller;

import com.circketApplication.dataModels.response.GetAllTeamResponse;
import com.circketApplication.dataModels.response.TeamDetailResponse;
import com.circketApplication.service.impl.dataService.TeamDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TeamController {
    @Autowired
    private TeamDetailService teamDetailService;
    @GetMapping("/getAllTeams")
    public GetAllTeamResponse getAllTeams(){
            return teamDetailService.getAllTeams();
    }
    @GetMapping("/getTeamDetails")
    public TeamDetailResponse getTeamDetails(@RequestParam String teamName){
            return teamDetailService.getTeamDetails(teamName);
    }
}
