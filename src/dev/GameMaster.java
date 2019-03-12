package dev;

import java.util.Scanner;

/**
 * The GameMaster class controls the flow of the game between player and the computer.
 * @author  Mert Ege CAN
 * @version 1.0, 12 March 2019
 */
class GameMaster {

    // variables
    private Scanner scan;
    private NumberGuesser computer;
    private String lastPlayerScore;
    private String lastComputerScore;

    // constructor
    GameMaster(){
        scan = new Scanner(System.in);
        computer = new NumberGuesser();
    }

    /**
     * Checks if the last play was a correct guess.
     * @param   score   last score either from player or computer.
     * @return  true if the last score was all correct, false otherwise.
     */
    private boolean isGameEnded(String score){
        return score.equals("+4-0");
    }

    /**
     * Main loop which continues until player or computer wins.
     */
    void play(){
        boolean gameEnded = false;
        boolean turn = Math.random() < 0.5; //start is shuffled

        while(!gameEnded){
            if(turn) {
                playerGuess();
                if(isGameEnded(lastPlayerScore)) {
                    System.out.println("Player wins");
                    gameEnded = true;
                }
                else {
                    turn = false;
                }
            }
            else {
                computerGuess();
                if(isGameEnded(lastComputerScore)) {
                    System.out.println("Computer wins");
                    gameEnded = true;
                }
                else {
                    turn = true;
                }
            }
        }
    }

    /**
     * Takes guess from player, gives it to computer to calculate score then prints it.
     */
    private void playerGuess(){

        System.out.print("Enter guess: ");
        String playerGuess = scan.nextLine();

        while(!playerGuess.matches("[0-9]{4}"))
        {
            System.out.println("The string you entered is invalid");
            System.out.print("Enter guess: ");
            playerGuess = scan.nextLine();
        }

        lastPlayerScore = computer.giveScore(playerGuess);
        System.out.println("Player score: " + lastPlayerScore);
    }

    /**
     * Takes guess from computer, gives it to player to calculate score then prints it.
     */
    private void computerGuess(){
        System.out.println("Computer guess: " + computer.guess());
        System.out.print("Enter score: ");
        lastComputerScore = scan.nextLine();

        while(!lastComputerScore.matches("[+][0-4][-][0-4]"))
        {
            System.out.println("The string you entered is invalid");
            System.out.print("Enter score: ");
            lastComputerScore = scan.nextLine();
        }

        computer.takeGuess(lastComputerScore);
    }
}
