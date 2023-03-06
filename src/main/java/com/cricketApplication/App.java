package com.cricketApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class App {
    //	@Autowired
//	private GameRepository gameRepository;
//	@Autowired
//	private GameService gameService;
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

    }
    //Runs once everytime the application starts
//	@EventListener(ApplicationReadyEvent.class)
//	public void startPastGames(){
//		List<GameDao> gameDaoList = gameRepository.findByEndDateIsNullAndStartDateLessThan(new Timestamp(System.currentTimeMillis()));
//		for (GameDao gameDao:gameDaoList) {
//			gameService.resumeGame(gameDao);
//		}
//	}
    //Runs every second
//	@Scheduled(fixedRate=1000)
//	public void StartFutureGames(){
//		List<GameDao> gameDaoList = gameRepository.
//				findByEndDateIsNullAndStartDateLessThanAndGameActive(new Timestamp(System.currentTimeMillis()),false);
//		for (GameDao gameDao:gameDaoList) {
//			gameService.resumeGame(gameDao);
//		}
//	}
}
