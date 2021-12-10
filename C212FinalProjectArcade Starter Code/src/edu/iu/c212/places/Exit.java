package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

public class Exit extends Place{

    public Exit(Arcade arcade) {
        setArcade(arcade);
        setPlaceName(Places.EXIT);
        setEntryFee(0);
        setIsGame(false);
    }

    @Override
    public void onEnter(User user) {
        System.out.println("Thanks for playing "  + user.getUsername() + "!"); 
        getArcade().saveUsersToFile();   
    
        System.exit(0);
    }
    
}
