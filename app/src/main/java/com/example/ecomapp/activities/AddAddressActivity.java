package com.example.ecomapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ecomapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    EditText name,address,city,postalCode,phoneNumber;
    Toolbar toolbar;
    Button addAddressBtn;

    FirebaseFirestore fireStore;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_address);

        toolbar = findViewById (R.id.add_address_toolbar);
        setSupportActionBar (toolbar);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);


        toolbar.setNavigationOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                finish ();
            }
        });

        fireStore = FirebaseFirestore.getInstance ();
        auth = FirebaseAuth.getInstance ();

        name = findViewById (R.id.ad_name);
        address = findViewById (R.id.ad_address);
        city = findViewById (R.id.ad_city);
        postalCode = findViewById (R.id.ad_code);
        phoneNumber = findViewById (R.id.ad_phone);
        addAddressBtn = findViewById (R.id.ad_add_address);

        addAddressBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                String userName = name.getText ().toString ();
                String userCity = name.getText ().toString ();
                String userAddress = name.getText ().toString ();
                String userPostalCode = name.getText ().toString ();
                String userPhoneNumber = name.getText ().toString ();

                String final_address = "";

                if(!userName.isEmpty ())
                {
                    final_address += userName;
                }
                if(!userCity.isEmpty ())
                {
                    final_address += userCity;
                }
                if(!userAddress.isEmpty ())
                {
                    final_address += userAddress;
                }
                if(!userPostalCode.isEmpty ())
                {
                    final_address += userPostalCode;
                }
                if(!userPhoneNumber.isEmpty ())
                {
                    final_address += userPhoneNumber;
                }
                if(!userName.isEmpty () && !userCity.isEmpty () && !userAddress.isEmpty () && !userPostalCode.isEmpty () && !userPhoneNumber.isEmpty ())
                {
                    Map<String , String> map = new HashMap<> ();
                    map.put ("userAddress" , final_address);

                    fireStore.collection ("CurrentUser").document (auth.getCurrentUser ().getUid ())
                            .collection ("Address").add (map).addOnCompleteListener (new OnCompleteListener<DocumentReference> () {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if(task.isSuccessful ())
                                    {
                                        Toast.makeText (AddAddressActivity.this, "Address Added", Toast.LENGTH_SHORT).show ();
                                        startActivity (new Intent (AddAddressActivity.this , DetailedActivity.class));
                                        finish ();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText (AddAddressActivity.this, "Kindly Fill All Field", Toast.LENGTH_SHORT).show ();
                }
            }
        });
    }
}