package com.brianroper.androidweekly.presenters;

import android.content.Context;
import android.content.Intent;

import com.brianroper.androidweekly.services.ArchiveService;
import com.brianroper.androidweekly.views.ArticleView;

/**
 * Created by brianroper on 1/9/17.
 */

public class ArticlePresenter implements Presenter<ArticleView> {

    private ArticleView mArticleView;
    private Context mContext;

    public ArticlePresenter(Context context) {
        mContext = context;
    }

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

    /**
     * starts the ArchiveService
     */
    public void startArchiveService(){
        Intent archiveService = new Intent(mContext, ArchiveService.class);
        mContext.startService(archiveService);
    }
}
