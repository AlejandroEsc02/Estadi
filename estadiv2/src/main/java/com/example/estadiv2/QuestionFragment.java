package com.example.estadiv2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class QuestionFragment extends Fragment {
    private EditText epregunta;
    private Button bsalir, bpreguntar;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Spinner spinner;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    String[] cate = new String[]{"Matematica", "Fisica", "Sociales", "Generales"};
    String cat;



    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        epregunta = view.findViewById(R.id.epregunta);
        bsalir = view.findViewById(R.id.bsalir);
        bpreguntar = view.findViewById(R.id.bguardar);
        spinner = view.findViewById(R.id.spinner);

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, cate);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cat = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bpreguntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pregunta,fireid;
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email=user.getEmail();

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String strDate = dateFormat.format(date).toString();

                if (epregunta.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Digite todos los campos." + cat,
                            Toast.LENGTH_SHORT).show();
                } else {

                    pregunta = epregunta.getText().toString();
                    switch (cat) {
                        case "Matematica":
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference("Matematica");


                            fireid=myRef.push().getKey();
                            Matematica matematica = new Matematica(pregunta, email,fireid,strDate );
                            myRef.child(pregunta).setValue(matematica);


                            Map<String, String> b=ServerValue.TIMESTAMP;
                            Toast.makeText(getContext(), "Hecho!",
                                    Toast.LENGTH_SHORT).show();
                            break;

                        case "Fisica":
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference("Fisica");
                            fireid=myRef.push().getKey();
                            Fisica fisica = new Fisica(pregunta, email, fireid,strDate);
                            myRef.child(pregunta).setValue(fisica);

                            Toast.makeText(getContext(), "Hecho!",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case "Sociales":
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference("Sociales");
                            fireid=myRef.push().getKey();
                            Sociales sociales = new Sociales(pregunta, email, fireid,strDate);
                            myRef.child(pregunta).setValue(sociales);


                            Toast.makeText(getContext(), "Hecho!",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case "Generales":
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference("Generales");
                            fireid=myRef.push().getKey();
                            Generales generales = new Generales(pregunta, email, fireid,strDate);
                            myRef.child(pregunta).setValue(generales);


                            Toast.makeText(getContext(), "Hecho!",
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }

                    PreguntasFragment preguntasFragment = new PreguntasFragment();
                    ft.replace(R.id.frame, preguntasFragment).commit();

                }


            }
        });

        bsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PreguntasFragment preguntasFragment = new PreguntasFragment();
                ft.replace(R.id.frame, preguntasFragment).commit();

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}



