package com.iashish0001.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login_button , new_button;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_button = findViewById(R.id.login_button);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        new_button = findViewById(R.id.new_button);
        auth = FirebaseAuth.getInstance();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = email.getText().toString().trim();
                String mPassword = password.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)){
                    email.setError("Email field can not be empty");
                    return;
                }

                if(TextUtils.isEmpty(mPassword)){
                    email.setError("Password field can not be empty");
                    return;
                }

                if(password.length() < 6){
                    password.setError("Field requires atleast 6 charcters");
                    return;
                }

                auth.signInWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Logged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),home.class));
                        }else{
                            Toast.makeText(MainActivity.this,"Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }
    public void newuser(View view){
        startActivity(new Intent(getApplicationContext(),register.class));
        finish();

    }
}