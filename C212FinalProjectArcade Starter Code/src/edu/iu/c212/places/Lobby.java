package edu.iu.c212.places;

import java.util.List;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

public class Lobby extends Place{

    public Lobby(Arcade arc) {
        setArcade(arc);
        setPlaceName(Places.LOBBY);
        setEntryFee(0.0);
        setIsGame(false);
    }
    
    @Override
    public void onEnter(User user) {

        List<Place> placeOptions = getArcade().getAllPlaces();

        Place selectedPlace = ConsoleUtils.printMenuToConsole("Welcome to the Arcade, " + user.getUsername() + "! Your balance is $" + user.getBalance() +". What place would you like to go from the list?", placeOptions, true);

        getArcade().transitionArcadeState(selectedPlace.getPlaceName());
    }
}