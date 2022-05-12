package com.example.nexustest3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HelperAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayListName;

    public HelperAdapter(Context context, ArrayList arrayListName) {
        this.context = context;
        this.arrayListName=arrayListName;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.learndropdown, parent ,false);
        ViewHolderClass viewHolderClass=new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass)holder;
        viewHolderClass.textView.setText(Data.names[position]);
    }

    @Override
    public int getItemCount() {
        return arrayListName.size();
    }
    public class ViewHolderClass extends RecyclerView.ViewHolder
    {
        TextView textView;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.learninginfo);
        }
    }
}