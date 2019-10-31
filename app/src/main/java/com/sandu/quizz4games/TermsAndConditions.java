package com.sandu.quizz4games;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class TermsAndConditions extends AppCompatActivity {

    Button btn_agree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        btn_agree = findViewById(R.id.btn_agree);


        btn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TermsAndConditions.this, LoginScreen.class);
                startActivity(i);
            }
        });

    }
}
