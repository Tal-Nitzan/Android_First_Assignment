package com.example.firstassignment;

import java.io.Serializable;

public class Player implements Serializable {
    String name;
    int score;
    Card currentCard;

    Player(String name) {
        this.score = 0;
        this.name = name;
    }

    void addPoint() {
        this.score++;
    }

    String getName() {return this.name;};

    int getScore() {
        return score;
    }

}