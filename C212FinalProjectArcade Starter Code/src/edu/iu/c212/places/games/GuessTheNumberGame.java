package edu.iu.c212.places.games;

import edu.iu.c212.models.User;

public class GuessTheNumberGame extends Game{

    private int numberToGuess;
    private int numberOfGuesses;

    @Override
    public void onEnter(User user) {
        setPrize(10.0);


    }
}
