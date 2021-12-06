package edu.iu.c212.places.games.blackjack;

public class BlackjackPlayer extends BlackJackParticipant{

    public BlackjackPlayer() {
        super(cards.getDeck());
        hit();
        hit();
    }

    public String getCurrentTotalsString() {
        // corrected implementation of getCurrentTotalsString()
        if (handTotals[0] == handTotals[1]) {
            return Integer.toString(handTotals[0]);
        }
        else if (handTotals[0] != handTotals[1] && handTotals[0] <= 21 && handTotals[1] <= 21) {
            return Integer.toString(handTotals[0]) + " | " + Integer.toString(handTotals[1]);
        }
        else {
            if (handTotals[0] > 21) {
                return Integer.toString(handTotals[1]);
            }
            else {
                return Integer.toString(handTotals[0]);
            }
        }
    }

    public int getBestTotal() {
        // corrected implementation of getBestTotal()
        if (handTotals[0] > 21 && handTotals[1] > 21) {
            return handTotals[0];
        }
        else if (handTotals[0] == handTotals[1]) {
            return handTotals[0];
        }
        else if (handTotals[0] != handTotals[1] && handTotals[0] <= 21 && handTotals[1] <= 21) {
            return Math.max(handTotals[0], handTotals[1]);
        }
        else {
            if (handTotals[0] > 21) {
                return handTotals[1];
            }
            else {
                return handTotals[0];
            }
        }
    }
    
}
