package com.example.ecomapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecomapp.R;
import com.example.ecomapp.activities.DetailedActivity;
import com.example.ecomapp.models.NewProductsModel;

import java.util.List;

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductsAdapter.ViewHolder> {

    private final Context context;
    private final List<NewProductsModel> newProductsModelList;

    public NewProductsAdapter(Context context, List<NewProductsModel> list) {
        this.context = context;
        this.newProductsModelList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder (LayoutInflater.from (parent.getContext ()).inflate (R.layout.new_products, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        Glide.with (context).load (newProductsModelList.get (position).getImage_url ()).into (holder.newImg);
        holder.newName.setText (newProductsModelList.get (position).getName ());
        holder.newPrice.setText (String.valueOf (newProductsModelList.get (position).getPrice ()));


        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context , DetailedActivity.class);
                intent.putExtra ("detailed" , newProductsModelList.get (position));
                context.startActivity (intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return newProductsModelList.size ();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView newImg;
        TextView newName , newPrice;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            newImg = itemView.findViewById (R.id.new_img);
            newName = itemView.findViewById (R.id.new_product_name);
            newPrice = itemView.findViewById (R.id.new_price);
        }
    }
}
