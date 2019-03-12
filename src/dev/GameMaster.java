package dev;

import java.util.Scanner;

public class GameMaster {

    private Scanner scan;
    private String playerScore;
    private String playerGuess;
    private String computerScore;
    private String computerGuess;
    private NumberGuesser computer;

    public GameMaster(){
        scan = new Scanner(System.in);
        computer = new NumberGuesser();
        playerScore = "+0-0";
        computerScore = "+0-0";
    }

    private boolean isGameEnd(){

        if(playerScore.equals("+4-0")){
            System.out.println("player won");
            return true;
        }
        else if(computerScore.equals("+4-0")){
            System.out.println("comp won");
            return true;
        }
        else
            return false;
    }

    public void play(){
        while(!isGameEnd()){
            playerGuess();

            if(isGameEnd())
                break;

            computerGuess();
        }
    }

    private void playerGuess(){

        System.out.println("enter guess:");
        playerGuess = scan.nextLine();

        while(!playerGuess.matches("[0-9]{4}"))
        {
            System.out.println("The string you entered is invalid");
            playerGuess = scan.nextLine();
        }

        playerScore = computer.giveScore(playerGuess);
        System.out.println("player score: " + playerScore);
    }

    private void computerGuess(){
        computerGuess = computer.guess();
        System.out.println("computer guess:" + computerGuess);

        computerScore = scan.nextLine();

        while(!computerScore.matches("[+][0-4][-][0-4]"))
        {
            System.out.println("The string you entered is invalid");
            computerScore = scan.nextLine();
        }

        computer.takeScore(computerScore);

    }
}
