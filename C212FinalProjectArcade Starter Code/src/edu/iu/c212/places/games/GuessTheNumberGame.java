package edu.iu.c212.places.games;

import java.util.Scanner;

import edu.iu.c212.models.User;

public class GuessTheNumberGame extends Game{

    private int numberToGuess;
    private int numberOfGuesses = 0;
    private int userGuess;

    @Override
    public void onEnter(User user) {
        setPrize(10.0);
        setEntryFee(5.0);
        numberToGuess = (int) (Math.random() * 100);

        System.out.println("Welcome to Guess The Number. You'll be guessing a number between 0 and 100.");
        System.out.println("You'll get $10 if you correctly guess the number within 5 tries. Otherwise, you get nothing.");

        // check to see if the user has enough money to play
        if (user.getBalance() < getEntryFee()) {
            System.out.println("");
            System.out.println("You don't have enough money to play this game.");
            System.out.println("----------");
            return;
        }

        // Save singular user to file with new balance upon entry
        user.setBalance(user.getBalance() - getEntryFee());
        saveSingularUser(user);
        
        // Let the user guess the number
        while (userGuess != numberToGuess && numberOfGuesses < 5) {
            System.out.println("What's' your guess?");
            
            Scanner sc = new Scanner(System.in);
            int userGuess = Integer.parseInt(sc.nextLine());
            numberOfGuesses++;

            if (userGuess != numberToGuess) {
                System.out.println("Oh no, you didn't guess correctly.");

                if (userGuess > numberToGuess) {
                    System.out.println("Your number was too high.");
                } else {
                    System.out.println("Your number was too low.");
                }
            }
        }

        if (userGuess == numberToGuess) {
            System.out.println("Congratulations, you correctly guessed the number!");
            System.out.println("You guessed it within " + numberOfGuesses + " tries, so you get $10");
            user.setBalance(user.getBalance() + getPrize());
            saveSingularUser(user);
            System.out.println("----------");
            return;
        } else {
            System.out.println("You didn't guess the number in time.");
            System.out.println("You lost $" + getEntryFee() + ".");
            System.out.println("----------");
            return;
        }

    }
}
