
# Cricket simulation

Cricket module which can be used to simlate a cricket game.

Spring application uses the cricket module for CURD operations on DB (SQL,elasticsearch) and returns results to the respective APIs.






## Features

* Cricket game
  - Set the number of overs
  - simulates a random ball outcome based on the type of player batting
  - ends the game when the target score is reached or if the batting team is all out 
* Game service
  - Creates a game with 2 teams, loads players from DB if they exists or creates new players
  - simulates a ballout come every 1 second
  - can pause a game with gameId and resume a paused game
  - also allows scheduling games which are to be played in the future
- DB storage 
  - After every ball the details of the outcome are stored in the DB(Sql,elasticsearch)
  - After every game team stats and player stats are updated in the DB
- APIs - 3 types
  - Create APIs
    - API to create player
    - API to create team
    - API to create Game
  - Create API documentation - https://documenter.getpostman.com/view/21067498/2s93JnW7gE
  - Game service APIs
    - API to pause a game
    - API to resume a game
  - Game service API documentation - https://documenter.getpostman.com/view/21067498/2s93Jxr1gi
  - Get APIs
    - List of APIs to obtain details of games,players and teams
    - APIs prefixed with "es/" uses elastic search to query
  - Mysql get APIs documentation - https://documenter.getpostman.com/view/21067498/2s93Jxr1ge
  - elasticsearch get APIs documentation - https://documenter.getpostman.com/view/21067498/2s93Jxr1cK

