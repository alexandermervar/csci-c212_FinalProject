package edu.iu.c212.places.games;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Places;
import edu.iu.c212.utils.ConsoleUtils;

public class GuessTheNumberGame extends Game{

    public GuessTheNumberGame(Arcade arcade) {
        setArcade(arcade);
        setPlaceName(Places.GUESSTHENUMBERGAME);
        setEntryFee(5.0);
        setPrize(10.0);
    }

    @Override
    public void onEnter(User user) {

        int numberToGuess = (int) (Math.random() * 100);
        int userGuess = -1;
        int numberOfGuesses = 0;

        System.out.println("Welcome to Guess The Number. You'll be guessing a number between 0 and 100.");
        System.out.println("You'll get $10 if you correctly guess the number within 5 tries. Otherwise, you get nothing.");
        
        // Let the user guess the number
        while (userGuess != numberToGuess && numberOfGuesses < 5) {
            System.out.println("What's your guess?");
            
            userGuess = ConsoleUtils.readIntegerLineFromConsoleOrElseComplainAndRetry(number -> number >= 0 && number <= 100, "Number must be within range!");
            numberOfGuesses++;

            if (userGuess != numberToGuess) {
                System.out.println("Oh no, you didn't guess correctly.");

                if (userGuess > numberToGuess) {
                    System.out.println("Your number was too high.");
                } else {
                    System.out.println("Your number was too low.\n");
                }
            }
        }

        if (userGuess == numberToGuess) {
            System.out.println("Congratulations, you correctly guessed the number!");
            System.out.println("The number was: " + numberToGuess);
            System.out.println("You guessed it within " + numberOfGuesses + " tries, so you get $10");
            System.out.println("----------");

            user.setBalance(user.getBalance() + getPrize());

            getArcade().saveUsersToFile();
            getArcade().transitionArcadeState(Places.LOBBY);
        } else {
            System.out.println("You didn't guess the number in time.");
            System.out.println("The number was: " + numberToGuess);
            System.out.println("You lost $" + getEntryFee() + ".");
            System.out.println("----------");

            getArcade().saveUsersToFile();
            getArcade().transitionArcadeState(Places.LOBBY);        }

    }
}
