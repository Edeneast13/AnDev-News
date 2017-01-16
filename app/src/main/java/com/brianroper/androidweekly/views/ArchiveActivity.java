package com.brianroper.androidweekly.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.brianroper.androidweekly.R;
import com.brianroper.androidweekly.adapters.ArchiveAdapter;
import com.brianroper.androidweekly.model.ArchiveEvent;
import com.brianroper.androidweekly.model.Constants;
import com.brianroper.androidweekly.presenters.ArchivePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArchiveActivity extends AppCompatActivity implements ArchiveView {

    @BindView(R.id.archive_toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.archive_recycler)
    public RecyclerView mRecyclerView;

    private ArchivePresenter mArchivePresenter;
    private ArchiveAdapter mArchiveAdapter;
    private EventBus mEventBus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

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
        getSupportActionBar().setTitle("Android Weekly");
    }

    /**
     * initializes the presenter for this activity and attaches it to the view
     */
    @Override
    public void initializePresenter(){
        mArchivePresenter = new ArchivePresenter(getApplicationContext());
        mArchivePresenter.attachView(this);
        mArchivePresenter.startArchiveService();
    }

    /**
     * initializes the views adapter
     */
    @Override
    public void initializeAdapter(){
        mArchiveAdapter = new ArchiveAdapter(getApplicationContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mArchiveAdapter);
    }

    /**
     * update data in the adapter
     */
    @Override
    public void handleAdapterDataSet(){
        mArchiveAdapter.getArchiveDataFromRealm();
        mArchiveAdapter.notifyDataSetChanged();
    }

    /**
     * watches for ArchiveEvent message data change throughout app
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onArchiveMessageEvent(ArchiveEvent archiveEvent){
        Constants constants = new Constants();
        if(archiveEvent.message == constants.ARCHIVE_EVENT_FINISHED) {
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
}
