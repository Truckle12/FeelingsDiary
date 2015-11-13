package com.example.ssteffanus.feelingsdiary;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * Created by ssteffanus on 11/13/2015.
 */
public class JournalClass {
    private ArrayList<EntryClass> entries;
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

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
