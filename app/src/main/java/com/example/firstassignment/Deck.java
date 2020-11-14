package com.example.firstassignment;

public class Deck {
    protected Card[][] cards;
    protected int drawedCards;

    public Deck() {
        cards = new Card[4][12];
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards[0].length; j++) { // 0=h 1=d
                cards[i][j] = new Card(j+2, Card.calculateSign(i));
            }
        }
        this.shuffle();
        this.drawedCards = 0;
    }

    void shuffle() {
        int numOfSwaps = ((int)(Math.random() * 48)) + 52; // The swap will generate a number between 52..100.
        for (int i=0 ; i<numOfSwaps ; i++) {
            int cardSign1 = (int)(Math.random() * 4);
            int cardValue1 = (int)(Math.random() * 12);
            int cardSign2 = (int)(Math.random() * 4);
            int cardValue2 = (int)(Math.random() * 12);
            Card temp = cards[cardSign1][cardValue1];
            cards[cardSign1][cardValue1] = cards[cardSign2][cardValue2];
            cards[cardSign2][cardValue2] = temp;
        }
    }
    Card drawCard() {
        drawedCards++;
        return cards[((drawedCards-1)/4)%4][(drawedCards-1)%12];
    }

}
