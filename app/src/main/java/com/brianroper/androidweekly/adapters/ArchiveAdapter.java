package com.brianroper.androidweekly.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by brianroper on 1/11/17.
 */

public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder> {

    @Override
    public ArchiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ArchiveViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ArchiveViewHolder extends RecyclerView.ViewHolder{

        public ArchiveViewHolder(View itemView) {
            super(itemView);
        }
    }
}
