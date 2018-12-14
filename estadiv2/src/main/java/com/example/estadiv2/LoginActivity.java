package com.example.estadiv2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private EditText eusername,epassword;
    String name,pass;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eusername=findViewById(R.id.eusername);
        epassword=findViewById(R.id.epassword);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            gomaina();
        }
    }

    private void gomaina() {
        Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent1);

    }

    public void gomain (View view){
        String name1,pass1;
        name1 = eusername.getText().toString();
        pass1 = epassword.getText().toString();

        if (name1.isEmpty() || pass1.isEmpty()){
            Toast.makeText(getApplicationContext(), "Digite todos los campos.",
                    Toast.LENGTH_SHORT).show();
        }else {
            mAuth.signInWithEmailAndPassword(name1, pass1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                gomaina();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(), "Contraseña o Usuario inválido.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }


    public void goregistro (View view){
        Intent intent2 =new Intent(LoginActivity.this,RegistroActivity.class);
        startActivity(intent2);

    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
