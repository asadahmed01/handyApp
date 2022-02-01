package com.example.handyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {


    Button loginBtn;
    TextView signupTextView;
    ProgressBar progressBar;
    TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextEmail = findViewById(R.id.emailL);
        textInputEditTextPassword = findViewById(R.id.passwordL);

        signupTextView = findViewById(R.id.signUpText);

        loginBtn = findViewById(R.id.buttonLogin);
        progressBar = findViewById(R.id.progress);

        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = String.valueOf(textInputEditTextEmail.getText());
                String pass = String.valueOf(textInputEditTextPassword.getText()) ;

                if(!email.isEmpty() && !pass.isEmpty())
                {
                    progressBar.setVisibility(View.VISIBLE);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All fields required!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}