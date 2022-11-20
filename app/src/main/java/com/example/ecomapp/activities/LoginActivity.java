package com.example.ecomapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecomapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText emailEdit , passwordEdit;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
    //    getSupportActionBar ().hide ();

        auth = FirebaseAuth.getInstance ();
        emailEdit = findViewById (R.id.emailEdit);
        passwordEdit = findViewById (R.id.passwordEdit);

    }

    public void signIn(View view) {
        String userEmail = emailEdit.getText ().toString ();
        String userPassword = passwordEdit.getText ().toString ();
        if(TextUtils.isEmpty (userEmail))
        {
            Toast.makeText (this , "Enter Email Address!" , Toast.LENGTH_LONG).show ();
            return;
        }

        if(TextUtils.isEmpty (userPassword))
        {
            Toast.makeText (this , "Enter Password!" , Toast.LENGTH_LONG).show ();
            return;
        }

        if(userPassword.length () < 6)
        {
            Toast.makeText (this , "Password to short, Enter minimum 6 characters!" , Toast.LENGTH_LONG).show ();
            return;
        }

        auth.createUserWithEmailAndPassword (userEmail , userPassword)
                .addOnCompleteListener (LoginActivity.this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful ()) {
                            Toast.makeText (LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show ();

                            Intent intent = new Intent (LoginActivity.this, MainActivity.class);
                            startActivity (intent);
                        }
                        else
                        {
                            Toast.makeText (LoginActivity.this, "Error :"+task.getException (), Toast.LENGTH_SHORT).show ();

                        }
                    }
                });

    }

    public void signUp(View view) {
        Intent intent = new Intent (LoginActivity.this , RegistrationActivity.class);
        startActivity (intent);
    }
}