package com.CircketSimu.App;

import com.CircketSimu.App.CricketGame.Team;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan()
public class config {

    @Bean
    public Team team() {
        return new Team("Team1");
    }

}