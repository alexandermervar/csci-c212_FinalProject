package edu.iu.c212;

import java.util.List;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;

public interface IArcade {
    
    List<User> getUserSaveDataFromFile();

    void saveUsersToFile();

    void transitionArcadeState(String newPlaceNameToGoTo);

    User getUserOnArcadeEntry();

    List<Place> getAllPlaces();

}
