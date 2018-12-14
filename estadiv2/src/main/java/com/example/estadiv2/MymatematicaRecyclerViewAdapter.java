package com.example.estadiv2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MymatematicaRecyclerViewAdapter extends RecyclerView.Adapter<MymatematicaRecyclerViewAdapter.MymatematicaRecyclerViewAdapterViewHolder> {

    private ArrayList<Matematica> matematicalist;
    private RecyclerViewOnItemClickListener onRecyclerViewItemClickListener;



    public MymatematicaRecyclerViewAdapter(ArrayList<Matematica> matematicalist) {
        this.matematicalist = matematicalist;
    }

    @NonNull
    @Override
    public MymatematicaRecyclerViewAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_matematica,parent,false);
        return new MymatematicaRecyclerViewAdapterViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MymatematicaRecyclerViewAdapterViewHolder holder, int position) {
        Matematica matematica=matematicalist.get(position);
        holder.bindMatematica(matematica);
    }

    @Override
    public int getItemCount() {
        return matematicalist.size();
    }

    public class MymatematicaRecyclerViewAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tPregunta,tUsuario,tdate;
        private LinearLayout click;

        public MymatematicaRecyclerViewAdapterViewHolder(View itemView) {
            super(itemView);

            tUsuario = itemView.findViewById(R.id.tUsuario);
            tPregunta=itemView.findViewById(R.id.tPregunta);
            tdate=itemView.findViewById(R.id.tdate);

            click = itemView.findViewById(R.id.click);

            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyclerViewItemClickListener != null) {
                        onRecyclerViewItemClickListener.onClick(v, getAdapterPosition());

                    }
                }
            });
        }

        public void bindMatematica(Matematica matematica){
            tPregunta.setText(" "+matematica.getPregunta());
            tUsuario.setText("Usuario: "+matematica.getUsuario());
            tdate.setText(matematica.getDate());
        }
    }
    public void setOnRecyclerViewItemClickListener(RecyclerViewOnItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}