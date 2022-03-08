package Activities.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.handyapp_v2.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.recycleView);
        search = view.findViewById(R.id.search);
        firestore = FirebaseFirestore.getInstance();


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                RetreaveFirestore();

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                RetreaveFirestore();

            }

            @Override
            public void afterTextChanged(Editable s) {

                RetreaveFirestore();

            }

        });


    }

    private void RetreaveFirestore() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<ListModel>();
        myAdapter = new ListAdapter(getContext(), list);

        recyclerView.setAdapter(myAdapter);

        EventChildListner();
    }

    private void EventChildListner() {
        String Search = "";
        Search = search.getText().toString();
        firestore.collection("data")
                .whereEqualTo("username", Search)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {

                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                list.add(dc.getDocument().toObject(ListModel.class));

                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        RetreaveFirestore();
    }

}