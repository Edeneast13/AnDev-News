package com.brianroper.androidweekly.presenters;

import android.content.Context;
import android.content.Intent;

import com.brianroper.androidweekly.services.ArchiveService;
import com.brianroper.androidweekly.views.ArchiveView;

/**
 * Created by brianroper on 1/9/17.
 */

public class ArchivePresenter implements Presenter<ArchiveView> {

    private ArchiveView mArchiveView;
    private Context mContext;

    public ArchivePresenter(Context context) {
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
    public void attachView(ArchiveView view) {
        this.mArchiveView = view;
    }

    /**
     * starts the ArchiveService
     */
    public void startArchiveService(){
        Intent archiveService = new Intent(mContext, ArchiveService.class);
        mContext.startService(archiveService);
    }
}
