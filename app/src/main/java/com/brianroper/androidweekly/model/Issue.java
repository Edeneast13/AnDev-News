package com.brianroper.androidweekly.model;

import java.util.ArrayList;

/**
 * Created by brianroper on 1/13/17.
 */

public class Issue {
    private ArrayList<Volume> volumeList;

    public ArrayList<Volume> getVolumeList() {
        return volumeList;
    }

    public void setVolumeList(ArrayList<Volume> volumeList) {
        this.volumeList = volumeList;
    }
}
