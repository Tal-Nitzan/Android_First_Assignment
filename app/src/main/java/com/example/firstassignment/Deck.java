package com.example.firstassignment;

import java.util.Random;

public class Deck {
    public static final int NUM_OF_SIGNS = 4;
    public static final int NUM_OF_CARDS = 13;
    public static final int TOTAL_NUM_CARDS = NUM_OF_CARDS*NUM_OF_SIGNS;
    private final Card[][] cards;
    private int drawedCards;

    public Deck() {
        cards = new Card[NUM_OF_SIGNS][NUM_OF_CARDS];
        initDeck();
        this.drawedCards = 0;
    }

    public void initDeck() {
        for (int i = 0; i < NUM_OF_SIGNS; i++) {
            for (int j = 0; j < NUM_OF_CARDS; j++) { // 0=h 1=d
                cards[i][j] = new Card(j+2, Card.calculateSign(i));
            }
        }
        this.shuffle();
    }

    public int getDrawedCards() {
        return drawedCards;
    }

    public void shuffle() {
        Random rand = new Random();
        int numOfSwaps = rand.nextInt((TOTAL_NUM_CARDS) + 1) + TOTAL_NUM_CARDS; // The swap will generate a number between TOTAL_NUM_CARDS..(TOTAL_NUM_CARDS*2).
        for (int i=0 ; i<numOfSwaps ; i++) {
            int cardSign1 = rand.nextInt(NUM_OF_SIGNS);
            int cardValue1 = rand.nextInt(NUM_OF_CARDS);
            int cardSign2 = rand.nextInt(NUM_OF_SIGNS);
            int cardValue2 = rand.nextInt(NUM_OF_CARDS);
            Card temp = cards[cardSign1][cardValue1];
            cards[cardSign1][cardValue1] = cards[cardSign2][cardValue2];
            cards[cardSign2][cardValue2] = temp;
        }
    }

    public boolean isEmpty() {
        return drawedCards == (NUM_OF_CARDS*NUM_OF_SIGNS);
    }
    Card drawCard() {
        drawedCards++;
        return cards[((drawedCards-1)/NUM_OF_SIGNS)%NUM_OF_SIGNS][(drawedCards-1)%NUM_OF_CARDS];
    }

}
