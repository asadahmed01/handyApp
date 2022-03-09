package Activities.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.example.handyapp_v2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import Activities.BuyerDashboardActivity;
import Activities.SignUpActivity;


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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Intent intent = new Intent(getActivity(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(getActivity())
                        .withPermissions(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        //select image

                        if (report.areAllPermissionsGranted()) {
                            //select image
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
                }).check();
            }
        });
        getProfileData();
    }

    private void getProfileData() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firebaseFirestore;
        firebaseFirestore = FirebaseFirestore.getInstance();
        String firebaseUser = firebaseAuth.getCurrentUser().getUid();


        firebaseFirestore.collection("users").document(firebaseAuth.getCurrentUser().getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot dataSnapshot = task.getResult();
                            if (dataSnapshot.exists()) {
                                String username = dataSnapshot.get("username").toString();
                                String myemail = dataSnapshot.get("email").toString();
                                String userType = dataSnapshot.get("usertype").toString();
                                String userAddress = dataSnapshot.get("Address").toString();
                                try {
                                    String imageurl = dataSnapshot.get("imageURL").toString();
                                    Picasso.get().load(imageurl).into(profileImageView);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
                                }
                                if (userType.equals("Seller")){
                                    changeUser.setText("Switch to Buyer");
                                }else {
                                    changeUser.setText("Switch to Seller");
                                }
                                changeUserType(userType);

                                address.setText(userAddress);
                                usertype.setText(userType);
                                nametxt.setText(username);
                                name.setText(username);
                                email.setText(myemail);

                            } else {
                                Toast.makeText(getActivity(), "Error While getting data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void changeUserType(String userType) {
        changeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType.equals("Seller")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setTitle("Confirm");
                    builder.setMessage("Are you sure want to switch to Buyer");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseFirestore firestore;
                            firestore = FirebaseFirestore.getInstance();
                            HashMap<String, Object> map = new HashMap<>();

                            map.put("usertype", "Buyer");

                            firestore.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .update(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getActivity(), BuyerDashboardActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });

                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }
}