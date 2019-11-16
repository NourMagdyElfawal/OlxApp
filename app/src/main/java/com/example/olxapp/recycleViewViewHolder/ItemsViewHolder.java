package com.example.olxapp.recycleViewViewHolder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class ItemsViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPosition;

    public ItemsViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
