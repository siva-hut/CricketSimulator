package com.cricketApplication.cricketGame;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class GameProperties {
    boolean noBall = false;
    private boolean switchSides = false;
    private boolean wicketSimulation = false;
    private boolean changeStrike = false;
}
