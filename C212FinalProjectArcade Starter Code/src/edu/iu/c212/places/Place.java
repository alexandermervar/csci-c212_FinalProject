package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

public abstract class Place {
    private String placeName;
    private Arcade arcade;
    private double entryFee;
    
    // Added isGame variable for completeed functionality of toString()
    private boolean isGame;

    abstract void onEnter(User user);

    @Override
    public String toString() {
        return "Location: " + placeName + "Entry fee: $" + entryFee + "Game?: " + isGame;
    }
}
