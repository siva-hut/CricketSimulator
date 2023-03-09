package com.cricketApplication.service.dataService;

import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.repositories.PlayerRepository;
import com.cricketApplication.dataModels.response.getAll.GetAllPlayerResponse;
import com.cricketApplication.dataModels.response.get.GetPlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerDetailService {
    @Autowired
    private PlayerRepository playerRepository;
    public GetAllPlayerResponse getAllPlayers() {
        return GetAllPlayerResponse.getAllPlayerResponse(playerRepository.findAll());
    }
    public GetPlayerResponse getPlayer(Long playerId) {
            PlayerDao playerDao = playerRepository.findById(playerId).get();
            return GetPlayerResponse.getPlayerResponse(playerDao);

    }
}
