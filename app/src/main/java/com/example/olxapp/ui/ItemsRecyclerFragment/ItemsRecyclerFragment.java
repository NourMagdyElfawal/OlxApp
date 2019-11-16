package com.example.olxapp.ui.ItemsRecyclerFragment;




import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.olxapp.Adapter.HomeAdapter;
import com.example.olxapp.Adapter.ItemsAdapter;
import com.example.olxapp.Interface.Items;
import com.example.olxapp.R;
import com.example.olxapp.design.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemsRecyclerFragment extends Fragment {

    private ItemsRecyclerViewModel mViewModel;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    ItemsAdapter mSportAdapter;

    LinearLayoutManager mLayoutManager;



    public static ItemsRecyclerFragment newInstance() {
        return new ItemsRecyclerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.items_recycler_fragment, container, false);


        ButterKnife.bind(this,view);
        setUp();
        return view;
    }

    private void setUp() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        Drawable dividerDrawable = ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.divider_drawable);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        mSportAdapter = new ItemsAdapter(new ArrayList<>());

        prepareDemoContent();
    }

    private void prepareDemoContent() {
//        CommonUtils.showLoading(getActivity());
        new Handler().postDelayed(() -> {
            //prepare data and show loading
//            CommonUtils.hideLoading();
            ArrayList<Items> mSports = new ArrayList<>();
            String[] sportsList = getResources().getStringArray(R.array.sports_titles);
            String[] sportsInfo = getResources().getStringArray(R.array.sports_info);
            String[] sportsImage = getResources().getStringArray(R.array.sports_images);
            for (int i = 0; i < sportsList.length; i++) {
                mSports.add(new Items(sportsImage[i], sportsInfo[i], "News", sportsList[i]));
            }
            mSportAdapter.addItems(mSports);
            mRecyclerView.setAdapter(mSportAdapter);
        }, 2000);

    }
        @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ItemsRecyclerViewModel.class);
        // TODO: Use the ViewModel
    }

}
