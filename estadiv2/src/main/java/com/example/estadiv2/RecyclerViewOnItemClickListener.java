package com.example.estadiv2;


import android.view.View;

public interface RecyclerViewOnItemClickListener {
    void onClick(View v, int position);
    void passposition(int posi);
    void passthings(String pregunta,String usuario);
}
