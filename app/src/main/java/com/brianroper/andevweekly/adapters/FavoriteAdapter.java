package com.brianroper.andevweekly.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brianroper.andevweekly.R;
import com.brianroper.andevweekly.model.Favorite;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by brianroper on 3/16/17.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>{

    private Context mContext;
    private RealmResults<Favorite> mRealmResults;

    public FavoriteAdapter(Context context) {
        mContext = context;
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View root = inflater.inflate(R.layout.volume_item, parent, false);
        final FavoriteViewHolder favoriteViewHolder = new FavoriteViewHolder(root);
        return favoriteViewHolder;
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.FavoriteViewHolder holder, int position) {
        holder.mVolumeHeadline.setText(mRealmResults.get(position).getHeadline());
        holder.mVolumeSource.setText(mRealmResults.get(position).getSource());
        holder.mVolumeSummary.setText(mRealmResults.get(position).getSummary());
    }

    @Override
    public int getItemCount() {
        return mRealmResults.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.volume_headline)
        public TextView mVolumeHeadline;
        @BindView(R.id.volume_source)
        public TextView mVolumeSource;
        @BindView(R.id.volume_summary)
        public TextView mVolumeSummary;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void getFavoriteDataFromRealm(){
        Realm realm;
        Realm.init(mContext);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfiguration);
        mRealmResults = realm.where(Favorite.class).findAll();
    }
}
