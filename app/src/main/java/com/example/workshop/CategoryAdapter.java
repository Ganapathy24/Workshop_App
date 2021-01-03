package com.example.workshop;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    Context context;
    ArrayList<UserEntity> members;

    public CategoryAdapter(Context c , ArrayList<UserEntity> p)
    {
        context = c;
        members = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.batteryshop,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(members.get(position).getName());
        holder.shop.setText(members.get(position).getShopName());
        holder.category.setText(members.get(position).getCategory());
//        holder.phone.setText(members.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,shop,category;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            shop = (TextView) itemView.findViewById(R.id.shop);
            category = (TextView) itemView.findViewById(R.id.category);
//           phone = (TextView) itemView.findViewById(R.id.phone);



        }
    }
}