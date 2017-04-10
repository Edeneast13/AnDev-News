package com.brianroper.andevweekly.views;

/**
 * Created by brianroper on 1/12/17.
 */

public interface VolumeView extends View {
    int getVolumeId();
    void getDataFromRealm();
    void setTitleTextView(String issue);
}
