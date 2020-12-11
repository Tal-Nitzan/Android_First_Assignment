package com.example.firstassignment;

import java.util.ArrayList;
import java.util.Comparator;

public class TopTen {
        private ArrayList<Record> records = new ArrayList<>();

        public TopTen() { }

        public TopTen(ArrayList<Record> records) {
            this.records = records;
        }

        public ArrayList<Record> getRecords() {
            return records;
        }

        public TopTen setRecords(ArrayList<Record> records) {
            this.records = records;
            return this;
    }
        public void sortTopTen() {
            this.records.sort(new Comparator<Record>() {

                @Override
                public int compare(Record o1, Record o2) {
                    return o2.getScore().compareTo(o1.getScore());
                }
            });
        }
}
