package edu.iu.c212.places.games.blackjack;

import java.util.ArrayList;

public abstract class BlackJackParticipant {
    
    protected static ArrayList<Integer> cards;
    protected int[] handTotals;
    public abstract int getBestTotal();

    public BlackJackParticipant(Cards deck) {
        cards = deck.getDeck();
        handTotals = new int[2];
    }

    // Getters for the cards and handTotals
    public ArrayList<Integer> getCards() {
        return cards;
    }

    public int[] getHandTotals() {
        return handTotals;
    }

    public void hit() {
        // Add the value(s) of a randomly-chosen card to handTotals, without replacement
        
        // Select a random index value from the cards ArrayList

        int selectedCardIndex = (int) (Math.random() * cards.size());

        int selectedCard = cards.get(selectedCardIndex);
        cards.remove(selectedCardIndex);
        if (selectedCard == 11) {
            handTotals[0] += 1;
            handTotals[1] += 11;
        } else {
            handTotals[0] += selectedCard;
            handTotals[1] += selectedCard;
        }
    }
    
}

