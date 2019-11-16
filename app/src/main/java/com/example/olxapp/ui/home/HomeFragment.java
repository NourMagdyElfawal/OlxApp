package com.example.olxapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.olxapp.Adapter.HomeAdapter;
import com.example.olxapp.Interface.category;
import com.example.olxapp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
//    List<category> historicList = new ArrayList<>();

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    HomeAdapter homeAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

            homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.home_recycleviw);

//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);

        ArrayList<category> imageUrlList = prepareImages();
        ArrayList<category> imageTitleList=prepareTitles();
        homeAdapter = new HomeAdapter(getActivity(), imageUrlList,imageTitleList);

//        homeAdapter = new HomeAdapter(getActivity(), historicList);
        recyclerView.setAdapter(homeAdapter);

        return root;
    }


    private ArrayList<category> prepareImages() {
        // here you should give your image URLs and that can be a link from the Internet
        String imageUrls[] = {
                "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
                "https://homepages.cae.wisc.edu/~ece533/images/arctichare.png",
                "https://homepages.cae.wisc.edu/~ece533/images/fruits.png",
                "https://homepages.cae.wisc.edu/~ece533/images/frymire.png",
                "https://homepages.cae.wisc.edu/~ece533/images/girl.png",
                "https://homepages.cae.wisc.edu/~ece533/images/monarch.png",
                "https://homepages.cae.wisc.edu/~ece533/images/airplane.png",
                "https://homepages.cae.wisc.edu/~ece533/images/arctichare.png",
                "https://homepages.cae.wisc.edu/~ece533/images/boat.png",
                "https://homepages.cae.wisc.edu/~ece533/images/cat.png"};

        ArrayList<category> imageUrlList = new ArrayList<category>();
        for (int i = 0; i < imageUrls.length; i++) {
            category imageUrl = new category();
            imageUrl.setImageUrl(imageUrls[i]);
            imageUrlList.add(imageUrl);
        }
        Log.d("HomeFragment", "List count: " + imageUrlList.size());
        return imageUrlList;
    }
    private ArrayList<category> prepareTitles() {
        // here you should give your image titles and that can be a link from the Internet
        String itemTitles[] = {
                "السيارات",
                "الكهربائيات",
                "الملابس",
                "مستلزمات المنزل",
                "عقارات",
                "وظائف",
                "اغذيه",
                "اثاث",
                "مستلزمات سيارات",
                "الكترونيات"};

        ArrayList<category> imageTitleList = new ArrayList<>();
        for (int i = 0; i < itemTitles.length; i++) {
            category imageTitle = new category();
            imageTitle.setName(itemTitles[i]);
            imageTitleList.add(imageTitle);
        }
        Log.d("HomeFragment", "List count: " + imageTitleList.size());
        return imageTitleList;

    }

}


