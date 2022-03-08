package Activities.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.handyapp_v2.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    ListAdapter myAdapter;
    ListModel listModel;
    ArrayList<ListModel> list = new ArrayList<ListModel>();
    ;
    FirebaseFirestore firestore;
    String category;
    EditText search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}