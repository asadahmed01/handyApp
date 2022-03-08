package Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.handyapp_v2.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import Activities.models.ListModel;
import Adapters.ListAdapter;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListAdapter myAdapter ;
    ListModel listModel;
    ArrayList<ListModel> list= new ArrayList<ListModel>();;
    FirebaseFirestore firestore;
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycleView);
        firestore = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        listModel = new ListModel();
//        RetreavePosts();
        RetreaveFirestore();

    }

    private void RetreaveFirestore() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<ListModel>();
        myAdapter = new ListAdapter(this, list);

        recyclerView.setAdapter(myAdapter);

        EventChildListner();
    }

    private void EventChildListner() {
        firestore.collection("data")
                .whereEqualTo("category",category)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error!=null){

                        }
                        for (DocumentChange dc :value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                list.add(dc.getDocument().toObject(ListModel.class));

                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}