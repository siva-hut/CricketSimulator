package com.CircketSimu.App;

import com.CircketSimu.App.CricketGame.Game;
import com.CircketSimu.App.CricketGame.Scheduler;
import com.CircketSimu.App.CricketGame.Team;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	/*	for (String s:ap.getBeanDefinitionNames()) {
			System.out.println(s);
		}
		Game g = ap.getBean(Game.class);
		System.out.println(g.team1.name);
	*/
		String [] arr = {"sia","jnic","ceni","wich","ciewh","ewffw","wvvrfw","fwefwe","ce23","scew","acaef"};
		Team team1 = new Team("ssr",arr);
		Team team2 = new Team("kkr",arr);

		Game g =new Game(team1,team2,20);
		Scheduler.addGame(g);
	}

}
