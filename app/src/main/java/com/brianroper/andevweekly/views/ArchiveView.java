package com.brianroper.andevweekly.views;

import android.support.v7.widget.Toolbar;

/**
 * Created by brianroper on 1/9/17.
 */

public interface ArchiveView extends View {
    void handleToolbarBehavior(Toolbar toolbar);
    void initializePresenter();
    void initializeAdapter();
    void handleAdapterDataSet();
}
