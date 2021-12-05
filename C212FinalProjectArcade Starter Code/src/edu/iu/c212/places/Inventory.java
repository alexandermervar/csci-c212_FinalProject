package edu.iu.c212.places;

import edu.iu.c212.models.User;

public class Inventory extends Place {

    public Inventory(String placeName) {
        setPlaceName(placeName);
        setEntryFee(0);
        setIsGame(false);
    }

    @Override
    public void onEnter(User user) {
        // TODO Auto-generated method stub
        
    }
    
}
