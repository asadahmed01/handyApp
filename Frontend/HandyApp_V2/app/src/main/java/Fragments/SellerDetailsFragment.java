package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.handyapp_v2.R;

import Adapters.ReviewDataAdapter;
import Data.ReviewData;
import Data.SellerData;

import java.util.ArrayList;
import java.util.List;

import Adapters.SellerDataAdapter;


public class SellerDetailsFragment extends Fragment {

    /*-----CourseList RecyclerView Data Here-------*/
    Integer[] imgBg = {R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
            R.drawable.ic_launcher_background, R.drawable.ic_launcher_background};
    String[] sellerRatings = {"4.9","5.0","4.9","4.9"};
    String[] sellerJon = {"Pluming","Pluming",
            "Pluming","Pluming"};
    String[] sellerName = {"Mohamed Abusultan","Mohamed Halbouni","Asad Ahmed",
            "Nathanial Joseph"};
    String[] jobAvgTime = {"2h 30m","2h 30m","2h 30m","2h 30m"};
    String[] sellerJobPrice = {"$75.00","$75.00","$75.00","$75.00"};

    private RecyclerView recyclerView;
    private SellerDataAdapter sellerDataAdapter;
    private ArrayList<SellerData> sellerData;
    RecyclerView rv;
    LinearLayoutManager layoutManager;
    List<ReviewData> userList;
    ReviewDataAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seller_details, container, false);

        recyclerView = view.findViewById(R.id.sellerRecyclerList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        sellerData = new ArrayList<>();

        for (int i = 0; i < imgBg.length; i++) {
            SellerData model = new SellerData(imgBg[i], sellerRatings[i], sellerJon[i],
                    sellerName[i], jobAvgTime[i], sellerJobPrice[i]);
            sellerData.add(model);
        }

        sellerDataAdapter = new SellerDataAdapter(getContext(),sellerData);
        recyclerView.setAdapter(sellerDataAdapter);



        userList = new ArrayList<>();
        userList.add(new ReviewData(R.drawable.ic_profile, "Ashley Burk", "this is a review about seller", "______________________________"));
        userList.add(new ReviewData(R.drawable.ic_profile, "Ashley Burk", "this is a review about seller", "______________________________"));
        userList.add(new ReviewData(R.drawable.ic_profile, "Ashley Burk", "this is a review about seller", "______________________________"));
        userList.add(new ReviewData(R.drawable.ic_profile, "Ashley Burk", "this is a review about seller", "______________________________"));


        //initDate();
        //initRecyclerView();
    /*    rv = view.findViewById(R.id.reviews);
        LinearLayoutManager lean = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        //layoutManager = new LinearLayoutManager(getContext(),userList);
        //layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(lean);
        adapter = new ReviewDataAdapter(userList);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
*/

        return view;
    }


 /*   private void initDate() {
        userList = new ArrayList<>();
        userList.add(new ReviewData(R.drawable.ic_profile, "Ashley Burk", "this is a review about seller", "______________________________"));
        userList.add(new ReviewData(R.drawable.ic_profile, "Ashley Burk", "this is a review about seller", "______________________________"));
        userList.add(new ReviewData(R.drawable.ic_profile, "Ashley Burk", "this is a review about seller", "______________________________"));
        userList.add(new ReviewData(R.drawable.ic_profile, "Ashley Burk", "this is a review about seller", "______________________________"));
    }*/

    /*private void initRecyclerView() {
        rv = getView().findViewById(R.id.reviews);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rv.setLayoutManager(layoutManager);
        adapter = new ReviewDataAdapter(userList);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }*/


}