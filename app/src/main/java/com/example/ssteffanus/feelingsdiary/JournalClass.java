package com.example.ssteffanus.feelingsdiary;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ssteffanus on 11/13/2015.
 */
public class JournalClass {
    private HashMap<String, ArrayList<EntryClass>> entries;   /* October 8, 2015 @ 2:50 pm */
    private Bitmap userIcon;
    private String password;

    public JournalClass () {
        entries = null;
        userIcon = MainActivity.defaultImage;
        password = null;
    }

    public String getPassword() {
        return this.password;
    }
    public HashMap<String, ArrayList<EntryClass>> getEntries() {
        return this.entries;
    }

    public void setEntries(HashMap <String,ArrayList<EntryClass>> entryMap) {
        this.entries = entryMap;
    }
    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
