package com.example.firstassignment;

public class Game {
    private Player p1;
    private Player p2;
    private Deck deck;


    public Game() {
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
        p1.setCurrentCard(deck.drawCard());
        p2.setCurrentCard(deck.drawCard());
        calculatePoint(p1.getCurrentCard(), p2.getCurrentCard());
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