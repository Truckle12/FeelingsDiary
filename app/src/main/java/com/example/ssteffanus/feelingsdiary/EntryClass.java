    package com.example.ssteffanus.feelingsdiary;

    /**
     * Created by ssteffanus on 11/13/2015.
     */
    public class EntryClass {
        private String date;
        private String time;
        private String mood;

        public EntryClass () {
            this.date = null;
            this.time = null;
            this.mood = null; 
        }

        public EntryClass (String date, String time, String mood) {
            this.date = date;
            this.time = time;
            this.mood = mood;
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
    }
