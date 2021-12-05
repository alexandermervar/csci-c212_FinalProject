package edu.iu.c212.places;

import edu.iu.c212.models.User;

public class Lobby extends Place {

    public Lobby(String placeName) {
        setPlaceName(placeName);
        setEntryFee(0);
        setIsGame(false);
    }

    @Override
    public void onEnter(User user) {
        // TODO Auto-generated method stub
        
    }
    
}
