package com.brianroper.andevweekly.presenters;

import com.brianroper.andevweekly.views.FavoriteView;

/**
 * Created by brianroper on 3/16/17.
 */

public class FavoritePresenter implements Presenter<FavoriteView> {

    private FavoriteView mFavoriteView;

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void attachView(FavoriteView favoriteView) {
        mFavoriteView = favoriteView;
    }

    public void getRealmData(){
        mFavoriteView.getDataFromRealm();
    }
}
