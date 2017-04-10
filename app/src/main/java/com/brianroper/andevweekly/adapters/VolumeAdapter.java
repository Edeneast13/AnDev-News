package com.brianroper.andevweekly.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by brianroper on 1/15/17.
 */

public class VolumeAdapter extends RecyclerView.Adapter<VolumeAdapter.VolumeViewHolder> {

    private Context mContext;
    private RealmResults<Volume> mRealmResults;

    public VolumeAdapter(Context context) {
        mContext = context;
    }

    @Override
    public VolumeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View root = inflater.inflate(R.layout.volume_item, parent, false);
        final VolumeViewHolder volumeViewHolder = new VolumeViewHolder(root);

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVolumeListener(volumeViewHolder);
            }
        });

        root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setVolumeLongListener(volumeViewHolder);
                return true;
            }
        });
        return volumeViewHolder;
    }

    @Override
    public void onBindViewHolder(VolumeViewHolder holder, int position) {
        holder.mVolumeHeadline.setText(mRealmResults.get(position).getHeadline());
        holder.mVolumeSource.setText(mRealmResults.get(position).getSource());
        holder.mVolumeSummary.setText(mRealmResults.get(position).getSummary());
    }

    @Override
    public int getItemCount() {
        return mRealmResults.size();
    }

    public class VolumeViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.volume_headline)
        public TextView mVolumeHeadline;
        @BindView(R.id.volume_source)
        public TextView mVolumeSource;
        @BindView(R.id.volume_summary)
        public TextView mVolumeSummary;

        public VolumeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * retrieves volume data from realm database
     */
    public RealmResults<Volume> getVolumeDataFromRealm(int issue){
        Realm realm;
        Realm.init(mContext);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfiguration);
        mRealmResults = realm.where(Volume.class).equalTo("issue", issue).findAll();
        Log.i("RealmResult Size: ", mRealmResults.size() + "");
        return mRealmResults;
    }

    /**
     * handles click behavior for recycler view item
     */
    public void setVolumeListener(VolumeViewHolder holder){
        if(Util.activeNetworkCheck(mContext)){
            int position = holder.getAdapterPosition();
            new FinestWebView.Builder(mContext).show(mRealmResults.get(position).getLink());
        }
        else{Util.noActiveNetworkToast(mContext);}
    }

    public void setVolumeLongListener(VolumeViewHolder holder){
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
                Favorite favorite = realm
                        .createObject(Favorite.class, mRealmResults.get(position).getId());
                favorite.setIssue(mRealmResults.get(position).getIssue());
                favorite.setLink(mRealmResults.get(position).getLink());
                favorite.setSummary(mRealmResults.get(position).getSummary());
                favorite.setHeadline(mRealmResults.get(position).getHeadline());
                favorite.setSource(mRealmResults.get(position).getSource());
            }
        });
        realm.close();
    }
}
