package com.circketApplication.dataModels.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Builder
@Getter
@Setter
public class CreatePlayerResponse {
    String status;
    String message;
    Long playerId;

}
