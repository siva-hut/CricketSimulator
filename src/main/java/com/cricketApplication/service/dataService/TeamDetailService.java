package com.cricketApplication.service.dataService;

import com.cricketApplication.dao.repositories.TeamRepository;
import com.cricketApplication.dataModels.response.getAll.GetAllTeamResponse;
import com.cricketApplication.dataModels.response.get.GetTeamDetailResponse;
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
