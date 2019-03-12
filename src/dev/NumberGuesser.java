package dev;

import java.util.ArrayList;
import java.util.Collections;

/**
 * NumberGuesser class is the computer which opposes the player. It uses the correct placed digit (denoted by plus sign)
 * and wrong placed but contained digit (denoted by minus sign) scores to narrow the possible answer array and finally,
 * makes the correct guess or left with one answer.
 * @author  Mert Ege CAN
 * @version 1.0,    12 March 2019
 */
class NumberGuesser {

    // variables
    private String secretNumber;
    private ArrayList<String> numberArray;

    // constructor
    NumberGuesser(){
        numberArray = new ArrayList<>();
        fillArray(numberArray);
        secretNumber = numberArray.get(numberArray.size() - 1); //array is shuffled, last element is computer's number
    }

    /**
     * Checks if a number has repeating digits.
     * @param   number  a number to be checked.
     * @return  true if the number has duplicate digit, false otherwise.
     */
    private boolean hasRepeat(String number){
        for(int i = 1; i < number.length(); i++) {
            if (number.substring(0, i).indexOf(number.charAt(i)) >= 0)
                return true;
        }

        return false;
    }

    /**
     * Empties and fills a given array with non-duplicate-4-digit numbers then shuffles the order.
     * @param   array   array to be filled.
     */
    private void fillArray(ArrayList<String> array){
        array.clear();

        for(int i = 1023; i < 9877; i++){
            if(!hasRepeat(Integer.toString(i)))
                array.add(Integer.toString(i));
        }

        Collections.shuffle(array);
    }

    /**
     * This method compares two given numbers, according to their possible scores. It checks if the second number worth exactly
     * as the first number scored by the user compared among themselves.
     * @param   first   first reference element with score to be compared.
     * @param   second  second element to be compared.
     * @param   firstPlus   first element's plus value.
     * @param   firstMinus  first element's minus value.
     * @return  returns true if given two number have the same score, false otherwise.
     */
    private boolean hasSameScore(String first, String second, int firstPlus, int firstMinus){
        int secondPlus = 0;
        int secondMinus = 0;

        for(int i = 0; i < second.length(); i++){
            if(second.charAt(i) == first.charAt(i))
                secondPlus = secondPlus + 1;
            else if (first.indexOf(second.charAt(i)) >= 0)
                secondMinus = secondMinus + 1;
        }

        return firstPlus == secondPlus && firstMinus == secondMinus;
    }

    /**
     * This method traces the all possible answers array and cleans up the not possible answers found by hasSameScore
     * method. Within this cleanup operation, remaining elements of the array are promises to be a better scored guesses
     * or eliminate more wrong possibilities on the upcoming guess run.
     * @param   array   possible answers array to be cleaned.
     * @param   guess   guess made by the computer.
     * @param   guessPlus   guess plus score.
     * @param   guessMinus  guess minus score.
     */
    private void cleanupArray(ArrayList<String> array, String guess, int guessPlus, int guessMinus){
        for(int i = 0; i < array.size(); i++){
            if(!hasSameScore(guess, array.get(i), guessPlus, guessMinus)) {
                array.remove(i);
                i--;    //if an element is removed, we need to move back index by 1
            }
        }

        if(array.size() == 0){
            System.err.println("No possible answer with scores you given!!!");
            System.exit(-1);
        }
    }

    /**
     * Since the possible answer array is shuffled, the method returns the first element from those numbers from the
     * array as a guess.
     * @return  returns the guess as a string.
     */
    String guess(){
        return numberArray.get(0);  //next element remaining in the array is the upcoming guess by computer
    }

    /**
     * This method parses the score given by the player and calls cleanupArray with last guess made
     * @param   score   last score provided by the user to the last guess made by computer.
     */
    void takeGuess(String score){
        int plus = Integer.parseInt(score.substring(score.indexOf('+') + 1, score.indexOf('-')));
        int minus = Integer.parseInt(score.substring(score.indexOf('-') + 1));

        cleanupArray(numberArray, numberArray.get(0), plus, minus);
    }

    /**
     * This method calculates the score of the player's guess and returns it.
     * @param   guess   guess made by the player.
     * @return  returns the score of the player guess.
     */
    String giveScore(String guess){
        int plus = 0;
        int minus = 0;

        for(int i = 0; i < guess.length(); i++){
            if(guess.charAt(i) == secretNumber.charAt(i))
                plus = plus + 1;
            else if (secretNumber.indexOf(guess.charAt(i)) >= 0)
                minus = minus + 1;
        }

        return "+" + plus + "-" + minus;
    }
}