package com.example.estadiv2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    private EditText eusername, epassword,erepassword;
    private CheckBox chmatematica, chfisica, chsociales;
    private static String user,pass, repass,asesorias=" ",asesor;
    private String sexo = "masculino";
    private FirebaseDatabase database;
    private DatabaseReference myRef,myRefA;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        eusername=findViewById(R.id.eusername);
        epassword=findViewById(R.id.epassword);
        erepassword=findViewById(R.id.erepassword);
        chmatematica=findViewById(R.id.chmatematica);
        chfisica=findViewById(R.id.chfisica);
        chsociales=findViewById(R.id.chsociales);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Usuarios");
        myRefA = database.getReference("Asesores");

        mAuth = FirebaseAuth.getInstance();

    }

    public void gologinr(View view){

        user = eusername.getText().toString();
        pass = epassword.getText().toString();
        repass = erepassword.getText().toString();

        if (user.isEmpty() || pass.isEmpty() || repass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Digite todos los campos.",
                    Toast.LENGTH_SHORT).show();
        }else{
            if (repass.equals(pass)){

                mAuth.createUserWithEmailAndPassword(user, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    asesorias=" ";

                                    if (chmatematica.isChecked() || chfisica.isChecked()|| chsociales.isChecked()) {
                                        if (chmatematica.isChecked()) asesorias = asesorias + " Matematica";
                                        if (chfisica.isChecked()) asesorias = asesorias + " Fisica";
                                        if (chsociales.isChecked()) asesorias = asesorias + " Sociales";
                                        asesor="Si";
                                        FirebaseUser userc = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid=userc.getUid();
                                        Usuarios usuarios=new Usuarios(user,sexo,asesor,uid);

                                        myRef.child(uid).setValue(usuarios);
                                        Asesores asesores = new Asesores(user, asesorias,uid);
                                        myRefA.child(uid).setValue(asesores);
                                        Toast.makeText(getApplicationContext(), "Registrado.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent2 = new Intent(RegistroActivity.this, MainActivity.class);
                                        startActivity(intent2);
                                        finish();
                                    }else {
                                        asesor="No";
                                        FirebaseUser userc = FirebaseAuth.getInstance().getCurrentUser();
                                        String uid=userc.getUid();
                                        Usuarios usuarios=new Usuarios(user,sexo,asesor,uid);
                                        myRef.child(uid).setValue(usuarios);
                                        Toast.makeText(getApplicationContext(), "Registrado.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent2 = new Intent(RegistroActivity.this, MainActivity.class);
                                        startActivity(intent2);
                                        finish();

                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Registro fallido.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            } else {
                Toast.makeText(getApplicationContext(), "Contraseñas no iguales.",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void gomain (View view){
        Intent intent2 = new Intent(RegistroActivity.this,MainActivity.class);
        startActivity(intent2);
    }

    public void radioButtonClicker(View view) {
        int id = view.getId();

        if (id == R.id.rbhombre) {
            sexo = "Masculino";
        } else {
            sexo = "Femenino";
        }
    }

    public void gologin(View view) {
        Intent intent2 = new Intent(RegistroActivity.this,LoginActivity.class);
        startActivity(intent2);
    }
}