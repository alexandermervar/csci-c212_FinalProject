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

    public static boolean bust = false;
    public static JLabel totalsLabel = new JLabel("");
    public static JLabel dealerLabel = new JLabel("");
    public static JButton hit = new JButton("Hit!");;
    public static JButton stay = new JButton("Stay!");
    private BlackjackPlayer player;
    private BlackjackDealer dealer;

    public BlackjackGame(Arcade arcade) {
        //TODO
        // Call the superclass constructor
        setPlaceName(Places.BLACKJACKGAME);

        //TODO: setEntryFee() and setPrize()
        setEntryFee();
        setPrize();
        setArcade(arcade);
        player = new BlackjackPlayer();
        dealer = new BackjackDealer();
    }

    @Override
    public void onEnter(User user) {
        //TODO
        // Initialize player, dealer, and user members
        // Set up and display the JFrame as in Lab08

        //TODO: Add a way to display the dealer's hands with text "Dealer has # + ???" where # is a number
        dealerLabel.setText("Dealer has " +  + " + ???");

        totalsLabel.setText("Total(s): " + player.getCurrentTotalsString());

        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel();
        JPanel statusPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(400, 400));

        mainPanel.add(statusPanel);
        mainPanel.add(buttonsPanel);

        hit.addActionListener(new HitButtonListener());
        stay.addActionListener(new StayButtonListener());

        hit.setEnabled(true);
        stay.setEnabled(true);


        buttonsPanel.add(hit);
        buttonsPanel.add(stay);


        statusPanel.add(dealerLabel);
        statusPanel.add(totalsLabel);

        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private class HitButtonListener implements ActionListener {   
        @Override
        public void actionPerformed(ActionEvent e) {
            // Use your hit() method on the player
            // Follow other expectations from Lab08
                
            // Old Code from Lab08
            /*
            Cards oneCards = new Cards(false);
            int tempCard = oneCards.addCard();

            if (tempCard == 1) {
                handTotals[0] += tempCard;
                handTotals[1] += tempCard + 10;
            }
            else {
                handTotals[0] += tempCard;
                handTotals[1] += tempCard;
            }

            totalsLabel.setText("Hand Value: " + displayHand(handTotals));

             if (handTotals[0] > 21 && handTotals[1] > 21) {
                hit.setEnabled(false);
                stay.setEnabled(false);
                totalsLabel.setText("BUST!");
            }
            */

            player.hit();
            totalsLabel.setText("Total(s): " + player.getCurrentTotalsString());
            if (player.getBestTotal() > 21) {
                hit.setEnabled(false);
                stay.setEnabled(false);
                totalsLabel.setText("BUST!");
                getArcade().transitionArcadeState(Places.LOBBY);
            }
        }
    }

    private class StayButtonListener implements ActionListener {     
        @Override
        public void actionPerformed(ActionEvent e) {
            // Follow other expectations from Lab08
            // User your play() method on the dealer
            hit.setEnabled(false);
            stay.setEnabled(false);
            dealer.play();

            //TODO: Check for winner
            // If the dealer and player tie, the entryFee is refunded to the player
            // If the player wins, the prize is given to the player

            if (player.getBestTotal() > dealer.getBestTotal()) {
                totalsLabel.setText("You win!");
                //TODO: Give the player the prize
                getArcade().transitionArcadeState(Places.LOBBY);
            }
            else if (player.getBestTotal() < dealer.getBestTotal()) {
                totalsLabel.setText("You lose!");
                getArcade().transitionArcadeState(Places.LOBBY);
            }
            else {
                totalsLabel.setText("Tie!");
                //TODO: Return the entryFee to the player
                getArcade().transitionArcadeState(Places.LOBBY);
            }
        }
            
    } 
}
