package com.onpu.statements;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 21.09.2018.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Item> itemList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name, desc, comments;
        public ImageView icon;
        public ImageButton remove, edit;
        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.id);
            name = (TextView) view.findViewById(R.id.name);
            desc = (TextView) view.findViewById(R.id.desc);
            comments = (TextView) view.findViewById(R.id.comments);
            icon = (ImageView) view.findViewById(R.id.users);
            remove = (ImageButton) view.findViewById(R.id.remove);
        }
    }

    public Adapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Item movie = itemList.get(position);
        holder.id.setText(movie.getId());
        holder.name.setText(movie.getName());
        holder.desc.setText(movie.getDesc());
        holder.comments.setText(movie.getComments());
        holder.icon.setImageResource(movie.getIcon());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.equals(holder.remove)) {
                    removeAt(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void removeAt(int position) {
        itemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemList.size());
    }
}