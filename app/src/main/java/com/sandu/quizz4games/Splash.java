package com.sandu.quizz4games;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class Splash extends AppCompatActivity {
    private ImageView logo;
    private TextView by_dev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logo = findViewById(R.id.logo);
        by_dev = findViewById(R.id.by_dev);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        final Intent i = new Intent(this,TermsAndConditions.class);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.myanimation);
        logo.startAnimation(myanim);
        by_dev.startAnimation(myanim);

        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(2500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();

    }

}
