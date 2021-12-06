package edu.iu.c212.places.games.blackjack;

import java.util.ArrayList;

public class Cards {
    
    private static ArrayList<Integer> deck;

    public static ArrayList<Integer> getDeck() {
        return deck;
    }

    public Cards() {
        // add integers to the cards variable representing each card
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(i + 2);
                deck.add(i + 2);
                deck.add(i + 2);
                deck.add(i + 2);
            }
        }
        for (int k = 0; k < 12; k++) {
            deck.add(10);
            deck.add(10);
            deck.add(10);
            deck.add(10);
        }
        for (int l = 0; l < 4; l++) {
            deck.add(11);
            deck.add(11);
            deck.add(11);
            deck.add(11);
        }
    }
}
