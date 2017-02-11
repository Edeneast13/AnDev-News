package com.brianroper.androidweekly.presenters;

import android.content.Context;
import android.content.Intent;

import com.brianroper.androidweekly.model.Constants;
import com.brianroper.androidweekly.services.ArchiveService;
import com.brianroper.androidweekly.utils.Util;
import com.brianroper.androidweekly.views.ArchiveView;
import com.thefinestartist.finestwebview.FinestWebView;

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
        if(Util.activeNetworkCheck(mContext)==true) {
            Intent archiveService = new Intent(mContext, ArchiveService.class);
            mContext.startService(archiveService);
        }
        else{Util.noActiveNetworkToast(mContext);}
    }

    /**
     * opens the android weekly home page
     */
    public void showHomePage(){
        Constants constants = new Constants();
        new FinestWebView.Builder(mContext).show(constants.ARCHIVE_BASE_URL);
    }
}
