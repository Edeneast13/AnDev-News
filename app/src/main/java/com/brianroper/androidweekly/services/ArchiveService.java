package com.brianroper.androidweekly.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.brianroper.androidweekly.model.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by brianroper on 1/9/17.
 */

public class ArchiveService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        getArticleData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * retrieves archive issues list from androidweekly.net
     */
    public void getArticleData(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                try{
                    Constants constants = new Constants();

                    Document document = Jsoup.connect(constants.ARCHIVE_BASE_URL).get();

                    String title = document.title();
                    Elements elements = document.select("a[href]");

                    Log.i("Title: ", title);

                    for (Element element : elements){
                        Log.i("Link: ", element.attr("href"));
                        Log.i("Text: ", element.text());
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
