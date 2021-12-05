package edu.iu.c212.places;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;

public abstract class Place {
    private String placeName;
    private Arcade arcade;
    private double entryFee;
    
    // Added isGame variable for completeed functionality of toString()
    private boolean isGame;

    public abstract void onEnter(User user);
    
    @Override
    public String toString() {
        return placeName + " (Cost: $" + entryFee + ") Game? " + isGame;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    public Arcade getArcade() {
        return arcade;
    }

    public void setArcade(Arcade arcade) {
        this.arcade = arcade;
    }

    public boolean getIsGame() {
        return isGame;
    }

    public void setIsGame(boolean isGame) {
        this.isGame = isGame;
    }

}
