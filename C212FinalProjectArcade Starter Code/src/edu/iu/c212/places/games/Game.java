package edu.iu.c212.places.games;

import edu.iu.c212.models.User;

public abstract class Game {
    
    // The onEnter function, which takes an object of the class User as a parameter.
    abstract void onEnter(User user);
}
