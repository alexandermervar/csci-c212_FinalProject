package edu.iu.c212.places.games.blackjack;

import edu.iu.c212.models.User;
import edu.iu.c212.places.games.Game;

public class BlackjackGame extends Game {

    public BlackjackGame(String placeName) {
        setPlaceName(placeName);
        setEntryFee(0); // Not final value
        setIsGame(true);
    }

    @Override
    public void onEnter(User user) {
        // TODO Auto-generated method stub
        
    }

    
}
