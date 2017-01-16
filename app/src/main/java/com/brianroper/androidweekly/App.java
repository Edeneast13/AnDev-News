package com.brianroper.androidweekly;

import android.app.Application;
import android.content.Intent;

import com.brianroper.androidweekly.services.ArchiveService;

/**
 * Created by brianroper on 1/15/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //startService(new Intent(this, ArchiveService.class));
    }
}
