package com.brianroper.androidweekly.presenters;

import com.brianroper.androidweekly.views.ArticleView;

/**
 * Created by brianroper on 1/9/17.
 */

public class ArticlePresenter implements Presenter<ArticleView> {

    private ArticleView mArticleView;

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
    public void attachView(ArticleView view) {
        this.mArticleView = view;
    }

    /**
     * retrieves article data from realm database
     */
    public void getArticlesFromRealm(){

    }

    /**
     * handles article recycler on click listener behaviour
     */
    public void articleClickListener(){

    }

    public void setRecyclerAdapter(){

    }
}
