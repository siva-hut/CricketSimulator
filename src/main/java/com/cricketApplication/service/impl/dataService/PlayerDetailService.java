package com.cricketApplication.service.impl.dataService;

import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dao.repositories.PlayerRepository;
import com.cricketApplication.dataModels.response.GetAllPlayerResponse;
import com.cricketApplication.dataModels.response.GetPlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerDetailService {
    @Autowired
    private PlayerRepository playerRepository;

    //Converting list of PlayerDao to list of PlayerResponse
    private List<GetPlayerResponse> convert(List<PlayerDao> playerDaoList) {
        List<GetPlayerResponse> playerResponseList = new ArrayList<>();
        for (PlayerDao playerDao : playerDaoList) {
            playerResponseList.add(GetPlayerResponse.getPlayerResponse(playerDao));
        }
        return playerResponseList;
    }

    public GetAllPlayerResponse getAllPlayers() {

        return GetAllPlayerResponse.getAllPlayerResponse(
                convert(playerRepository.findAll())
        );

    }
    public GetPlayerResponse getPlayer(Long playerId) {

            PlayerDao playerDao = playerRepository.findById(playerId).get();
            return GetPlayerResponse.getPlayerResponse(playerDao);

    }
}
