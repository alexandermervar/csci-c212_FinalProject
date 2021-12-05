package edu.iu.c212;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.iu.c212.models.User;
import edu.iu.c212.places.Exit;
import edu.iu.c212.places.Inventory;
import edu.iu.c212.places.Lobby;
import edu.iu.c212.places.Place;
import edu.iu.c212.places.Places;
import edu.iu.c212.places.Store;
import edu.iu.c212.places.games.GuessTheNumberGame;
import edu.iu.c212.places.games.TriviaGame;
import edu.iu.c212.places.games.blackjack.BlackjackGame;
import edu.iu.c212.places.games.hangman.HangmanGame;
import edu.iu.c212.utils.FileUtils;

public class Arcade implements IArcade{

    private User currentUser;
    private List<User> allUsers;
    private List<Place> allPlaces;

    public Arcade() {
        allUsers = getUserSaveDataFromFile();
        currentUser = getUserOnArcadeEntry();
        allPlaces = new ArrayList<Place>();

        allPlaces.add(new Lobby(Places.LOBBY));
        allPlaces.add(new Store(Places.STORE));
        allPlaces.add(new Inventory(Places.INVENTORY));
        allPlaces.add(new GuessTheNumberGame(Places.GUESSTHENUMBERGAME));
        allPlaces.add(new TriviaGame(Places.TRIVIAGAME));
        allPlaces.add(new BlackjackGame(Places.BLACKJACKGAME));
        allPlaces.add(new HangmanGame(Places.HANGMANGAME));
        allPlaces.add(new Exit(Places.EXIT));

        for (Place place : allPlaces) {
            place.setArcade(this);
        }

        transitionArcadeState(Places.LOBBY);

    }

    @Override
    public List<User> getUserSaveDataFromFile() {
        try {
            return FileUtils.getUserDataFromFile();
        } catch (Exception e) {
            System.exit(1);
            return null;
        }
    }

    @Override
    public void saveUsersToFile() {
        try {
            FileUtils.writeUserDataToFile(allUsers);
        } catch (IOException e) {
            System.exit(1);
        }
    }

    @Override
    public void transitionArcadeState(String newPlaceNameToGoTo) {
        
        double userBalance = currentUser.getBalance();
        
        for (Place place : allPlaces) {
            if (place.getPlaceName().equals(newPlaceNameToGoTo)) {
                double entryFee = place.getEntryFee();

                if (userBalance < entryFee) {
                    System.out.println("Sorry you do not have the funds to enter this place");
                    transitionArcadeState(Places.LOBBY);
                }
                else {
                    currentUser.setBalance(userBalance - entryFee);
                    place.onEnter(currentUser);
                }
            }
        } 
    }

    @Override
    public User getUserOnArcadeEntry() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your username");
        String username = sc.nextLine();

        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                System.out.println("Welcome back " + username + "!");
                return user;
            }
        }

        User newUser = new User(username, 500, new ArrayList<>());
        allUsers.add(newUser);
        saveUsersToFile();
        System.out.println("Welcom " + username + "!");

        return newUser;
    }

    @Override
    public List<Place> getAllPlaces() {
        return allPlaces;
    }
    
}
