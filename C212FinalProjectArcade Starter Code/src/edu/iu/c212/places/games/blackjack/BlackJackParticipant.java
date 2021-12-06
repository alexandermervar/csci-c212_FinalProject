package edu.iu.c212.places.games.blackjack;

import java.util.ArrayList;

public abstract class BlackJackParticipant {
    
    protected static ArrayList<Integer> cards;
    protected int[] handTotals;
    public abstract int getBestTotal();

    // Getters for the cards and handTotals
    public ArrayList<Integer> getCards() {
        return cards;
    }
    public int[] getHandTotals() {
        return handTotals;
    }


    public BlackJackParticipant() {
        cards = new ArrayList<Integer>();
        handTotals = new int[2];
        // add integers to the cards variable representing each card
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(i + 2);
                cards.add(i + 2);
                cards.add(i + 2);
                cards.add(i + 2);
            }
        }
        for (int k = 0; k < 12; k++) {
            cards.add(10);
            cards.add(10);
            cards.add(10);
            cards.add(10);
        }
        for (int l = 0; il < 4; l++) {
            cards.add(11);
            cards.add(11);
            cards.add(11);
            cards.add(11);
        }
    }

    public void hit() {
        // Add the value(s) of a randomly-chosen card to handTotals, without replacement
        int selectedCardIndex = (int) Math.random() * 52;
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

