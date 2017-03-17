package com.brianroper.andevweekly.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.brianroper.andevweekly.R;
import com.brianroper.andevweekly.adapters.VolumeAdapter;
import com.brianroper.andevweekly.model.Constants;
import com.brianroper.andevweekly.model.RecyclerViewDivider;
import com.brianroper.andevweekly.model.VolumeEvent;
import com.brianroper.andevweekly.presenters.VolumePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class VolumeActivity extends AppCompatActivity implements VolumeView {

    @BindView(R.id.volume_toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.volume_recycler)
    public RecyclerView mRecyclerView;
    @BindView(R.id.volume_title)
    public TextView mTitleTextView;

    private VolumePresenter mVolumePresenter;
    private VolumeAdapter mVolumeAdapter;
    private LinearLayoutManager mLayoutManager;
    private EventBus mEventBus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        ButterKnife.bind(this);

        initializePresenter();
        initializeAdapter();

        handleToolbarBehavior(mToolbar);
        handleAdapterDataSet();
    }

    /**
     * handles toolbar behavior
     */
    @Override
    public void handleToolbarBehavior(Toolbar toolbar){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setLogo(R.drawable.toolbarlogo);
    }

    /**
     * initializes the presenter for this activity and attaches it to the view
     */
    @Override
    public void initializePresenter(){
        mVolumePresenter = new VolumePresenter(getApplicationContext());
        mVolumePresenter.attachView(this);
        mVolumePresenter.startVolumeService(getVolumeId());
        if(getVolumeId() <= 102){
            Toasty.info(this,
                    "This issue isn't supported just yet",
                    Toast.LENGTH_LONG,
                    true).show();
        }
    }

    /**
     * gets the current volume id from the received intent
     */
    @Override
    public int getVolumeId(){
        Intent archiveIntent = getIntent();
        int id = archiveIntent.getIntExtra("id", 0)-1;
        setTitleTextView(id+1 + "");
        return id;
    }

    /**
     * initializes the activities adapter
     */
    @Override
    public void initializeAdapter(){
        mVolumeAdapter = new VolumeAdapter(getApplicationContext());
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.addItemDecoration(new RecyclerViewDivider(getApplicationContext()));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mVolumeAdapter);
    }

    /**
     * handles the data set of the attached adapter
     */
    @Override
    public void handleAdapterDataSet(){
        mVolumeAdapter.getVolumeDataFromRealm(getVolumeId());
        mVolumeAdapter.notifyDataSetChanged();
    }

    /**
     * watches for VolumeEvent message data change throughout app
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onVolumeMessageEvent(VolumeEvent volumeEvent){
        Constants constants = new Constants();
        if(volumeEvent.message == constants.VOLUME_EVENT_FINISHED) {
            handleAdapterDataSet();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mEventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mEventBus.unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * sets the text for the title textview
     */
    @Override
    public void setTitleTextView(String issue){
        mTitleTextView.setText(issue);
    }
}
