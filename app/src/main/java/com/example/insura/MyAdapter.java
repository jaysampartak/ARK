package com.example.insura;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
   private ArrayList<ClassListItem > listItems;
   private Context context;

    public MyAdapter(ArrayList<ClassListItem > listItems, Context context) {
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
          //  item=(CardView)itemView.findViewById(R.id.item);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ClassListItem  listItem=listItems.get(position);
    

    // holder.url.setText(listItem.getUrl());
    holder.url.setMovementMethod(LinkMovementMethod.getInstance());
    holder.name.setText(listItem.getName());
    String utf= listItem.getImgUrl();

        byte[] decoded = Base64.getDecoder().decode(utf);

       Bitmap decodedByte = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);

        holder.img.setImageBitmap(decodedByte);

//        Picasso.with(context)
//                .load(listItem.getImgUrl())
//                .into(holder.img);
//

        holder.url.setOnClickListener(v -> {
            if(!listItem.getImgUrl().isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(listItem.getUrl()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}


