package com.CircketSimu.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		//ApplicationContext ap =
		SpringApplication.run(App.class, args);
	/*	for (String s:ap.getBeanDefinitionNames()) {
			System.out.println(s);
		}
		Game g = ap.getBean(Game.class);
		System.out.println(g.team1.name);
	*/
	}

}
