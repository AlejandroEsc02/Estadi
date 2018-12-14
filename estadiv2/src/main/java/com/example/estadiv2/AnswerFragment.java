package com.example.estadiv2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AnswerFragment extends Fragment  {
    private EditText erespuesta;
    private Button bsalir, bresponder;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    String qid, pregunta, usuario;


    public AnswerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answer, container, false);

        erespuesta = view.findViewById(R.id.erespuesta);
        bsalir = view.findViewById(R.id.bsalir);
        bresponder = view.findViewById(R.id.bguardar);

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        Bundle args = getArguments();
        if (args != null) {
            qid=args.getString("qid");
            pregunta=args.getString("pregunta");
            usuario=args.getString("usuario");
        }


        bresponder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String respuesta;
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String email=user.getEmail();

                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String strDate = dateFormat.format(date).toString();

                if (erespuesta.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Digite todos los campos." ,
                            Toast.LENGTH_SHORT).show();
                } else {

                    respuesta = erespuesta.getText().toString();
                    database = FirebaseDatabase.getInstance();
                    myRef = database.getReference(qid);
                    Respuestas respuestas = new Respuestas(respuesta, email, myRef.push().getKey(),strDate);
                    myRef.child(respuesta).setValue(respuestas);
                    Toast.makeText(getContext(), "Hecho!.",
                            Toast.LENGTH_SHORT).show();

                    RespuestasFragment respuestasFragment = new RespuestasFragment();
                    Bundle args = new Bundle();
                    args.putString("qid", qid);
                    args.putString("usuario", usuario);
                    args.putString("pregunta", pregunta);
                    respuestasFragment.setArguments(args);
                    ft.replace(R.id.frame, respuestasFragment).commit();

                }
            }
        });

        bsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RespuestasFragment respuestasFragment = new RespuestasFragment();
                Bundle args = new Bundle();
                args.putString("qid", qid);
                args.putString("usuario", usuario);
                args.putString("pregunta", pregunta);
                respuestasFragment.setArguments(args);
                ft.replace(R.id.frame, respuestasFragment).commit();

            }
        });

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}