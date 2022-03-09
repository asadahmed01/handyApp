package Activities.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.handyapp_v2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedImageView;


public class ProfileFragment extends Fragment {

    TextView nametxt,usertype,changeUser;
    TextView name, email, description,address;
    FirebaseAuth firebaseAuth;
    RoundedImageView profileImageView;
    ImageView editImage,editprofile;
    private Uri resultUri;
    private String url = "";
    private DatabaseReference Refdatabase;
    private FirebaseFirestore firestore;
    private StorageReference storage;
    RelativeLayout loading;
    Button logout;

    SharedPreferences sharedpreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedpreferences = getActivity().getSharedPreferences("usertype", Context.MODE_PRIVATE);
        nametxt = view.findViewById(R.id.nametxt);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        loading = view.findViewById(R.id.loading);
        usertype = view.findViewById(R.id.usertype);
        changeUser = view.findViewById(R.id.change_user_typr);
        address = view.findViewById(R.id.address);
        logout = view.findViewById(R.id.logout);

        profileImageView = view.findViewById(R.id.imgUser);
        editImage = view.findViewById(R.id.edit_image);
        editprofile = view.findViewById(R.id.edit_profile);

        Refdatabase = FirebaseDatabase.getInstance().getReference();
        firestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();


        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}