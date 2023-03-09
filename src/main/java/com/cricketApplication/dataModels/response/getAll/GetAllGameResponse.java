package com.cricketApplication.dataModels.response.getAll;

import com.cricketApplication.dao.entities.GameDao;
import com.cricketApplication.dataModels.response.get.GetGameResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
public class GetAllGameResponse {
    String status;
    String message;
    List<GetGameResponse> gameList;

    public static GetAllGameResponse getAllGameResponse(List<GameDao>gameResponseList){
        return GetAllGameResponse.builder()
                .gameList(convert(gameResponseList))
                .status("success")
                .message("List of games")
                .build();
    }
    private static List<GetGameResponse> convert(List<GameDao> gameDaoList) {
        List<GetGameResponse> gameResponseList = new ArrayList<>();
        for (GameDao gameDao : gameDaoList) {
            gameResponseList.add(GetGameResponse.getGameResponse(gameDao));
        }
        return gameResponseList;
    }
}
