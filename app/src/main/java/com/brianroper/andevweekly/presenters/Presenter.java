package com.brianroper.andevweekly.presenters;

import com.brianroper.andevweekly.views.View;

/**
 * Created by brianroper on 1/9/17.
 */

public interface Presenter<T extends View> {
    void onCreate();
    void onStart();
    void onStop();
    void onPause();
    void attachView(T view);
}
