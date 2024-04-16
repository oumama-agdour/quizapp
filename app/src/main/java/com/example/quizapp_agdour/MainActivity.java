package com.example.quizapp_agdour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
  //declaration
    EditText etlogin , etpassword;
    Button blogin;
    TextView tvRegister;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FirebaseApp.initializeApp(this);

        // récuperation d'id
         etlogin=findViewById(R.id.email);
         etpassword=findViewById(R.id.password);
         blogin=findViewById(R.id.signin);
         tvRegister=findViewById(R.id.toregister);
         mAuth = FirebaseAuth.getInstance();
        //...

        //association de listeners
         blogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 signIn(etlogin.getText().toString(), etpassword.getText().toString());
             }
         });
        // traitement
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));

            }
        });
    }

    private void signIn(String login, String password) {
        mAuth.signInWithEmailAndPassword(login,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, Qst1.class));
                } else {
                    Toast.makeText(getApplicationContext(),
                            "login failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}