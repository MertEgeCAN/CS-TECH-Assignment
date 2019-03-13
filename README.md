# CS-TECH-Assignment

*Read in raw mode suggested*

To compile:
  javac NumberGuesser.java GameMaster.java Main.java

To run:
  java Main

How to play:
  -The game starts random with either player turn or computer turn
  -If its player turn, enter a number guess (4 digits)
    -Computer will give the answer score of the player guess (+[0-4]-[0-4])
    -The guess turn will change to computer
  -If its computer turn, it will guess a number
    -Enter the score for computer's guess on your secret number (+[0-4]-[0-4])
    -The guess turn will change to player
  
Form of answer:
  + : for contained digit in right location
  - : for contained digit in wrong location
  
Example:
  Secret: 7532
  Guess : 7624
  Score : +1-1
  
  Secret: 1234
  Guess : 4321
  Score : +0-4
  
  Secret: 9876
  Guess : 9876
  Score : +4-0
  
Note: It is expected that player knows the rules. Error protection is only benefit of computer, player is not considered due to competition.
