package edu.iu.c212.places.games.blackjack;

import edu.iu.c212.Arcade;
import edu.iu.c212.models.User;
import edu.iu.c212.places.Places;
import edu.iu.c212.places.games.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlackjackGame extends Game {

    /*
        The members as described in Lab08 should be included here, as well
        as player, user, and dealer members
            - a dealerLabel member that can be updated during events
              like the player's label
    */

    public BlackjackGame(Arcade arc) {
        //TODO
        // Call the superclass constructor
        setPlaceName(Places.BLACKJACKGAME);
        setEntryFee();
        setPrize();
    }

    @Override
    public void onEnter(User user) {
        //TODO
        // Initialize player, dealer, and user members
        // Set up and display the JFrame as in Lab08
        
    }

    private class HitButtonListener implements ActionListener {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // Use you hit() method on the player
                // Follow other expectations from Lab08
                
            }
            
    }

    private class StayButtonListener implements ActionListener {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                // Follow other expectations from Lab08
                // User your play() method on the dealer
                
            }
            
    }

    
}
