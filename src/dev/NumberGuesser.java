package dev;

import java.util.ArrayList;
import java.util.Collections;

public class NumberGuesser {

    // variables
    private String secretNumber;
    private ArrayList<String> numberArray;

    // constructor
    public NumberGuesser(){
        numberArray = new ArrayList<>();
        fillArray(numberArray);
        secretNumber = numberArray.get(numberArray.size() - 1);
        System.out.println(secretNumber);
    }

    private boolean hasRepeat(String number){
        for(int i = 1; i < number.length(); i++) {
            if (number.substring(0, i).indexOf(number.charAt(i)) >= 0)
                return true;
        }

        return false;
    }

    private void fillArray(ArrayList<String> array){
        array.clear();

        for(int i = 1023; i < 9877; i++){
            if(!hasRepeat(Integer.toString(i)))
                array.add(Integer.toString(i));
        }

        //System.out.println(array);
        Collections.shuffle(numberArray);
    }

    private boolean hasSameScore(String first, String second, int firstPlus, int firstMinus){
        int secondPlus = 0;
        int secondMinus = 0;

        for(int i = 0; i < second.length(); i++){
            if(second.charAt(i) == first.charAt(i))
                secondPlus = secondPlus + 1;
            else if (first.indexOf(second.charAt(i)) >= 0)
                secondMinus = secondMinus + 1;
        }

        //System.out.println(first + " " + second + " " + firstPlus + " " + firstMinus + " " + secondPlus + " " + secondMinus + " " + (firstPlus == secondPlus && firstMinus == secondMinus));

        return firstPlus == secondPlus && firstMinus == secondMinus;
    }

    private void cleanupArray(ArrayList<String> array, String guess, int guessPlus, int guessMinus){
        for(int i = 0; i < array.size(); i++){
            if(!hasSameScore(guess, array.get(i), guessPlus, guessMinus)) {
                array.remove(i);
                i--;
            }
        }
    }

    public String guess(){
        return numberArray.get(0);
    }

    public void takeScore(String score){
        int plus = Integer.parseInt(score.substring(score.indexOf('+') + 1, score.indexOf('-')));
        int minus = Integer.parseInt(score.substring(score.indexOf('-') + 1));

        cleanupArray(numberArray, numberArray.get(0), plus, minus);
    }

    public String giveScore(String guess){
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
