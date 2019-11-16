package com.example.olxapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.olxapp.Interface.category;
import com.example.olxapp.R;
import com.example.olxapp.ui.ItemsRecyclerFragment.ItemsRecyclerFragment;
import com.example.olxapp.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private Context mContext;
    private List<category> categoryList;
    private List<category> imageTitleList;

    public <E> HomeAdapter(ArrayList<E> es) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView thumbnail;
        public RelativeLayout vRoot;

        public MyViewHolder(View view) {
            super(view);
            vRoot = (RelativeLayout) view.findViewById(R.id.rl_root);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public HomeAdapter(Context mContext, List<category> categoryList, List<category> imageTitleList) {
        this.mContext = mContext;
        this.categoryList = categoryList;
        this.imageTitleList=imageTitleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//        Glide.with(mContext).load(categoryList.get(position).getImageUrl()).into(holder.thumbnail);

        final category category = imageTitleList.get(position);
        holder.title.setText(category.getName());
        Glide.with(mContext).load(categoryList.get(position).getImageUrl()).into(holder.thumbnail);
//        Glide.with(mContext).load(category.getImage()).into(holder.thumbnail);
        holder.vRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                ItemsRecyclerFragment itemsRecyclerFragment = new ItemsRecyclerFragment();
                activity.getSupportFragmentManager().beginTransaction().add(R.id.container, itemsRecyclerFragment).commit();

//                Fragment fragment = new tasks();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.content_frame, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

//                Log.e("imageTitleList","imageTitleList");
//                Intent intent = new Intent(mContext, DetailsActivity.class);
//                intent.putExtra("category", categoryList.get(position));
//                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


}
