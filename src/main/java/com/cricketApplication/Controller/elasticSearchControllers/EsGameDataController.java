package com.cricketApplication.Controller.elasticSearchControllers;

import com.cricketApplication.dataModels.response.getAll.GetAllGameResponse;
import com.cricketApplication.service.elasticSearchServices.EsGameDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/es/")
public class EsGameDataController {
    @Autowired
    private EsGameDetailService esGameDetailService;
    @GetMapping("/getGameMatchByTeamName")
    public GetAllGameResponse getGameMatchByTeamName(@RequestParam String teamName){
        return esGameDetailService.getGameMatchByTeamName(teamName);
    }
    @GetMapping("/getTrueGameMatchByTeamName")
    public GetAllGameResponse getTruePlayerMatch(@RequestParam String teamName){
        return esGameDetailService.getTrueGameMatchByTeamName(teamName);
    }
}
