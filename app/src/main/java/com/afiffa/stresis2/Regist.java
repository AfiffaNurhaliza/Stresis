package com.afiffa.stresis2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.security.PrivateKey;

public class Regist extends AppCompatActivity {
   private EditText namalengkap, tanggal, alamat, email, sandi;
   private Button daftar;
   private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        namalengkap = findViewById(R.id.namalengkap);
        tanggal = findViewById(R.id.tanggal);
        alamat = findViewById(R.id.alamat);
        email = findViewById(R.id.email);
        sandi = findViewById(R.id.sandi);
        daftar = findViewById(R.id.daftar);
        mAuth = FirebaseAuth.getInstance();

        daftar.setOnClickListener(v -> {
            if (namalengkap.getText().length() > 0 && tanggal.getText().length() > 0 && alamat.getText().length() > 0 && email.getText().length() > 0 && sandi.getText().length() > 0){
        }else{
                Toast.makeText(getApplicationContext(), "lengkapi data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void register(String namalengkap, String tanggal, String alamat, String email, String sandi){
    mAuth.createUserWithEmailAndPassword(email, sandi).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()&& task.getResult()!=null) {
                        FirebaseUser firebaseUser = task.getResult().getUser();
                        if (firebaseUser!=null) {
                            UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(namalengkap)
                                    .build();
                            firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    reload();
                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(),"GAGAL", Toast.LENGTH_SHORT).show();
                        }
                }else
                {
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            });
}
    private void reload(){
        startActivity(new Intent(getApplicationContext(), loginpsikolog.class));
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