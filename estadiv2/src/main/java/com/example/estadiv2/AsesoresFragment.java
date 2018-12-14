package com.example.estadiv2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AsesoresFragment extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAsesoresRecyclerViewAdapter myasesoresRecyclerViewAdapter;
    private ArrayList<Asesores> asesoreslist;
    private FragmentManager fm;
    private RecyclerViewOnItemClickListener passe;

    private FragmentTransaction fy;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public AsesoresFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asesores_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        asesoreslist = new ArrayList<>();
        myasesoresRecyclerViewAdapter = new MyAsesoresRecyclerViewAdapter(asesoreslist);
        //myasesoresRecyclerViewAdapter.setOnRecyclerViewItemClickListener(this);
        recyclerView.setAdapter(myasesoresRecyclerViewAdapter);

        fm = getActivity().getSupportFragmentManager();
        fy = fm.beginTransaction();
        loadData();

        myasesoresRecyclerViewAdapter.notifyDataSetChanged();

        return view;
    }

    private void loadData() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.child("Asesores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Asesores asesores =snapshot.getValue(Asesores.class);
                    asesoreslist.add(asesores);
                    myasesoresRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}