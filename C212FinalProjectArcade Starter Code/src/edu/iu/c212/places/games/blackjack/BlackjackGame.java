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

    private static JLabel totalsLabel;
    private static JLabel dealerLabel;
    private static JButton hit;
    private static JButton stay;
    private static JFrame frame;
    private User user;
    private BlackjackPlayer player;
    private BlackjackDealer dealer;
    public boolean isRunning = true;

    public BlackjackGame(Arcade arcade) {
        setPlaceName(Places.BLACKJACKGAME);
        setEntryFee(20.0);
        setPrize(50.0);
        setArcade(arcade);
    }

    @Override
    public void onEnter(User user) {
        // Initialize player, dealer, and user members
        // Set up and display the JFrame as in Lab08

        Cards deck = new Cards();

        player = new BlackjackPlayer(deck);
        dealer = new BlackjackDealer(deck);

        this.user = user;

        JPanel mainPanel = new JPanel();
        JPanel statusPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        frame = new JFrame();
        stay = new JButton("Stay!");
        hit = new JButton("Hit!");
        dealerLabel = new JLabel("");
        totalsLabel = new JLabel("");

        dealerLabel.setText("Dealer has " + dealer.getShownCard() + " + ???");

        totalsLabel.setText("Total(s): " + player.getCurrentTotalsString());
        

        mainPanel.setPreferredSize(new Dimension(400, 400));

        mainPanel.add(statusPanel);
        mainPanel.add(buttonsPanel);

        hit.addActionListener(new HitButtonListener());
        stay.addActionListener(new StayButtonListener());
        frame.addWindowListener(new WindowClosedListener());

        buttonsPanel.add(hit);
        buttonsPanel.add(stay);

        statusPanel.add(dealerLabel);
        statusPanel.add(totalsLabel);


        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
        
        // if (!isRunning) {
        //     getWinner(user, player, dealer);
        // }
    }

    private class HitButtonListener implements ActionListener {   
        @Override
        public void actionPerformed(ActionEvent e) {
            // Use your hit() method on the player
            // Follow other expectations from Lab08

            player.hit();
            totalsLabel.setText("Total(s): " + player.getCurrentTotalsString());
            if (player.getBestTotal() > 21) {
                totalsLabel.setText("BUST!");
                stay.setEnabled(false);
                hit.setEnabled(false);
                getWinner(user, player, dealer);
            }
        }
    }

    private class WindowClosedListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowClosing(WindowEvent e) {}

        @Override
        public void windowClosed(WindowEvent e) {
            getArcade().saveUsersToFile();
            getArcade().transitionArcadeState(Places.LOBBY);      
        }

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}

    }

    private class StayButtonListener implements ActionListener {     
        @Override
        public void actionPerformed(ActionEvent e) {
            // Follow other expectations from Lab08
            // User your play() method on the dealer
            hit.setEnabled(false);
            stay.setEnabled(false);
            dealer.play();
            int dealerBest = dealer.getBestTotal();

            if (dealerBest > 21) {
                dealerLabel.setText("Dealer has: BUSTED!");
            }
            else {
                dealerLabel.setText("Dealer has: " + dealerBest);
            }

            getWinner(user, player, dealer);
        }
            
    }

    public void getWinner(User user, BlackjackPlayer player, BlackjackDealer dealer) {
        // If the dealer and player tie, the entryFee is refunded to the player
        // If the player wins, the prize is given to the player

        if (player.getBestTotal() > 21) {
            System.out.println("Bust!");
        }
        else if (player.getBestTotal() > dealer.getBestTotal()) {
            System.out.println("You Win! Dealer had a " + dealer.getDealerBest());
            user.setBalance(user.getBalance() + getPrize());
        }
        else if (player.getBestTotal() < dealer.getBestTotal()) {
            System.out.println("You Lose! Dealer had a " + dealer.getDealerBest());
        }
        else {
            System.out.println("Push!");
            user.setBalance(user.getBalance() + getEntryFee());
        }
    } 
}
