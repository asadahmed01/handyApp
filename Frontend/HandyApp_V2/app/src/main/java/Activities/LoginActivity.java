package Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.handyapp_v2.R;

import java.net.Inet4Address;

public class LoginActivity extends AppCompatActivity {

    /*----- Variable Define --------*/
    EditText password_et;
    ImageView password_show, password_hide;
    FrameLayout frame_eye;
    Button loginBtn;
    private int passwordNotVisible = 1;
    TextView signUptxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        password_et = findViewById(R.id.password_et);
        frame_eye = findViewById(R.id.frame_eye);
        password_show = findViewById(R.id.password_show);
        password_hide = findViewById(R.id.password_hide);
        loginBtn = findViewById(R.id.loginBtn);
        signUptxt = findViewById(R.id.signUpbtn);



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
                Intent intent = new Intent(getApplicationContext(), AllCategoriesActivity.class);
                startActivity(intent);
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
}