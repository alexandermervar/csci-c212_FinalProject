package edu.iu.c212.places;

import edu.iu.c212.models.User;

public class Exit extends Place{

    public Exit(String placeName) {
        setPlaceName(placeName);
        setEntryFee(0);
        setIsGame(false);
    }

    @Override
    public void onEnter(User user) {
        System.out.println("Thanks for playing "  + user.getUsername() + "!");    
        
        System.exit(0);
    }
    
}
