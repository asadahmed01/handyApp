package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import com.example.handyapp_v2.R;

public class SignUpActivity extends AppCompatActivity {

    /*-------- Variable Define -----------*/
    LinearLayout sellerRegisterCard,buyerRegisterCard;
    ImageView tickSeller,tickBuyer;
    TextView tvIam1,tvIam2,tvStudent,tvTutor;
    ImageView password_show, password_hide;
    FrameLayout frame_eye;
    TextView login;


    EditText email_txt;
    EditText address_txt;
    EditText password_txt;
    EditText userName_txt;


    Button createAccountBtn;
    private int passwordNotVisible = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        sellerRegisterCard = findViewById(R.id.sellerRegister);
        buyerRegisterCard = findViewById(R.id.buyerRegister);
        tickSeller = findViewById(R.id.imgTickSeller);
        tickBuyer = findViewById(R.id.imgTickBuyer);
        tvIam1 = findViewById(R.id.tvIam1);
        tvIam2 = findViewById(R.id.tvIam2);
        tvStudent = findViewById(R.id.tvStudent);
        tvTutor = findViewById(R.id.tvTutor);
        createAccountBtn = findViewById(R.id.createAccBtn);
        login = findViewById(R.id.loginBtn);
        //liStudent.setOnClickListener((View.OnClickListener) this);


        //--------User Edit Txt Field ID---------//
        password_txt = findViewById(R.id.userPasswordtxt);
        email_txt = findViewById(R.id.userEmailtxt);
        address_txt = findViewById(R.id.userAddresstxt);
        userName_txt = findViewById(R.id.userNametxt);




        frame_eye = findViewById(R.id.frame_eye);
        password_show = findViewById(R.id.password_show);
        password_hide = findViewById(R.id.password_hide);

        /*---------- Password hide and show code here ----------*/
        frame_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordNotVisible == 1) {
                    password_show.setVisibility(View.VISIBLE);
                    password_hide.setVisibility(View.GONE);
                    password_txt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {
                    password_show.setVisibility(View.GONE);
                    password_hide.setVisibility(View.VISIBLE);
                    password_txt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }
                password_txt.setSelection(password_txt.length());
            }
        });

        ScrollView scrollview = findViewById(R.id.scrollview1);
        scrollview.fullScroll(View.FOCUS_DOWN);
        scrollview.setFocusableInTouchMode(true);
        scrollview.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),AllCategoriesActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }


    public void ChangeCards(View v) {
        switch (v.getId()) {
            case R.id.sellerRegister:
                changeCardsSelection(1);
                break;

            case R.id.buyerRegister:
                changeCardsSelection(2);
                break;
        }

    }



    //1. means the user selected the buyer card.
    //2. means the user selected the seller card.
    public void changeCardsSelection(int flag)
    {
        if(flag == 1)
        {
            sellerRegisterCard.setBackgroundResource(R.drawable.rectangle_navy_blue);
            buyerRegisterCard.setBackgroundResource(R.drawable.rectangle_silver);
            tickSeller.setImageResource(R.drawable.ic_circle_tick);
            tickBuyer.setImageResource(R.drawable.circle_black_border);
            tvIam1.setTextColor(getResources().getColor(R.color.white));
            tvIam2.setTextColor(getResources().getColor(R.color.black));
            tvStudent.setTextColor(getResources().getColor(R.color.white));
            tvTutor.setTextColor(getResources().getColor(R.color.black));
        }
        if(flag == 2)
        {
            sellerRegisterCard.setBackgroundResource(R.drawable.rectangle_silver);
            buyerRegisterCard.setBackgroundResource(R.drawable.rectangle_navy_blue);
            tickSeller.setImageResource(R.drawable.circle_black_border);
            tickBuyer.setImageResource(R.drawable.ic_circle_tick);
            tvIam1.setTextColor(getResources().getColor(R.color.black));
            tvIam2.setTextColor(getResources().getColor(R.color.white));
            tvStudent.setTextColor(getResources().getColor(R.color.black));
            tvTutor.setTextColor(getResources().getColor(R.color.white));
        }
    }




    private void register(String userName, String userEmail, String userPassword)
    {
        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    assert firebaseUser != null;
                    String userID = firebaseUser.getUid();

                    reference = FirebaseDatabase.getInstance().getReference("Users").child(userID);
                    HashMap<String,String> hashMap = new HashMap<>();
                    hashMap.put("id",userID);
                    hashMap.put("username",userName);

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent = new Intent(getApplicationContext(),AllCategoriesActivity.class);
                                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }


}