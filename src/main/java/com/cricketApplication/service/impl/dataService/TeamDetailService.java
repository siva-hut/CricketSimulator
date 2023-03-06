package com.cricketApplication.service.impl.dataService;

import com.cricketApplication.dao.repositories.TeamRepository;
import com.cricketApplication.dataModels.response.GetAllTeamResponse;
import com.cricketApplication.dataModels.response.TeamDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamDetailService {
    @Autowired
    TeamRepository teamRepository;

    public GetAllTeamResponse getAllTeams() {
        return GetAllTeamResponse.builder()
                .status("success").message("List of teams")
                .teamNames(teamRepository.findAllTeamName())
                .build();
    }

    public TeamDetailResponse getTeamDetails(String teamName) {
        return TeamDetailResponse.builder().
                status("success").message("Team details").
                team(teamRepository.findByName(teamName)).build();
    }
}
