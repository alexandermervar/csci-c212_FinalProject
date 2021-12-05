package edu.iu.c212.places;

import java.util.Arrays;
import java.util.List;

import edu.iu.c212.models.User;
import edu.iu.c212.utils.ConsoleUtils;

public class Lobby extends Place{

    public Lobby(String placeName) {
        setPlaceName(placeName);
        setEntryFee(0.0);
        setIsGame(false);
    }
    
    @Override
    public void onEnter(User user) {

        List<String> placeOptions = Arrays.asList(Places.ALLPLACES);

        String selectedPlace = ConsoleUtils.printMenuToConsole("Welcome to the Arcade! Your balance is $" + user.getBalance() +". What place would you like to go from the list?", placeOptions, true);

        getArcade().transitionArcadeState(selectedPlace);
    }
}