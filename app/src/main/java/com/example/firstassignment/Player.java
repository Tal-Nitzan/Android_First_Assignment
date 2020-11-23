package com.example.firstassignment;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int score;
    private Card currentCard;

    public Player(String name) {
        this.score = 0;
        this.name = name;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public void addPoint() {
        this.score++;
    }

    public String getName() {return this.name;};

    public int getScore() {
        return score;
    }

}