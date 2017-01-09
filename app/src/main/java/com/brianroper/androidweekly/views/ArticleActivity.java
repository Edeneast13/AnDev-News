package com.brianroper.androidweekly.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.brianroper.androidweekly.R;

import butterknife.BindView;

public class ArticleActivity extends AppCompatActivity {

    @BindView(R.id.article_toolbar)
    public Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        handleToolbarBehavior(mToolbar);
    }

    /**
     * handles toolbar behavior
     */
    public void handleToolbarBehavior(Toolbar toolbar){
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Android Weekly");
    }
}
