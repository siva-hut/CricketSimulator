package com.circketApplication.dataModels.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePlayerRequest {
    String playerName;
    String playerType;
    String teamName;
}
