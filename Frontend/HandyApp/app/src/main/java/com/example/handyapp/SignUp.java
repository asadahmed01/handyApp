package com.example.handyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextFullName;
    TextInputEditText textInputEditTextUserName;
    TextInputEditText textInputEditTextPassword;
    TextInputEditText textInputEditTextEmail;
    ProgressBar progressBar;

    Button signUpBtn;
    TextView loginTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //text feilds
        textInputEditTextFullName =   findViewById(R.id.fullname);
        textInputEditTextEmail =  findViewById(R.id.email);
        textInputEditTextUserName = findViewById(R.id.username);
        textInputEditTextPassword =  findViewById(R.id.password);
        progressBar = findViewById(R.id.progress);

        loginTextView = findViewById(R.id.loginText);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
        //Button
        signUpBtn = findViewById(R.id.buttonSignUp);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullName = String.valueOf(textInputEditTextFullName.getText());
               String userName = String.valueOf(textInputEditTextUserName.getText());
               String emailAddress = String.valueOf(textInputEditTextEmail.getText());
               String password = String.valueOf(textInputEditTextPassword.getText());

                 if(!(fullName.isEmpty())  && !(userName.isEmpty()) && !(emailAddress.isEmpty()) && !(password.isEmpty()))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                   startActivity(intent);
                   finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All Fields are required!", Toast.LENGTH_SHORT).show();

                }

            }
        });

        //

//        Handler hd = new Handler();
//        hd.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        })

    }
}