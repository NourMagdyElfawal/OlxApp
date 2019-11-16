package com.example.olxapp.ui.ItemsRecyclerFragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.olxapp.R;

public class ItemsRecyclerFragment extends Fragment {

    private ItemsRecyclerViewModel mViewModel;

    public static ItemsRecyclerFragment newInstance() {
        return new ItemsRecyclerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.items_recycler_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ItemsRecyclerViewModel.class);
        // TODO: Use the ViewModel
    }

}
