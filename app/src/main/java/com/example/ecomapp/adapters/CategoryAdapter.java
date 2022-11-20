package com.example.ecomapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecomapp.R;
import com.example.ecomapp.activities.DetailedActivity;
import com.example.ecomapp.activities.LoginActivity;
import com.example.ecomapp.activities.RegistrationActivity;
import com.example.ecomapp.activities.ShowAllActivity;
import com.example.ecomapp.fragments.HomeFragment;
import com.example.ecomapp.models.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final Context context;
    private List<CategoryModel> categoryModelList;

    public CategoryAdapter(Context context, List<CategoryModel> list) {
        this.context = context;
        this.categoryModelList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder (LayoutInflater.from (parent.getContext ()).inflate (R.layout.category_list , parent ,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with (context).load (categoryModelList.get (position).getImage_url ()).into (holder.cateImg);
        holder.cateName.setText (categoryModelList.get (position).getName ());

        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context , ShowAllActivity.class);
                intent.putExtra ("type",categoryModelList.get (position).getType ());
                context.startActivity (intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cateImg;
        TextView cateName;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);
            cateImg = itemView.findViewById (R.id.cat_img);
            cateName = itemView.findViewById (R.id.cat_name);
        }
    }
}
