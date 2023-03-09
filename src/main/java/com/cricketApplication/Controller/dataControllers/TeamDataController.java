package com.cricketApplication.Controller.dataControllers;

import com.cricketApplication.dataModels.response.getAll.GetAllTeamResponse;
import com.cricketApplication.dataModels.response.get.GetTeamDetailResponse;
import com.cricketApplication.service.dataService.TeamDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TeamDataController {
    @Autowired
    private TeamDetailService teamDetailService;

    @GetMapping("/getAllTeams")
    public GetAllTeamResponse getAllTeams() {
        return teamDetailService.getAllTeams();
    }

    @GetMapping("/getTeamDetails")
    public GetTeamDetailResponse getTeamDetails(@RequestParam String teamName) {
        return teamDetailService.getTeamDetails(teamName);
    }
}
