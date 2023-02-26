package com.circketApplication;
import com.circketApplication.cricketGame.GameBuilder;
import com.circketApplication.dao.repositories.GameRepository;
import com.circketApplication.service.GameService;
import com.circketApplication.service.impl.GameServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class,args);
	}

}
