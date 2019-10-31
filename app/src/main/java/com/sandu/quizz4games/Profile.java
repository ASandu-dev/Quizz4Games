package com.sandu.quizz4games;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile extends AppCompatActivity {

    private static final String TAG = "";
    Button signout, back_btn;
    private String name, email, userId;
    private TextView userName, userEmail, score;
    Uri photoUrl;
    boolean emailVerified;
    private ImageView prof_pic;
    private int uScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //.....//

        //Initialize
        signout =findViewById(R.id.signout);
        userName=findViewById(R.id.name);
        userEmail=findViewById(R.id.email);
        score = findViewById(R.id.score);
        prof_pic = findViewById(R.id.prof_pic);
        back_btn = findViewById(R.id.back_btn);



        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDatabase.getReference();



        //...//

        //Actions

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Profile.this,LoginScreen.class);
                startActivity(i);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, Home.class);
                startActivity(i);
            }
        });

        //...//
        //Firebase
        //getting user info//
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();
            photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            userId = user.getUid();
        }
        userName.setText(name);
        userEmail.setText(email);
        Glide.with(this).load(photoUrl).into(prof_pic);

        myRef.child("users").child(userId).child("Name").setValue(name);
        myRef.child("users").child(userId).child("eMail").setValue(email);



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
        //...//






    }

}
