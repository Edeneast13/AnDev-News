package com.brianroper.androidweekly.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brianroper.androidweekly.R;
import com.brianroper.androidweekly.model.Archive;
import com.brianroper.androidweekly.views.VolumeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by brianroper on 1/11/17.
 */

public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder> {

    private Context mContext;
    private RealmResults<Archive> mRealmResults;

    public ArchiveAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ArchiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View root = inflater.inflate(R.layout.archive_item, parent, false);
        final ArchiveViewHolder archiveViewHolder = new ArchiveViewHolder(root);

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setArchiveListener(archiveViewHolder);
            }
        });

        return archiveViewHolder;
    }

    @Override
    public void onBindViewHolder(ArchiveViewHolder holder, int position) {
        holder.mArchiveTitleTextView.setText(mRealmResults.get(position).getTitle());
        holder.mArchiveDateTextView.setText(mRealmResults.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mRealmResults.size();
    }

    /**
     * view holder
     */
    public static class ArchiveViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.archive_title)
        public TextView mArchiveTitleTextView;
        @BindView(R.id.archive_date)
        public TextView mArchiveDateTextView;

        public ArchiveViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * retrieves archive data from realm
     */
    public void getArchiveDataFromRealm(){
        Realm realm;
        Realm.init(mContext);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(realmConfiguration);
        mRealmResults = realm.where(Archive.class).findAll();
    }

    public void setArchiveListener(ArchiveViewHolder holder){
        Intent volumeIntent = new Intent(mContext, VolumeActivity.class);
        volumeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        volumeIntent.putExtra("id", mRealmResults.get(holder.getAdapterPosition()).getId());
        mContext.startActivity(volumeIntent);
    }
}
