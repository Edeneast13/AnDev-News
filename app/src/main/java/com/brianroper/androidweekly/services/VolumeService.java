package com.brianroper.androidweekly.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.brianroper.androidweekly.model.Archive;
import com.brianroper.androidweekly.model.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Stack;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by brianroper on 1/12/17.
 */

public class VolumeService extends Service {

    private int mVolumeId;

    @Override
    public void onCreate() {
        super.onCreate();
        getVolumeDataFromRealm();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mVolumeId = intent.getExtras().getInt("id" ,0);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void getVolumeDataFromRealm(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                Realm realm;
                Realm.init(getApplicationContext());
                RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .build();
                realm = Realm.getInstance(realmConfiguration);
                RealmResults<Archive> results = realm.where(Archive.class).findAll();
                Archive volumeArchive = results.get(mVolumeId);

                getVolumeData(volumeArchive);
            }
        };
        thread.start();
    }

    public void getVolumeData(final Archive volumeArchive){
        try{
            Constants constants = new Constants();

            Document document = Jsoup
                    .connect(constants.ARCHIVE_VOLUME_BASE_URL + "/issues/issue-239")
                    .get();

            //html format changes after issue 102
            //if(volumeArchive.getId() >= 103){
                Elements body = document.select("p");
                Elements source = document.getElementsByClass("main-url");
                Elements headline = document.getElementsByClass("article-headline");
                cleanVolumeData(body, source, headline);
            //}
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void cleanVolumeData(Elements body, Elements source, Elements headline){
        Stack<String> volumeBody = new Stack<>();
        Stack<String> volumeSource = new Stack<>();
        Stack<String> volumeHeadline = new Stack<>();
        Stack<String> volumeHeadlineLink = new Stack<>();

        for (Element element : body){
            volumeBody.push(element.text());
        }

        for (Element element : source){
            volumeSource.push(element.text());
        }

        for (Element element : headline){
            volumeHeadline.push(element.text());
            volumeHeadlineLink.push(element.attr("href"));
        }

        Log.i("BodySize: ", volumeBody.size() + "");
        Log.i("SourceSize: ", volumeSource.size() + "");
        Log.i("LinkSize: ", volumeHeadlineLink.size() + "");
        Log.i("HeadlineSize: ", volumeHeadline.size() + "");

        
    }
}
