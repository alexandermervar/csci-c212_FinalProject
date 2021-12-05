package edu.iu.c212.places.games.hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.management.relation.RoleNotFoundException;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Places;
import edu.iu.c212.places.games.Game;
import edu.iu.c212.utils.ConsoleUtils;
import edu.iu.c212.utils.http.HttpUtils;

public class HangmanGame extends Game implements IHangmanGame {

    public HangmanGame(String placeName) {
        setPlaceName(placeName);
        setEntryFee(5);
        setPrize(15);
    }

    @Override
    public void onEnter(User user) {
        List<Character> guessedChars = new ArrayList<Character>();

        int incorrectGuessCount = 0;

        System.out.println("Welcome to hangman! Your task is to guess a random word. You are able to guess letters to reveal parts of the word. If you guess a letter that is not in the word 6 times you lose the game!");

        try {
            String randomWord = HttpUtils.getRandomHangmanWord();
            List<Character> validLex = getValidLexicon();

            while (incorrectGuessCount <= 6) {
                System.out.println("You have guessed " + incorrectGuessCount + " times incorrectly" + guessedChars.toString());
                System.out.println(getBlurredWord(guessedChars, randomWord));
                
                Character guess = ConsoleUtils.readLineFromConsole().toLowerCase().charAt(0);

                
                if (validLex.contains(guess)) {
                    if (!guessedChars.contains(guess)) {
                        guessedChars.add(guess);
                        if (!randomWord.contains(guess.toString())) {
                            incorrectGuessCount++;    
                        }

                        String userWord = getBlurredWord(guessedChars, randomWord);
                        if (userWord.equals(randomWord)) {
                            System.out.println("Congratulations you have guessed the correct word with " + incorrectGuessCount + " incorrect guesses! You have won $" + getPrize());
                            user.setBalance(user.getBalance() + getPrize());
                            getArcade().saveUsersToFile();
                            getArcade().transitionArcadeState(Places.LOBBY);
                        }
                    }
                    else {
                        System.out.println("You can only enter a guess once!");
                        incorrectGuessCount++;
                    }

                }
                else {
                    System.out.println("Invalid input! Only alphabetical letters allowed");
                    incorrectGuessCount++;
                } 
            }

            System.out.println("The word was" + randomWord + " You did not successfully guess the word, try again next time!");
            getArcade().transitionArcadeState(Places.LOBBY);
        } catch (IOException e) {
            e.printStackTrace();
            getArcade().transitionArcadeState(Places.LOBBY);
        }
    }

    @Override
    public String getBlurredWord(List<Character> guess, String word) {
        List<Character> validLex = getValidLexicon();
        validLex.removeAll(guess);

        for (Character character : validLex) {
            word = word.replace(String.valueOf(character), "*");
        }
        return word;
    }

    @Override
    public List<Character> getValidLexicon() {
        List<Character> lex = new ArrayList<Character>(Arrays.asList(new Character[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'}));
        return lex;
    }


    
}
