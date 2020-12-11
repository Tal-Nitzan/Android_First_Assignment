package com.example.firstassignment;

public class Record {
    private String name = "";
    private int score = 0;

    public Record() { }

    public Record(String name, long date, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Record setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getScore() {
        return score;
    }

    public Record setScore(int score) {
        this.score = score;
        return this;
    }
}
