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

public class MygeneralesRecyclerViewAdapter extends RecyclerView.Adapter<MygeneralesRecyclerViewAdapter.MygeneralesRecyclerViewAdapterViewHolder> {

    private ArrayList<Generales> generaleslist;
    private RecyclerViewOnItemClickListener onRecyclerViewItemClickListener;



    public MygeneralesRecyclerViewAdapter(ArrayList<Generales> generaleslist) {
        this.generaleslist = generaleslist;
    }

    @NonNull
    @Override
    public MygeneralesRecyclerViewAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_generales,parent,false);
        return new MygeneralesRecyclerViewAdapterViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MygeneralesRecyclerViewAdapterViewHolder holder, int position) {
        Generales generales=generaleslist.get(position);
        holder.bindGenerales(generales);
    }

    @Override
    public int getItemCount() {
        return generaleslist.size();
    }

    public class MygeneralesRecyclerViewAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tPregunta,tUsuario,tdate;
        private LinearLayout click;

        public MygeneralesRecyclerViewAdapterViewHolder(View itemView) {
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

        public void bindGenerales(Generales generales){
            tPregunta.setText(" "+generales.getPregunta());
            tUsuario.setText("Usuario: "+generales.getUsuario());
            tdate.setText(generales.getDate());
        }
    }
    public void setOnRecyclerViewItemClickListener(RecyclerViewOnItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
