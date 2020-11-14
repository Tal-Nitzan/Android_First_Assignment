package com.example.firstassignment;

public class Card {
    int value;
    char sign; // h = hearts, d = diamonds, c = clubs, s = spade
    int imgId;

    Card(int value, char sign) {
        this.setValue(value);
        this.setSign(sign);
    }

    void setValue(int value) {
        this.value = value;
    }

    int getValue() {
        return this.value;
    }

    void setSign(char sign) {
        this.sign = sign;
    }

    int getSign() {
        return this.sign;
    }

    static char calculateSign(int num) {
        switch (num) {
            case 0:
                return 'h';
            case 1:
                return 'd';
            case 2:
                return 'c';
            case 3:
                return 's';
            default:
                return 'h';
        }
    }
}
