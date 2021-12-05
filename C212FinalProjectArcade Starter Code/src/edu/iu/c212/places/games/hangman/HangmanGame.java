package edu.iu.c212.places.games.hangman;

import edu.iu.c212.models.User;
import edu.iu.c212.places.games.Game;

public class HangmanGame extends Game{

    public HangmanGame(String placeName) {
        setPlaceName(placeName);
        setEntryFee(0);
    }

    @Override
    public void onEnter(User user) {
        // TODO Auto-generated method stub
        
    }


    
}
