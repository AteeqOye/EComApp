package com.example.ecomapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.example.ecomapp.models.PopularProductModel;

import java.util.List;

public class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsAdapter.ViewHolder> {

    private Context context;
    private List<PopularProductModel> popularProductModelList;

    public PopularProductsAdapter(Context context, List<PopularProductModel> popularProductModelList) {
        this.context = context;
        this.popularProductModelList = popularProductModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder (LayoutInflater.from (parent.getContext ()).inflate (R.layout.popular_item , parent ,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with (context).load (popularProductModelList.get (position).getImage_url ()).into (holder.allImg);
        holder.allName.setText (popularProductModelList.get (position).getName ());
        holder.allPrice.setText (String.valueOf (popularProductModelList.get (position).getPrice ()));

        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context , DetailedActivity.class);
                intent.putExtra ("detailed" , popularProductModelList.get (position));
                context.startActivity (intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return popularProductModelList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView allImg;
        TextView allName , allPrice;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            allImg = itemView.findViewById (R.id.all_img);
            allName = itemView.findViewById (R.id.all_product_name);
            allPrice = itemView.findViewById (R.id.all_price);
        }
    }
}
