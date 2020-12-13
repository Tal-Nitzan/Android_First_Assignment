package com.example.firstassignment;

public class Record {
    private String name = "";
    private int score = 0;
    private double lat;
    private double lon;

    public Record() { }

    public Record(String name, int score, double lat, double lon) {
        this.name = name;
        this.score = score;
        this.lat = lat;
        this.lon = lon;
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

    public double getLat() {
        return lat;
    }

    public Record setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public Record setLon(double lon) {
        this.lon = lon;
        return this;
    }
}
