package com.brianroper.androidweekly.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.brianroper.androidweekly.R;
import com.brianroper.androidweekly.presenters.VolumePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VolumeActivity extends AppCompatActivity implements VolumeView {

    @BindView(R.id.volume_toolbar)
    public Toolbar mToolbar;

    private VolumePresenter mVolumePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        ButterKnife.bind(this);

        initializePresenter();

        handleToolbarBehavior(mToolbar);
    }

    /**
     * handles toolbar behavior
     */
    public void handleToolbarBehavior(Toolbar toolbar){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Android Weekly");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * initializes the presenter for this activity and attaches it to the view
     */
    public void initializePresenter(){
        mVolumePresenter = new VolumePresenter(getApplicationContext());
        mVolumePresenter.attachView(this);
        mVolumePresenter.startVolumeService(getVolumeId());
    }

    public int getVolumeId(){
        Intent archiveIntent = getIntent();
        int id = archiveIntent.getIntExtra("id", 0);
        return id;
    }
}
