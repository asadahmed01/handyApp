package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.handyapp_v2.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import CategoriesApiCalls.CategoriesResponse;
import LoginApiCalls.ApiClient;
import LoginApiCalls.LoginResponse;
import LoginApiCalls.UserLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    /*----- Variable Define --------*/
    List<CategoriesResponse> categories = new ArrayList<>();
    ArrayList<CategoriesResponse> myList;
    EditText password_et, email;
    ImageView password_show, password_hide;
    FrameLayout frame_eye;
    Button loginBtn;
    private int passwordNotVisible = 1;
    TextView signUptxt;
    List<LoginResponse> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        myList = new ArrayList<>();
        password_et = findViewById(R.id.password_et);
        frame_eye = findViewById(R.id.frame_eye);
        password_show = findViewById(R.id.password_show);
        password_hide = findViewById(R.id.password_hide);
        loginBtn = findViewById(R.id.loginBtn);
        signUptxt = findViewById(R.id.signUpbtn);
        email = findViewById(R.id.email_login);



        /*---------- Password hide and show code here ----------*/
        frame_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordNotVisible == 1) {
                    password_show.setVisibility(View.VISIBLE);
                    password_hide.setVisibility(View.GONE);
                    password_et.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {
                    password_show.setVisibility(View.GONE);
                    password_hide.setVisibility(View.VISIBLE);
                    password_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }
                password_et.setSelection(password_et.length());
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //check if email and password is empty
                if(isFieldEmpty(email.getText().toString()) || isFieldEmpty(password_et.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Email and password required", Toast.LENGTH_LONG).show();
                }
                else{
                    loginUser();
                    getCategories();
                    Intent intent = new Intent(getApplicationContext(), AllCategoriesActivity.class);
                    intent.putExtra("cat", (Serializable) categories);
                    startActivity(intent);
                }
            }
        });



        signUptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });


    }
    //check if input field is empty
    public boolean isFieldEmpty(String field){
        return TextUtils.isEmpty(field) ? true : false;
    }

    //login method
    public void loginUser(){
        UserLogin loginRequest = new UserLogin();
        loginRequest.setUsername(email.getText().toString());
        loginRequest.setPassword(password_et.getText().toString());

        Call<List< LoginResponse>> loginResponseCall  = ApiClient.getuserservice().userLogin();
        loginResponseCall.enqueue(new Callback<List <LoginResponse>> () {
            @Override
            public void onResponse(Call<List < LoginResponse>> call, Response<List < LoginResponse>> response) {
                if(response.isSuccessful()){
                    //Log.e("TAG", "response 33: " +response.body() );
                    users = response.body();
                    //Toast.makeText( LoginActivity.this, "FullName "+users , Toast.LENGTH_LONG).show();
//                    for(LoginResponse user : users){
//                        Toast.makeText( LoginActivity.this, "FullName "+user.getFullname() , Toast.LENGTH_LONG).show();
//                        Log.d("TAG", "Name: " +user.getFullname() );
//                    }

                }
                else{
                    Toast.makeText(LoginActivity.this,response.errorBody().toString(),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List <LoginResponse>> call, Throwable t) {
                Toast.makeText( LoginActivity.this, "Login failed "+ t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    //get all categories from the backend
    public void getCategories(){
        Call<List<CategoriesResponse>> categoriesResponseCall  =  CategoriesApiCalls.ApiClient.getCategoryService().categoryList();
        categoriesResponseCall.enqueue(new Callback<List<CategoriesResponse>>() {
            @Override
            public void onResponse(Call<List<CategoriesResponse>> call, Response<List<CategoriesResponse>> response) {

                if(response.isSuccessful()){
                    Log.e("TAG", "response: " +response.body() );
                    categories = response.body();
                    myList = (ArrayList<CategoriesResponse>)  response.body();
                    ///Toast.makeText( AllCategoriesActivity.this, "Category "+categories , Toast.LENGTH_LONG).show();
                    for(int i = 0; i < response.body().size(); i++){
                       categories.add(  response.body().get(i));
                    }

                }
                else{

                    Log.d("TAG", "ERROOOOOOOOOOOOR:" + response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<List<CategoriesResponse>> call, Throwable t) {


            }
        });
    }
}