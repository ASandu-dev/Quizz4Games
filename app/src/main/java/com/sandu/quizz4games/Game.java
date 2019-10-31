package com.sandu.quizz4games;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;




public class Game extends AppCompatActivity {



    AdView adView;
    private Button ans1_btn;
    private Button ans2_btn;
    private Button ans3_btn;
    private Button ans4_btn;
    private TextView  question, score, question_no;
    private DatabaseReference myRef;
    private String userId;

    private Questions mQuestions = new Questions();
    private Users mUser = new Users("","","","","");
    Random r;
    private String mAnswer;
    private int mQuestionsLength = mQuestions.mQuestions.length;

    private int uScore;
    private int uQuestionsAnswered;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Initiate
        r = new Random();
        adView = findViewById(R.id.add);
        ans1_btn = findViewById(R.id.ans1_btn);
        ans2_btn = findViewById(R.id.ans2_btn);
        ans3_btn = findViewById(R.id.ans3_btn);
        ans4_btn = findViewById(R.id.ans4_btn);

        Button back_btn = findViewById(R.id.back_btn);

        question = findViewById(R.id.question);
        score    = findViewById(R.id.score);
        question_no = findViewById(R.id.question_no);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        final FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        userId = user.getUid();






        myRef.child("users").child(userId).child("eScore").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uScore = dataSnapshot.getValue(Integer.class);
                score.setText(String.valueOf(uScore));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //...//



        // update question randomizer
        updateQuestion(r.nextInt(mQuestionsLength));
        //...//

        //Button Functions

        ans1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans1_btn.getText() == mAnswer){
                    correctAnswer();
                    ans1_btn.setBackgroundResource(R.drawable.corect_btn);
                    disableButtons();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            resetButtons();
                            updateQuestion(r.nextInt(mQuestionsLength));
                        }
                    }, 1000);

                }else
                    ans1_btn.setBackgroundResource(R.drawable.wrong_btn);
                disableButtons();
                showCorrectAnswer();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        resetButtons();
                        updateQuestion(r.nextInt(mQuestionsLength));
                    }
                }, 1000);
                wrongAnswer();




            }
        });

        ans2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans2_btn.getText() == mAnswer){
                    correctAnswer();

                    ans2_btn.setBackgroundResource(R.drawable.corect_btn);
                    disableButtons();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            resetButtons();
                            updateQuestion(r.nextInt(mQuestionsLength));
                        }
                    }, 1000);

                }else
                    ans2_btn.setBackgroundResource(R.drawable.wrong_btn);
                disableButtons();
                showCorrectAnswer();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        resetButtons();
                        updateQuestion(r.nextInt(mQuestionsLength));
                    }
                }, 1000);
                wrongAnswer();


            }

        });
        ans3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans3_btn.getText() == mAnswer){
                    correctAnswer();
                    ans3_btn.setBackgroundResource(R.drawable.corect_btn);
                    disableButtons();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            resetButtons();
                            updateQuestion(r.nextInt(mQuestionsLength));
                        }
                    }, 1000);


                }else
                    ans3_btn.setBackgroundResource(R.drawable.wrong_btn);
                disableButtons();
                showCorrectAnswer();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        resetButtons();
                        updateQuestion(r.nextInt(mQuestionsLength));
                    }
                }, 1000);
                wrongAnswer();


            }
        });
        ans4_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ans4_btn.getText() == mAnswer){
                    correctAnswer();
                    ans4_btn.setBackgroundResource(R.drawable.corect_btn);
                    disableButtons();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            resetButtons();
                            updateQuestion(r.nextInt(mQuestionsLength));
                        }
                    }, 1000);


                }else
                    ans4_btn.setBackgroundResource(R.drawable.wrong_btn);
                disableButtons();
                showCorrectAnswer();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        resetButtons();
                        updateQuestion(r.nextInt(mQuestionsLength));
                    }
                }, 1000);
                wrongAnswer();





            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Game.this,Home.class);
                startActivity(i);
            }
        });



        //...//



        // Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //.....//
        // AdMob add - Banner
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);

        //.......//


    }

    private void resetButtons() {
        ans1_btn.setBackgroundResource(R.drawable.default_btn);
        ans2_btn.setBackgroundResource(R.drawable.default_btn);
        ans3_btn.setBackgroundResource(R.drawable.default_btn);
        ans4_btn.setBackgroundResource(R.drawable.default_btn);
        ans1_btn.setEnabled(true);
        ans2_btn.setEnabled(true);
        ans3_btn.setEnabled(true);
        ans4_btn.setEnabled(true);


    }

    // Update Question Function
    private void updateQuestion(int num){

        question.setText(mQuestions.getQuestions(num));
        ans1_btn.setText(mQuestions.getChoice1(num));
        ans2_btn.setText(mQuestions.getChoice2(num));
        ans3_btn.setText(mQuestions.getChoice3(num));
        ans4_btn.setText(mQuestions.getChoice4(num));


        mAnswer = mQuestions.getCorrectAnswer(num);

    }



    //...//


    //Other Functions
    private void wrongAnswer(){
        uScore -= 5;
        uQuestionsAnswered ++;

        if (uScore < 0){
            uScore = 0;
            score.setText("0");
        }
        question_no.setText(String.valueOf(uQuestionsAnswered));
        score.setText(String.valueOf(uScore));
        myRef.child("users").child(userId).child("eScore").setValue(uScore);
    }

    private void correctAnswer(){
        uScore +=15;
        score.setText(String.valueOf(uScore));
        myRef.child("users").child(userId).child("eScore").setValue(uScore);
        uQuestionsAnswered += 0;
        question_no.setText(String.valueOf(uQuestionsAnswered));
    }

    private void showCorrectAnswer(){
        if (ans1_btn.getText() == mAnswer){
            ans1_btn.setBackgroundResource(R.drawable.corect_btn);
        }else if (ans2_btn.getText() == mAnswer){
            ans2_btn.setBackgroundResource(R.drawable.corect_btn);
        }else if (ans3_btn.getText() == mAnswer){
            ans3_btn.setBackgroundResource(R.drawable.corect_btn);
        }else if (ans4_btn.getText() == mAnswer){
            ans4_btn.setBackgroundResource(R.drawable.corect_btn);
        }
    }
    //...//
    private void disableButtons(){
        ans1_btn.setEnabled(false);
        ans2_btn.setEnabled(false);
        ans3_btn.setEnabled(false);
        ans4_btn.setEnabled(false);
    }


}











