package com.example.nexustest3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fragment2 extends Fragment {
    RecyclerView recyclerView;
    ArrayList name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment2,container, false);

        recyclerView=rootview.findViewById(R.id.recyclerView);

        name=new ArrayList();

        for(int i=0;i<Data.names.length;i++)
        {
            name.add(Data.names);
        }
        HelperAdapter helperAdapter=new HelperAdapter(getContext(), name);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(helperAdapter);

        return rootview;
    }
}

