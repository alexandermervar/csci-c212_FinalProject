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
    public boolean isRunning = true;

    public BlackjackGame(Arcade arcade) {
        setPlaceName(Places.BLACKJACKGAME);
        setEntryFee(20.0);
        setPrize(50.0);
        setArcade(arcade);

        Cards deck = new Cards();

        player = new BlackjackPlayer(deck);
        dealer = new BlackjackDealer(deck);
    }

    @Override
    public void onEnter(User user) {
        // Initialize player, dealer, and user members
        // Set up and display the JFrame as in Lab08

        dealerLabel.setText("Dealer has " + dealer.getShownCard() + " + ???");

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

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
        
        if (!isRunning) {
            getWinner(user, player, dealer);
        }
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
                isRunning = false;
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
            isRunning = false;
        }
            
    }

    public void getWinner(User user, BlackjackPlayer player, BlackjackDealer dealer) {
        // If the dealer and player tie, the entryFee is refunded to the player
        // If the player wins, the prize is given to the player

        if (player.getBestTotal() > dealer.getBestTotal()) {
            System.out.println("You Win!");
            user.setBalance(user.getBalance() + getPrize());
            getArcade().transitionArcadeState(Places.LOBBY);
        }
        else if (player.getBestTotal() < dealer.getBestTotal()) {
            System.out.println("You Lose!");
            getArcade().transitionArcadeState(Places.LOBBY);
        }
        else {
            System.out.println("Tie!");
            user.setBalance(user.getBalance() + getEntryFee());
            getArcade().transitionArcadeState(Places.LOBBY);
        }
    } 
}
