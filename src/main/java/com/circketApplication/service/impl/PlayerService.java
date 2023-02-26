package com.circketApplication.service.impl;

import com.circketApplication.dao.entities.PlayerDao;
import com.circketApplication.dao.repositories.PlayerRepository;
import com.circketApplication.dataModels.response.GetPlayerResponse;
import com.circketApplication.dataModels.response.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PlayerService {
    int x = 0;
    @Autowired
    private PlayerRepository playerRepository;
    private List<PlayerResponse>convert(List<PlayerDao> playerDaoList){
        List<PlayerResponse> playerResponseList = new ArrayList<>();
        for (PlayerDao playerDao:playerDaoList) {
            playerResponseList.add(PlayerResponse.builder()
                    .playerId(playerDao.getId())
                    .ballsBowled(playerDao.getBallsBowled()).ballsFaced(playerDao.getBallsFaced())
                    .playerName(playerDao.getName()).playerType(playerDao.getPlayerType())
                    .runsGiven(playerDao.getRunsGiven()).runsScored(playerDao.getRunsScored()).wicketsTaken(playerDao.getWicketsTaken())
                    .teamName(playerDao.getTeamName())
                    .build());
        }
        return playerResponseList;
    }
    public GetPlayerResponse getAllPlayers(){
        return GetPlayerResponse.builder()
                .message("Got all the player")
                .status("success")
                .playerResponseList(convert(playerRepository.findAll()))
                .build();

    }
    public GetPlayerResponse getPlayer(Long playerId){
        try {
            PlayerDao playerDao = playerRepository.findById(playerId).get();
            List<PlayerDao> playerDaoList = new ArrayList<>();
            playerDaoList.add(playerDao);
            return GetPlayerResponse.builder()
                    .message("Got the player")
                    .status("success")
                    .playerResponseList(convert(playerDaoList))
                    .build();
        }
        catch (Exception ex){
            return GetPlayerResponse.builder().status("error").message("Could not find the player").build();
        }
    }
    public void test() throws InterruptedException {
        x++;
        Thread.sleep(5000);
        x = x*x;
        System.out.println(x);
    }
}
