package com.circketApplication.service.impl.dataService;

import com.circketApplication.dao.repositories.TeamRepository;
import com.circketApplication.dataModels.response.GetAllTeamResponse;
import com.circketApplication.dataModels.response.TeamDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamDetailService {
    @Autowired
    TeamRepository teamRepository;
    public GetAllTeamResponse getAllTeams(){
        return GetAllTeamResponse.builder()
                .status("success").message("List of teams")
                .teamNames(teamRepository.findAllTeamName())
                .build();
    }
    public TeamDetailResponse getTeamDetails(String teamName){
        return TeamDetailResponse.builder().
                status("success").message("Team details").
                team(teamRepository.findByName(teamName)).build();
    }
}
