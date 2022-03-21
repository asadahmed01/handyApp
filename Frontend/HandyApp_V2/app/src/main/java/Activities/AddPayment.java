package Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.handyapp_v2.R;
import com.stripe.android.model.Card;
import com.stripe.android.view.CardMultilineWidget;

public class AddPayment extends AppCompatActivity {

    CardMultilineWidget cardMultilineWidget;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        setContentView(R.layout.activity_add_payment);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Payment");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        cardMultilineWidget = findViewById(R.id.card_input_widget);
        save = findViewById(R.id.save_payment);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    private void saveCard() {

        Card card =  cardMultilineWidget.getCard();
        if(card == null){
            Toast.makeText(getApplicationContext(),"Invalid card",Toast.LENGTH_SHORT).show();
        }else {
            if (!card.validateCard()) {
                // Do not continue token creation.
                Toast.makeText(getApplicationContext(), "Invalid card", Toast.LENGTH_SHORT).show();
            } else {
                //CreateToken(card);
            }
        }
    }


}