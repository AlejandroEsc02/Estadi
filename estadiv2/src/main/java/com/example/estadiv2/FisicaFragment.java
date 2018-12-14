package com.example.estadiv2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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


public class FisicaFragment extends Fragment implements RecyclerViewOnItemClickListener{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyfisicaRecyclerViewAdapter myfisicaRecyclerViewAdapter;
    private ArrayList<Fisica> fisicalist;
    private FragmentManager fm;
    private RecyclerViewOnItemClickListener passe;

    private FragmentTransaction fy;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private FloatingActionButton fab;

    public FisicaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fisica_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        fisicalist = new ArrayList<>();
        myfisicaRecyclerViewAdapter = new MyfisicaRecyclerViewAdapter(fisicalist);
        myfisicaRecyclerViewAdapter.setOnRecyclerViewItemClickListener(this);
        recyclerView.setAdapter(myfisicaRecyclerViewAdapter);

        fm = getActivity().getSupportFragmentManager();
        fy = fm.beginTransaction();
        loadData();

        myfisicaRecyclerViewAdapter.notifyDataSetChanged();

        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fy = fm.beginTransaction();
                QuestionFragment questionFragment = new QuestionFragment();
                fy.replace(R.id.frame, questionFragment).commit();

            }
        });

        return view;
    }

    private void loadData() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.child("Fisica").orderByChild("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Fisica fisica =snapshot.getValue(Fisica.class);
                    fisicalist.add(fisica);
                    myfisicaRecyclerViewAdapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View v, int position) {
        loadData();
        Fisica fisica = (Fisica) v.getTag();
        switch (v.getId()) {
            case R.id.click:
                RespuestasFragment respuestasFragment = new RespuestasFragment ();
                Bundle args = new Bundle();
                args.putString("pregunta", fisicalist.get(position).pregunta);
                args.putString("usuario", fisicalist.get(position).usuario);
                args.putString("qid",fisicalist.get(position).fireid);

                respuestasFragment.setArguments(args);
                fy.replace(R.id.frame, respuestasFragment).commit();
                break;
        }
    }
    @Override
    public void passposition(int position) {

    }

    @Override
    public void passthings(String pregunta, String usuario) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}