package edu.iu.c212.places.games;

import edu.iu.c212.utils.*;

import java.util.List;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;

public abstract class Game extends Place {
    
    private double prize;

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public void saveSingularUser(User user) {
        List<User> oldUsers = getArcade().getUserSaveDataFromFile();

        // Check to see if the giver user is already in the list
        // If so, update the user's values
        // If not, add the user to the list
        boolean found = false;


        for (User oldUser : oldUsers) {
            if (oldUser.getUsername().equals(user.getUsername())) {
                found = true;
                oldUser.setBalance(user.getBalance());
            }
        }

        if (!found) {
            oldUsers.add(user);
        }

        getArcade().saveUserSaveDataToFile(oldUsers);
    }

}
