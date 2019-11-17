package com.example.olxapp.ui;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.olxapp.MainActivity;
import com.example.olxapp.R;
import com.example.olxapp.design.CurvedBottomNavigationView;
import com.example.olxapp.ui.addAdv.AddAdvFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class ItemDetailsFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ItemDetailsViewModel mViewModel;
    CurvedBottomNavigationView mView;
    MainActivity mainActivity;
    CardView card_telephone,card_confirm;


    public static ItemDetailsFragment newInstance() {
        return new ItemDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.item_details_fragment, container, false);

        card_telephone=root.findViewById(R.id.card_telephone);
        card_confirm=root.findViewById(R.id.card_AdvAddress);
        mView = root.findViewById(R.id.customBottomBar);
        mView.setOnNavigationItemSelectedListener(ItemDetailsFragment.this);
        ((MainActivity) Objects.requireNonNull(getActivity())).hideFloatingActionButton();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ItemDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //        switch (menuItem.getItemId()) {
//            case R.id.action_offers:
//
//                break;
//
//            case R.id.action_categories:
//
//                android.app.Fragment fmm = new Shop_Now_fragment();
//                FragmentManager fmm2 = getFragmentManager();
//                fmm2.beginTransaction().replace(R.id.contentPanel, fmm).addToBackStack(null).commit();
//
//                break;
//
//            case R.id.action_cart:
//
//                android.app.Fragment fm = new Cart_fragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.contentPanel, fm).addToBackStack(null).commit();
//
//
//
//
////        item.setVisible(true);
////
////        View count = item.getActionView();
////        count.setOnClickListener(new View.OnClickListener() {
////
////            @Override
////            public void onClick(View v) {
////               // menu.performIdentifierAction(itemm.getItemId(), 0);
////            }
////        });
////
////        totalBudgetCount = (TextView) count.findViewById(R.id.actionbar_notifcation_textview);
////        totalBudgetCount.setText("" + dbcart.getCartCount());
//
//
//                ((MainActivity) getActivity()).setCartCounter("" + dbcart.getCartCount());
//
//
//
//                break;
//
//            case R.id.action_coupons:
//
//
//                android.app.Fragment fr = new Coupon_fragment();
//                FragmentManager fmc = getFragmentManager();
//                fmc.beginTransaction().replace(R.id.contentPanel, fr).addToBackStack(null).commit();
//
////                        Bundle args = new Bundle();
////                        Fragment fm = new Product_fragment();
////                        args.putString("cat_deal", "2");
////                        fm.setArguments(args);
////                        FragmentManager fragmentManager = getFragmentManager();
////                        fragmentManager.beginTransaction().replace(R.id.contentPanel, fm)
////                                .addToBackStack(null).commit();
////
//
//                break;
//
//            case R.id.action_wallet:
//                android.app.Fragment fm1 = new Wallet_fragment();
//                FragmentManager fm2 = getFragmentManager();
//                fm2.beginTransaction().replace(R.id.contentPanel, fm1).addToBackStack(null).commit();
//
//                break;
//        }

        return false;
    }
}
