package com.circketApplication;

import com.circketApplication.cricketGame.Game;
import com.circketApplication.Controller.GameController;
import com.circketApplication.cricketGame.GameBuilder;
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
		Game g =new GameBuilder().getGame();
		GameController.addGame(g);
	}

}
