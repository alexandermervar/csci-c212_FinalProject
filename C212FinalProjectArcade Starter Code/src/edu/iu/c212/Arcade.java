package edu.iu.c212;

import java.util.List;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;

public class Arcade implements IArcade{

    private User currentUser;
    private List<User> allUsers;
    private List<Place> allPlaces;

    public Arcade() {
        currentUser = getUserOnArcadeEntry();
        allUsers = getUserSaveDataFromFile();
        allPlaces = getAllPlaces();
    }

    @Override
    public List<User> getUserSaveDataFromFile() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void saveUsersToFile() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void transitionArcadeState(String newPlaceNameToGoTo) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public User getUserOnArcadeEntry() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Place> getAllPlaces() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
