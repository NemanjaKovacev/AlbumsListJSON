package com.example.nemanja.albumslistjson.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.nemanja.albumslistjson.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public final TextView albumNameTextView;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        albumNameTextView = (TextView) itemView.findViewById(R.id.albumTextView);
    }
}
