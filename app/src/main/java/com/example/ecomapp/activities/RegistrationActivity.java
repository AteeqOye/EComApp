package com.example.ecomapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class RegistrationActivity extends AppCompatActivity {

    EditText nameEdit , emailEdit , passwordEdit;
    private FirebaseAuth auth;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_registration);

      //  getSupportActionBar ().hide ();
        auth = FirebaseAuth.getInstance ();

        if(auth.getCurrentUser () !=null)
        {
            Intent intent = new Intent (RegistrationActivity.this , MainActivity.class);
            startActivity (intent);
            finish ();
        }


        nameEdit = findViewById (R.id.nameEdit);
        emailEdit = findViewById (R.id.emailEdit);
        passwordEdit = findViewById (R.id.passwordEdit);

        sharedPreferences = getSharedPreferences ("onBoardingScreen" , MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean ("firstTime" , true);

        if(isFirstTime)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit ();
            editor.putBoolean ("firstTime" , false);
            editor.commit ();


            Intent intent = new Intent (RegistrationActivity.this , OnBoardingActivity.class);
            startActivity (intent);
            finish ();
        }

    }

    public void signUp(View view) {

        String userName = nameEdit.getText ().toString ();
        String userEmail = emailEdit.getText ().toString ();
        String userPassword = passwordEdit.getText ().toString ();

        if(TextUtils.isEmpty (userName))
        {
            Toast.makeText (this , "Enter Name!" , Toast.LENGTH_LONG).show ();
        }

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
                .addOnCompleteListener (RegistrationActivity.this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful ())
                        {
                            Toast.makeText (RegistrationActivity.this, "Successfully Register", Toast.LENGTH_SHORT).show ();

                            Intent intent = new Intent (RegistrationActivity.this , MainActivity.class);
                            startActivity (intent);
                        }
                        else
                        {
                            Toast.makeText (RegistrationActivity.this, "Registered Fail!"+task.getException (), Toast.LENGTH_SHORT).show ();
                        }

                    }
                });

    }

    public void signIn(View view) {
        Intent intent = new Intent (RegistrationActivity.this , LoginActivity.class);
        startActivity (intent);
    }
}