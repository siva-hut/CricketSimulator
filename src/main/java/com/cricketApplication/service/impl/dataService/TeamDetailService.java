package com.cricketApplication.service.impl.dataService;

import com.cricketApplication.dao.repositories.TeamRepository;
import com.cricketApplication.dataModels.response.GetAllTeamResponse;
import com.cricketApplication.dataModels.response.GetTeamDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamDetailService {
    @Autowired
    private TeamRepository teamRepository;

    public GetAllTeamResponse getAllTeams() {
        return GetAllTeamResponse.getAllTeamResponse(
                teamRepository.findAllTeamName()
        );
    }

    public GetTeamDetailResponse getTeamDetails(String teamName) {
        return GetTeamDetailResponse.getTeamDetailResponse(
                teamRepository.findByName(teamName)
        );
    }
}
