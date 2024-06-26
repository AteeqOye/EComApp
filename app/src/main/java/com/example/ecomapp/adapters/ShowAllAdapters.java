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
import com.example.ecomapp.models.ShowAllModel;

import java.awt.font.TextAttribute;
import java.util.List;

public class ShowAllAdapters extends RecyclerView.Adapter<ShowAllAdapters.ViewHolder> {

    private Context context;
    private List<ShowAllModel> showAllModelList;

    public ShowAllAdapters(Context context, List<ShowAllModel> showAllModelList) {
        this.context = context;
        this.showAllModelList = showAllModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder (LayoutInflater.from (parent.getContext ()).inflate (R.layout.show_all_items , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with (context).load (showAllModelList.get (position).getImage_url ()).into (holder.mItemImage);
        holder.mCost.setText ("$"+showAllModelList.get (position).getPrice ());
        holder.mName.setText (showAllModelList.get (position).getName ());

        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (context , DetailedActivity.class);
                intent.putExtra ("detailed" , showAllModelList.get (position));
                context.startActivity (intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return showAllModelList.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mItemImage;
        private TextView mCost;
        private TextView mName;

        public ViewHolder(@NonNull View itemView) {
            super (itemView);

            mItemImage = itemView.findViewById (R.id.item_image);
            mCost = itemView.findViewById (R.id.item_cost);
            mName = itemView.findViewById (R.id.item_nam);
        }
    }
}
