package com.example.estadiv2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class PerfilFragment extends Fragment{
    private TextView tEmail,tSexo,tAsesor;
    private ImageButton beditar;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef,myRefA;
    private FirebaseDatabase database;

    private FragmentManager fm;
    private FragmentTransaction fy;
    String uid,asesorias,nombre,sexo,asesor,email;



    public PerfilFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        tEmail=view.findViewById(R.id.tEmail);
        tSexo=view.findViewById(R.id.tSexo);
        tAsesor=view.findViewById(R.id.tAsesor);
        beditar=view.findViewById(R.id.beditar);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        email=user.getEmail();

        fm = getFragmentManager();
        fy = fm.beginTransaction();

        loadData();

        beditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditarFragment editarFragment = new EditarFragment();
                fy.replace(R.id.frame, editarFragment).commit();

            }
        });

        return view;
    }

    private void loadData(){

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Usuarios");
        myRefA=database.getReference("Asesores");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(uid).exists()){

                    Usuarios usuarios = dataSnapshot.child(uid).getValue(Usuarios.class);
                    nombre=usuarios.nombre;
                    sexo=usuarios.sexo;
                    asesor=usuarios.asesor;

                    if (asesor.equals("No")) {
                        tEmail.setText("Usuario: " + email);
                        tSexo.setText("Sexo: " + sexo);
                        tAsesor.setText("Asesor: " + asesor);

                    }else{
                        myRefA.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.child(uid).exists()){

                                    Asesores asesores = dataSnapshot.child(uid).getValue(Asesores.class);
                                    tEmail.setText("Usuario: "+ email);
                                    tSexo.setText("Sexo: " + sexo);
                                    String oe=(" En "+asesores.asesoria.replace(" ",", ")).replace(" , , "," ");
                                    tAsesor.setText("Asesor: " +oe);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
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