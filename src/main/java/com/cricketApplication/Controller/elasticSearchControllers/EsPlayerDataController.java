package com.cricketApplication.Controller.elasticSearchControllers;

import com.cricketApplication.dataModels.response.getAll.GetAllPlayerResponse;
import com.cricketApplication.service.elasticSearchServices.EsPlayerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/es/")
public class EsPlayerDataController {
    @Autowired
    private EsPlayerDetailService esPlayerDetailService;
    @GetMapping("/getPlayerMatch")
    public GetAllPlayerResponse getPlayerMatch(@RequestParam String playerName){
        return esPlayerDetailService.getPlayerMatch(playerName);
    }
    @GetMapping("/getTruePlayerMatch")
    public GetAllPlayerResponse getTruePlayerMatch(@RequestParam String playerName){
        return esPlayerDetailService.getTruePlayerMatch(playerName);
    }
    @GetMapping("getPlayerSubstringByTeamName")
    public GetAllPlayerResponse getPlayerSubstringByTeamName(@RequestParam String playerName,@RequestParam String teamName){
        return esPlayerDetailService.getPlayerSubstringByTeamName(playerName,teamName);
    }
}
