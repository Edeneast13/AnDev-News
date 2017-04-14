package com.brianroper.andevweekly.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brianroper.andevweekly.R;
import com.brianroper.andevweekly.model.Favorite;
import com.brianroper.andevweekly.model.Volume;
import com.brianroper.andevweekly.utils.Util;
import com.thefinestartist.finestwebview.FinestWebView;

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
        View root = inflater.inflate(R.layout.favorite_item, parent, false);
        final FavoriteViewHolder favoriteViewHolder = new FavoriteViewHolder(root);

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Util.activeNetworkCheck(mContext)){
                    setFavoriteListener(favoriteViewHolder);
                }
                else{Util.noActiveNetworkToast(mContext);}
            }
        });

        favoriteViewHolder.mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFavoriteButtonListener(favoriteViewHolder);
            }
        });

        return favoriteViewHolder;
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.FavoriteViewHolder holder, int position) {
        holder.mFavoriteHeadline.setText(mRealmResults.get(position).getHeadline());
        holder.mFavoriteSummary.setText(mRealmResults.get(position).getSummary()
                + " " + mRealmResults.get(position).getSource());
    }

    @Override
    public int getItemCount() {
        return mRealmResults.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.favorite_headline)
        public TextView mFavoriteHeadline;
        @BindView(R.id.favorite_summary)
        public TextView mFavoriteSummary;
        @BindView(R.id.favorite_add)
        public ImageButton mFavoriteButton;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public RealmResults<Favorite> getFavoriteDataFromRealm(){
        Realm realm;
        Realm.init(mContext);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfiguration);
        mRealmResults = realm.where(Favorite.class).findAll();
        return mRealmResults;
    }

    /**
     * handles click behavior for recycler view item
     */
    public void setFavoriteListener(FavoriteAdapter.FavoriteViewHolder holder){
        if(Util.activeNetworkCheck(mContext)){
            int position = holder.getAdapterPosition();
            new FinestWebView.Builder(mContext).show(mRealmResults.get(position).getLink());
        }
        else{Util.noActiveNetworkToast(mContext);}
    }

    public void setFavoriteButtonListener(FavoriteViewHolder holder){
        final int position = holder.getAdapterPosition();
        Realm realm;
        Realm.init(mContext);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfiguration);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Favorite.class)
                        .equalTo("id", mRealmResults.get(position).getId())
                        .findFirst()
                        .deleteFromRealm();
                Volume volume = realm.where(Volume.class)
                        .equalTo("id", mRealmResults.get(position).getId())
                        .findFirst();
                volume.setSaved(false);
                realm.copyToRealmOrUpdate(volume);
                notifyDataSetChanged();
            }
        });
    }
}
