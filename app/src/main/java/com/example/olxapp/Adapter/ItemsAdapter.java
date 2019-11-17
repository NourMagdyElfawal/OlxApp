package com.example.olxapp.Adapter;


import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.olxapp.Interface.Items;
import com.example.olxapp.R;
import com.example.olxapp.recycleViewViewHolder.ItemsViewHolder;
import com.example.olxapp.ui.ItemsRecyclerFragment.ItemsRecyclerFragment;
import com.example.olxapp.ui.addAdv.AddAdvFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsViewHolder> {
    private static final String TAG = "ItemsAdapter";
    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<Items> mSportList;

    public ItemsAdapter(List<Items> sportList) {
        mSportList = sportList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mSportList != null && mSportList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mSportList != null && mSportList.size() > 0) {
            return mSportList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Items> sportList) {
        mSportList.addAll(sportList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onEmptyViewRetryClick();
    }

    public class ViewHolder extends ItemsViewHolder {

        @BindView(R.id.thumbnail)
        ImageView coverImageView;

        @BindView(R.id.title)
        TextView titleTextView;

        @BindView(R.id.newsTitle)
        TextView newsTextView;

        @BindView(R.id.newsInfo)
        TextView infoTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            coverImageView.setImageDrawable(null);
            titleTextView.setText("");
            newsTextView.setText("");
            infoTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final Items mSport = mSportList.get(position);

                if (mSport.getImageUrl() != null) {
                    Glide.with(itemView.getContext())
                            .load(mSport.getImageUrl())
                            .into(coverImageView);
                }

            if (mSport.getTitle() != null) {
                titleTextView.setText(mSport.getTitle());
            }

            if (mSport.getSubTitle() != null) {
                newsTextView.setText(mSport.getSubTitle());
            }

            if (mSport.getInfo() != null) {
                infoTextView.setText(mSport.getInfo());
            }

//                itemView.setOnClickListener(v -> {
//                    if (mSport.getImageUrl() != null) {
//                        try {
//                            Intent intent = new Intent();
//                            intent.setAction(Intent.ACTION_VIEW);
//                            intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                            intent.setData(Uri.parse(mSport.getImageUrl()));
//                            itemView.getContext().startActivity(intent);
//                        } catch (Exception e) {
//                            Log.e(TAG, "onClick: Image url is not correct");
//                        }
//                    }
//                });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    AddAdvFragment addAdvFragment = new AddAdvFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, addAdvFragment).commit();


                }
            });
        }
    }

    public class EmptyViewHolder extends ItemsViewHolder {

        @BindView(R.id.tv_message)
        TextView messageTextView;
        @BindView(R.id.buttonRetry)
        TextView buttonRetry;

        EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            buttonRetry.setOnClickListener(v -> mCallback.onEmptyViewRetryClick());
        }

        @Override
        protected void clear() {

        }

    }
}