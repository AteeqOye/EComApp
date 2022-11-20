package com.example.ecomapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ecomapp.R;
import com.example.ecomapp.models.NewProductsModel;
import com.example.ecomapp.models.PopularProductModel;
import com.example.ecomapp.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImage,addItems,removeItems;
    TextView rating,description,name,price,quantity;
    Button addToCard, buyNow;
    Toolbar toolbar;

    int totalQuantity = 1;
    int totalPrice = 0;

    // New Products
    NewProductsModel newProductsModel = null ;

    //Popular Products
    PopularProductModel popularProductModel = null ;

    //Show All
    ShowAllModel showAllModel = null;

    FirebaseAuth auth;
    private FirebaseFirestore fireStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detailed);


        toolbar = findViewById (R.id.detailed_toolBar);
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

        final Object obj = getIntent ().getSerializableExtra ("detailed");
        if(obj instanceof NewProductsModel){

            newProductsModel = (NewProductsModel) obj;
        }
        else if(obj instanceof PopularProductModel){

            popularProductModel = (PopularProductModel) obj;
        }
        else if(obj instanceof ShowAllModel){

            showAllModel = (ShowAllModel) obj;
        }


        detailedImage = findViewById (R.id.detailed_image);
        description = findViewById (R.id.detailed_description);
        name = findViewById (R.id.detailed_name);
        rating = findViewById (R.id.rating);
        price = findViewById (R.id.detailed_price);
        quantity = findViewById (R.id.quantity);

        addItems = findViewById (R.id.add_items);
        removeItems = findViewById (R.id.remove_items);

        addToCard = findViewById (R.id.add_to_card);
        buyNow = findViewById (R.id.buy_now);

        // New Products
        if(newProductsModel != null)
        {
            Glide.with (getApplicationContext ()).load (newProductsModel.getImage_url ()).into (detailedImage);
            description.setText (newProductsModel.getDescription ());
            name.setText (newProductsModel.getName ());
            rating.setText (newProductsModel.getRating ());
            price.setText (String.valueOf (newProductsModel.getPrice ()));

            totalPrice = newProductsModel.getPrice () * totalQuantity;
        }
        // Popular Products
        if(popularProductModel != null)
        {
            Glide.with (getApplicationContext ()).load (popularProductModel.getImage_url ()).into (detailedImage);
            description.setText (popularProductModel.getDescription ());
            name.setText (popularProductModel.getName ());
            rating.setText (popularProductModel.getRating ());
            price.setText (String.valueOf (popularProductModel.getPrice ()));
            name.setText (popularProductModel.getName ());

            totalPrice = popularProductModel.getPrice () * totalQuantity;
        }
        // showAll Products
        if(showAllModel != null)
        {
            Glide.with (getApplicationContext ()).load (showAllModel.getImage_url ()).into (detailedImage);
            description.setText (showAllModel.getDescription ());
            name.setText (showAllModel.getName ());
            rating.setText (showAllModel.getRating ());
            price.setText (String.valueOf (showAllModel.getPrice ()));
            name.setText (showAllModel.getName ());

            totalPrice = showAllModel.getPrice () * totalQuantity;
        }

        //BuyNow
        buyNow.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DetailedActivity.this , AddressActivity.class);

                if(newProductsModel != null)
                {
                    intent.putExtra ("item" , newProductsModel);
                }

                if(popularProductModel != null)
                {
                    intent.putExtra ("item" , popularProductModel);
                }

                if(showAllModel != null)
                {
                    intent.putExtra ("item" , showAllModel);
                }
                startActivity (intent);
            }
        });

        //AddToCard
        addToCard.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                addToCard();
            }
        });

        //AddItemBtn
        addItems.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(totalQuantity < 10)
                {
                    totalQuantity++;
                    quantity.setText (String.valueOf (totalQuantity));
                    if(newProductsModel != null)
                    {
                        totalPrice = newProductsModel.getPrice () * totalQuantity;

                    }
                    if(popularProductModel != null)
                    {
                        totalPrice = popularProductModel.getPrice () * totalQuantity;

                    }
                    if(showAllModel != null)
                    {
                        totalPrice = showAllModel.getPrice () * totalQuantity;
                    }
                }

            }
        });

        //RemoveItemBtn
        removeItems.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(totalQuantity > 1)
                {
                    totalQuantity--;
                    quantity.setText (String.valueOf (totalQuantity));
                    if(newProductsModel != null)
                    {
                        totalPrice = newProductsModel.getPrice () * totalQuantity;

                    }
                    if(popularProductModel != null)
                    {
                        totalPrice = popularProductModel.getPrice () * totalQuantity;

                    }
                    if(showAllModel != null)
                    {
                        totalPrice = showAllModel.getPrice () * totalQuantity;

                    }
                }
            }
        });
    }

    private void addToCard() {

        String saveCurrentTime , saveCurrentDate;

        Calendar calForDate = Calendar.getInstance ();

        SimpleDateFormat currentDate = new SimpleDateFormat ("MM/dd/yyyy");
        saveCurrentDate = currentDate.format (calForDate.getTime ());

        SimpleDateFormat currentTime = new SimpleDateFormat ("HH:mm:ss a");
        saveCurrentTime = currentTime.format (calForDate.getTime ());

        final HashMap<String , Object> cartMap = new HashMap<> ();

        cartMap.put ("productName" , name.getText ().toString ());
        cartMap.put ("productPrice" , price.getText ().toString ());
        cartMap.put ("currentTime" , saveCurrentTime);
        cartMap.put ("currentDate" , saveCurrentDate);
        cartMap.put ("totalQuantity" , quantity.getText ().toString ());
        cartMap.put ("totalPrice" , totalPrice);

        fireStore.collection ("AddToCarT").document (auth.getCurrentUser ().getUid ())
                .collection ("User").add (cartMap).addOnCompleteListener (new OnCompleteListener<DocumentReference> () {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText (DetailedActivity.this , "Added To A Cart" , Toast.LENGTH_SHORT).show();
                        finish ();
                    }
                });

    }

}