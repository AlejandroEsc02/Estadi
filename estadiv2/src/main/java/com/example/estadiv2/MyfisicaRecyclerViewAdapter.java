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

public class MyfisicaRecyclerViewAdapter extends RecyclerView.Adapter<MyfisicaRecyclerViewAdapter.MyfisicaRecyclerViewAdapterViewHolder> {

    private ArrayList<Fisica> fisicalist;
    private RecyclerViewOnItemClickListener onRecyclerViewItemClickListener;



    public MyfisicaRecyclerViewAdapter(ArrayList<Fisica> fisicalist) {
        this.fisicalist = fisicalist;
    }

    @NonNull
    @Override
    public MyfisicaRecyclerViewAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_fisica,parent,false);
        return new MyfisicaRecyclerViewAdapterViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyfisicaRecyclerViewAdapterViewHolder holder, int position) {
        Fisica fisica=fisicalist.get(position);
        holder.bindFisica(fisica);
    }

    @Override
    public int getItemCount() {
        return fisicalist.size();
    }

    public class MyfisicaRecyclerViewAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tPregunta,tUsuario,tdate;
        private LinearLayout click;

        public MyfisicaRecyclerViewAdapterViewHolder(View itemView) {
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

        public void bindFisica(Fisica fisica){
            tPregunta.setText(" "+fisica.getPregunta());
            tUsuario.setText("Usuario: "+fisica.getUsuario());
            tdate.setText(fisica.getDate());
        }
    }
    public void setOnRecyclerViewItemClickListener(RecyclerViewOnItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}