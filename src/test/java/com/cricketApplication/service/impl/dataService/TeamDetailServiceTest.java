package com.cricketApplication.service.impl.dataService;

import com.cricketApplication.dao.entities.TeamDao;
import com.cricketApplication.dao.repositories.TeamRepository;
import com.cricketApplication.service.dataService.TeamDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TeamDetailServiceTest {
    @InjectMocks
    private TeamDetailService teamDetailService;
    @Mock
    private TeamRepository teamRepository;
    @Test
    void getAllTeams() {
        when(teamRepository.findAllTeamName()).thenReturn(new ArrayList<>());
        Assertions.assertNotNull(teamDetailService.getAllTeams());
    }

    @Test
    void getTeamDetails() {
        when(teamRepository.findByName(anyString())).thenReturn(new TeamDao());
        Assertions.assertNotNull(teamDetailService.getTeamDetails("CSK"));
    }
}