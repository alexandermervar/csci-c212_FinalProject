package edu.iu.c212.places.games;

import java.util.List;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Place;

public abstract class Game extends Place {
    
    private double prize;

    public Game() {
        setIsGame(true);
    }
    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    // Method unecessary?
    // Calls Aracade.saveUsersToFile()
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

        // getArcade().saveUsersToFile(oldUsers); Causes errors
    }

}
