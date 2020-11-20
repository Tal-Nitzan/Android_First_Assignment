package com.example.firstassignment;

import android.content.Intent;
import android.util.Log;

public class Game {
    private Player p1;
    private Player p2;
    private Deck deck;


    Game() {
        this.p1 = new Player("Tal");
        this.p2 = new Player("Tomchin");
        this.deck = new Deck();
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public boolean isDone() {
        return deck.isEmpty();
    }

    public Player getWinner() {
        if (p1.getScore() >= p2.getScore()) {
            return p1;
        } else {
            return p2;
        }
    }



    public void makeStep() {
        p1.currentCard = deck.drawCard();
        p2.currentCard = deck.drawCard();
        calculatePoint(p1.currentCard, p2.currentCard);
    }


    private void calculatePoint(Card card1, Card card2) {
        if (card1.getValue() > card2.getValue()) {
            p1.addPoint();
        }
        else if (card1.getValue() < card2.getValue()) {
            p2.addPoint();
        } else {
            p1.addPoint();
            p2.addPoint();
        }
    }
}



//                if (deck.drawedCards == 52) {
//                        if (p1.getScore() >= p2.getScore()) {
//                        openWinnerActivity(p1);
//                        } else if (p1.getScore() < p2.getScore()) {
//        openWinnerActivity(p2);
//        }
//        return;
//        }
//        p1.currentCard = deck.drawCard();
//        p2.currentCard = deck.drawCard();
//        int id1 = getResources().getIdentifier(String.format("card_%s_%c", p1.currentCard.getValue(), p1.currentCard.getSign()), "drawable", getPackageName());
//        int id2 = getResources().getIdentifier(String.format("card_%s_%c", p2.currentCard.getValue() , p2.currentCard.getSign()), "drawable", getPackageName());
//        Log.d("pttt2", "" + id1);
//        Log.d("pttt2", "" + id2);
//        IMG_leftCard.setImageResource(id1);
//        IMG_rightCard.setImageResource(id2);
//        calculatePoint(p1.currentCard, p2.currentCard);