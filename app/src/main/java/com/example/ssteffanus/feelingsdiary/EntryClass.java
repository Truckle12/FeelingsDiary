    package com.example.ssteffanus.feelingsdiary;

    /**
     * Created by ssteffanus on 11/13/2015.
     */
    public class EntryClass {
        private String date;
        private String time;
        private String mood;
        private StringBuffer entry;

        public EntryClass () {
            this.date = null;
            this.time = null;
            this.mood = null;
            this.entry = null;
        }

        public EntryClass (String date, String time, String mood) {
            this.date = date;
            this.time = time;
            this.mood = mood;
            this.entry = null;
        }

        public String getEntryDate() {
            return this.date;
        }
        public String getEntryTime() {
            return this.time;
        }
        public String getEntryMood() {
            return this.mood;
        }
        public StringBuffer getEntryText() {return this.entry;}
    }
