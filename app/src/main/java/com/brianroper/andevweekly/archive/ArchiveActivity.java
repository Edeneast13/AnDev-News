package com.brianroper.andevweekly.archive;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.view.View;

import com.brianroper.andevweekly.R;
import com.brianroper.andevweekly.model.Constants;
import com.brianroper.andevweekly.utils.Util;
import com.brianroper.andevweekly.about.AboutActivity;
import com.brianroper.andevweekly.favorites.FavoriteFragment;
import com.thefinestartist.finestwebview.FinestWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArchiveActivity extends AppCompatActivity {

    @BindView(R.id.archive_toolbar)
    public Toolbar mToolbar;
    @BindView(R.id.bottom_navigation_view)
    public BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        ButterKnife.bind(this);

        handleToolbarBehavior(mToolbar);
        setBottomNavigationViewListener();
    }

    /**
     * handles toolbar behavior
     */
    public void handleToolbarBehavior(Toolbar toolbar){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Android Weekly");
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHomePage();
            }
        });
    }

    @Override
    public void onBackPressed() {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about :
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * shows the android weekly home page in a web view
     */
    public void showHomePage(){
        if(Util.activeNetworkCheck(getApplicationContext())){
            Constants constants = new Constants();
            new FinestWebView.Builder(getApplicationContext()).show(constants.ARCHIVE_VOLUME_BASE_URL);
        }
        else{Util.noActiveNetworkToast(getApplicationContext());}
    }

    public void setBottomNavigationViewListener(){
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()){
                    case R.id.action_news:
                        selectedFragment = ArchiveFragment.newInstance();
                        break;

                    case R.id.action_favorites:
                        selectedFragment = FavoriteFragment.newInstance();
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ArchiveFragment.newInstance());
        transaction.commit();
    }
}
