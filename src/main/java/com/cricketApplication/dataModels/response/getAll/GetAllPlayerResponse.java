package com.cricketApplication.dataModels.response.getAll;

import com.cricketApplication.dao.entities.PlayerDao;
import com.cricketApplication.dataModels.response.get.GetPlayerResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class GetAllPlayerResponse {
    String status;
    String message;
    List<GetPlayerResponse> playerResponseList;
    public static GetAllPlayerResponse getAllPlayerResponse(List<PlayerDao> playerResponseList){
        return GetAllPlayerResponse.builder()
                .status("success")
                .message("Got all the player")
                .playerResponseList(convert(playerResponseList))
                .build();
    }
    private static List<GetPlayerResponse> convert(List<PlayerDao> playerDaoList) {
        List<GetPlayerResponse> playerResponseList = new ArrayList<>();
        for (PlayerDao playerDao : playerDaoList) {
            playerResponseList.add(GetPlayerResponse.getPlayerResponse(playerDao));
        }
        return playerResponseList;
    }
}
