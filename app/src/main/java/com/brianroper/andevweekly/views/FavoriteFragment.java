package com.brianroper.andevweekly.views;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.view.View;

import com.brianroper.andevweekly.R;
import com.brianroper.andevweekly.adapters.FavoriteAdapter;
import com.brianroper.andevweekly.adapters.VolumeAdapter;
import com.brianroper.andevweekly.model.Favorite;
import com.brianroper.andevweekly.model.RecyclerViewDivider;
import com.brianroper.andevweekly.presenters.FavoritePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteFragment extends Fragment implements FavoriteView {

    @BindView(R.id.favorite_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.favorite_recycler)
    RecyclerView mRecyclerView;

    private FavoritePresenter mFavoritePresenter;
    private FavoriteAdapter mFavoriteAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mFavoritePresenter = new FavoritePresenter();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_favorite, container, false);

        ButterKnife.bind(this, root);

        initializePresenter();
        initializeAdapter();

        return root;
    }

    public static FavoriteFragment newInstance(){
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        return favoriteFragment;
    }

    public void initializeAdapter(){
        mFavoriteAdapter = new FavoriteAdapter(getActivity());
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.addItemDecoration(new RecyclerViewDivider(getActivity()));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mFavoriteAdapter);
    }

    public void initializePresenter(){
        mFavoritePresenter.attachView(this);
    }

    @Override
    public void finish() {}
}
