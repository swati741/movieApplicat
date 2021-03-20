package com.example.movieapp;

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

import java.util.List;

public class MoiveAdapter extends RecyclerView.Adapter<MoiveAdapter.MyViewHolder>{

    private Context mContext;
    private List<MoiveModelClass> list;


    public MoiveAdapter(Context mContext, List<MoiveModelClass> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.movie_item,parent,false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(list.get(position).getId());
        holder.name.setText(list.get(position).getName());
//        holder.overview.setText(list.get(position).getOverview());

        //https://image.tmdb.org/t/p/w500/6KErczPBROQty7QoIsaa6wJYXZi.jpg

        //using Glide

        Glide.with(mContext)
                .load("/https://image.tmdb.org/t/p/w500" +list.get(position).getImage())
                .into(holder.image);

//        holder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(mContext,movie_overvew.class);
//                intent.putExtra("image",list.get(position).getImage());
//                intent.putExtra("name",list.get(position).getName());
//                intent.putExtra("rate",list.get(position).getId());
//                intent.putExtra("overview",list.get(position).getOverview());
//                mContext.startActivity(intent);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView name;
        ImageView image;
//        TextView overview;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_text);
            name = itemView.findViewById(R.id.name_text);
            image = itemView.findViewById(R.id.movie_image);
//           overview=itemView.findViewById(R.id.overview);
        }
    }
}
