package com.CircketSimu.App.Controller;

import com.CircketSimu.App.CricketGame.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/Api")
public class SubscirptioController {
    @Autowired
    Game g;
    ArrayList<Game> activeGames = new ArrayList<>();
    @GetMapping("/ViewActiveGames")
    public ArrayList<Game> getActiveGames()
    {
        activeGames.add(g);
        return activeGames;
    }

}
