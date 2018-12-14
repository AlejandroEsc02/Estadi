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


public class RespuestasFragment extends Fragment implements RecyclerViewOnItemClickListener{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyrespuestasRecyclerViewAdapter myrespuestasRecyclerViewAdapter;
    private ArrayList<Respuestas> respuestaslist;
    private FragmentManager fm;
    private FragmentTransaction fy;
    private RecyclerViewOnItemClickListener passe;
    String preguntall;
    private TextView tPregunta,tUsuario;


    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private FloatingActionButton fab;
    String qid,pregunta,usuario;

    public RespuestasFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_respuestas_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        respuestaslist = new ArrayList<>();
        myrespuestasRecyclerViewAdapter = new MyrespuestasRecyclerViewAdapter(respuestaslist);
        myrespuestasRecyclerViewAdapter.setOnRecyclerViewItemClickListener(this);
        recyclerView.setAdapter(myrespuestasRecyclerViewAdapter);


        tPregunta=view.findViewById(R.id.tPregunta);
        tUsuario=view.findViewById(R.id.tUsuario);

        fm = getFragmentManager();

        Bundle args = getArguments();
        if (args != null) {
            usuario=args.getString("usuario");
            pregunta=args.getString("pregunta");
            qid=args.getString("qid");
            tPregunta.setText(pregunta);
            tUsuario.setText("Por: "+usuario);

        }

        loadData();
        myrespuestasRecyclerViewAdapter.notifyDataSetChanged();

        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fy = fm.beginTransaction();
                AnswerFragment answerFragment = new AnswerFragment();
                Bundle args = new Bundle();
                args.putString("qid", qid);
                args.putString("usuario", usuario);
                args.putString("pregunta", pregunta);
                answerFragment.setArguments(args);
                fy.replace(R.id.frame, answerFragment).commit();

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v, int position) {
        switch (v.getId()) {
            case R.id.click:
                break;
        }
    }

    private void loadData() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        myRef.child(qid).orderByChild("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Respuestas respuestas =snapshot.getValue(Respuestas.class);
                    respuestaslist.add(respuestas);
                    myrespuestasRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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