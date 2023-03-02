package com.circketApplication.cricketGame;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class GameProperties{
    private boolean switchSides = false;
    private boolean wicketSimulation = false;
    private boolean changeStrike = false;
    boolean noBall = false;
}
