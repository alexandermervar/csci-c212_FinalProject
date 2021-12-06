package edu.iu.c212.places.games.blackjack;

public class BlackjackDealer extends BlackJackParticipant{

    private int shownCard = 0;
    private int dealerBest;

    public int getShownCard() {
        return shownCard;
    }
    public int getDealerBest() {
        return dealerBest;
    }
    public String getPartialHand() {
        return shownCard + " + ???";
    }

    public BlackjackDealer() {
        dealerBest = -1;
        handTotals = new int[2];
        hit();
        hit();
    }

    @Override
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

    @Override
    public void hit() {
        int selectedCardIndex = (int) (Math.random() * cards.size());

        int selectedCard = cards.get(selectedCardIndex);
        cards.remove(selectedCardIndex);

        if (shownCard == 0) {
            shownCard = selectedCard;
        }
        if (selectedCard == 11) {
            handTotals[0] += 1;
            handTotals[1] += 11;
        } else {
            handTotals[0] += selectedCard;
            handTotals[1] += selectedCard;
        }
    }

    public void play() {
        
        while (getBestTotal() < 17) {
            hit();
        }
        if (getBestTotal() > 21) {
            dealerBest = -1;
        }
        else {
        dealerBest = getBestTotal();
        }
    }
}
