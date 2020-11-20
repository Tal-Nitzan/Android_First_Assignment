package com.example.firstassignment;

import java.io.Serializable;

public class Card implements Serializable {
    int value;
    char sign; // h = hearts, d = diamonds, c = clubs, s = spade

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


    public static Card getWinningCard(Card card1, Card card2) {
        if (card1.getValue() >= card2.getValue()) {
            return card1;
        }
        else {
            return card2;
        }
    }

    static char calculateSign(int num) {
        switch (num) {
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
