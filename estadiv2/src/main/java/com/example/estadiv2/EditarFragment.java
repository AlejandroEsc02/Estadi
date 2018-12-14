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
import android.widget.CheckBox;
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


public class EditarFragment extends Fragment{
    private CheckBox chmatematica,chfisica,chsociales;
    private Button bsalir;
    private ImageButton bguardar;
    private FirebaseAuth mAuth;
    private DatabaseReference myRef,myRefA;
    private FirebaseDatabase database;

    private FragmentManager fm;
    private FragmentTransaction fy;
    String uid,asesorias=" ",nombre,sexo;
    boolean band=true;


    public EditarFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar, container, false);

        chmatematica=view.findViewById(R.id.chmatematica);
        chfisica=view.findViewById(R.id.chfisica);
        chsociales=view.findViewById(R.id.chsociales);
        bguardar=view.findViewById(R.id.bguardar);
        bsalir=view.findViewById(R.id.bsalir);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        nombre=String.valueOf(user.getEmail());

        fm = getFragmentManager();
        fy = fm.beginTransaction();


        bguardar.setOnClickListener(new View.OnClickListener() {
            String a="pase";
            @Override
            public void onClick(View v) {



                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("Usuarios");
                myRefA=database.getReference("Asesores");
                myRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.child(uid).exists()) {
                            String si = "Si", no = "No", aseso;
                            Usuarios usuarios = dataSnapshot.child(uid).getValue(Usuarios.class);
                            aseso = String.valueOf(usuarios.asesor);
                            if (a.equals("pase")) {
                                if (aseso.equals(si)) {
                                    if (chmatematica.isChecked() || chfisica.isChecked() || chsociales.isChecked()) {
                                        if (chmatematica.isChecked())
                                            asesorias = asesorias + " Matematica";
                                        if (chfisica.isChecked()) asesorias = asesorias + " Fisica";
                                        if (chsociales.isChecked())
                                            asesorias = asesorias + " Sociales";
                                        myRefA.child(uid).child("asesoria").setValue(asesorias);
                                        a="No";


                                    } else {
                                        myRefA.child(uid).removeValue();
                                        myRef.child(uid).child("asesor").setValue("No");
                                        a="No";
                                    }
                                } else {
                                    if (chmatematica.isChecked() || chfisica.isChecked() || chsociales.isChecked()) {
                                        if (chmatematica.isChecked())
                                            asesorias = asesorias + " Matematica";
                                        if (chfisica.isChecked()) asesorias = asesorias + " Fisica";
                                        if (chsociales.isChecked())
                                            asesorias = asesorias + " Sociales";
                                        Asesores asesoresf = new Asesores(nombre, asesorias, uid);
                                        myRefA.child(uid).setValue(asesoresf);
                                        myRef.child(uid).child("asesor").setValue("Si");
                                        a="No";
                                    } else {
                                        a="No";

                                    }
                                }
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });getout();


            }

        });

        bsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getout();
            }
        });

        return view;
    }

    private void getout(){
        PerfilFragment perfilFragment = new PerfilFragment();
        fy.replace(R.id.frame, perfilFragment).commit();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}