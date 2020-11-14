package com.example.firstassignment;

public class Player {
    int score;
    Card currentCard;

    Player() {
        this.score = 0;
    }

    void addPoint() {
        this.score++;
    }

    int getScore() {
        return score;
    }

}
