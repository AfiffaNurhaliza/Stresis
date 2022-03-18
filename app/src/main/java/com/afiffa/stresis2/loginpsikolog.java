package com.afiffa.stresis2;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginpsikolog extends AppCompatActivity {
   private EditText username, password;
   private Button btnLoginButton;
   private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpsikolog);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnLoginButton = (Button) findViewById(R.id.btnLoginButton);

        mAuth = FirebaseAuth.getInstance();

        btnLoginButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),Regist.class));
        });
        btnLoginButton.setOnClickListener(v -> {
            if(username.getText().length()>0 && password.getText().length()>0){
        }else{
            Toast.makeText(getApplicationContext(),"lengkapi data", Toast.LENGTH_SHORT).show();
        }
    });
}
    private void login(String username, String password){
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
             if(task.isSuccessful()&& task.getResult()!=null) {
                 if (task.getResult().getUser() != null){
                     reload();
             }else {
                     Toast.makeText(getApplicationContext(), "GAGAL", Toast.LENGTH_SHORT).show();
                 }
             }else{
                 Toast.makeText(getApplicationContext(),"GAGAL", Toast.LENGTH_SHORT).show();
             }
            }
        });
    }


    private void reload(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}