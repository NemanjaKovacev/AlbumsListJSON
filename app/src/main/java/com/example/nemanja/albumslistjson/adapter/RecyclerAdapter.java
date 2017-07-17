package com.example.nemanja.albumslistjson.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nemanja.albumslistjson.R;
import com.example.nemanja.albumslistjson.holder.RecyclerViewHolder;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private final Context context;
    private final ArrayList<String> albums;

    public RecyclerAdapter(Context context, ArrayList<String> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        holder.albumNameTextView.setText(albums.get(position));
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }
}
