package com.brianroper.androidweekly.views;

import android.support.v7.widget.Toolbar;

/**
 * Created by brianroper on 1/12/17.
 */

public interface VolumeView extends View {
    void handleToolbarBehavior(Toolbar toolbar);
    void initializePresenter();
    int getVolumeId();
    void initializeAdapter();
    void handleAdapterDataSet();
    void setTitleTextView(String issue);
}
