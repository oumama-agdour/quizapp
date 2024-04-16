package com.example.quizapp_agdour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Qst3 extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    Button bNext;
    int score;
    String RepCorrect;
    private static final String TAG = "quiz3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qst3);
        rg=(RadioGroup) findViewById(R.id.radioGroup3);
        bNext=(Button) findViewById(R.id.next1);
        Intent intent=getIntent();
        score=intent.getIntExtra("score",0) ;
        //Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT).show();
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb=(RadioButton) findViewById(rg.getCheckedRadioButtonId());
                if(rg.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getApplicationContext(),"Merci de choisir une réponse S.V.P !",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(getApplicationContext(),rb.getText().toString(),Toast.LENGTH_SHORT).show();
                    if(rb.getText().toString().equals(RepCorrect)){
                        score+=1;
                        //Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT).show();
                    }
                    Intent intent=new Intent(Qst3.this,Qst4.class);
                    intent.putExtra("score",score);
                    startActivity(intent);
                    //overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                    finish();
                }

            }
        });
        // Initialize Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        // Retrieve the correct answer from Firestore
        db.collection("questions")
                .document("réponses")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            RepCorrect = document.getString("Qst3.answer");
                            Log.d(TAG, "Correct answer retrieved from Firestore: " + RepCorrect);
                        } else {
                            Log.e(TAG, "Document does not exist");
                        }
                    } else {
                        Log.e(TAG, "Error getting document", task.getException());
                    }
                });
    }
}

