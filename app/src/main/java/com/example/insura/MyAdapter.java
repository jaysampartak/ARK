package com.example.insura;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
   private ArrayList<ClassListItem> listItems;
   private Context context;

    public MyAdapter(ArrayList<ClassListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public  TextView url;
        public TextView name;
        public CardView item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.img);
            url=(TextView) itemView.findViewById(R.id.url);
            name=(TextView)itemView.findViewById(R.id.name);
            item=(CardView)itemView.findViewById(R.id.item);
        }
    }

    //called when instance of viewholder class is created
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.list_item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
    ClassListItem listItem=listItems.get(position);
    
    if(listItem.getImg()!=null)holder.img.setImageBitmap(listItem.getImg());
    holder.url.setText(listItem.getUrl());
    holder.name.setText(listItem.getName());



    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}


