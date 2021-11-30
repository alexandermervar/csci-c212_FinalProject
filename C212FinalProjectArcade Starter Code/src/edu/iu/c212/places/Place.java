package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

public abstract class Place {
    private String placeName;
    private Arcade arcade;
    private double entryFee;

    abstract void onEnter(User user);

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Location: " + placeName + "Entry fee: " + entryFee + "$ ";
    }
}
