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

public class MyrespuestasRecyclerViewAdapter extends RecyclerView.Adapter<MyrespuestasRecyclerViewAdapter.MyrespuestasRecyclerViewAdapterViewHolder> {

    private ArrayList<Respuestas> respuestaslist;
    private RecyclerViewOnItemClickListener onRecyclerViewItemClickListener;



    public MyrespuestasRecyclerViewAdapter(ArrayList<Respuestas> respuestaslist) {
        this.respuestaslist = respuestaslist;
    }

    @NonNull
    @Override
    public MyrespuestasRecyclerViewAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_respuestas,parent,false);
        return new MyrespuestasRecyclerViewAdapterViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyrespuestasRecyclerViewAdapterViewHolder holder, int position) {
        Respuestas respuestas=respuestaslist.get(position);
        holder.bindRespuestas(respuestas);
    }

    @Override
    public int getItemCount() {
        return respuestaslist.size();
    }

    public class MyrespuestasRecyclerViewAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tRespuesta,tUsuario,tdate;
        private LinearLayout click;

        public MyrespuestasRecyclerViewAdapterViewHolder(View itemView) {
            super(itemView);

            tUsuario = itemView.findViewById(R.id.tUsuario);
            tRespuesta=itemView.findViewById(R.id.tRespuesta);
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

        public void bindRespuestas(Respuestas respuestas){
            tRespuesta.setText(" "+respuestas.getRespuesta());
            tUsuario.setText("Por: "+respuestas.getUsuario());
            tdate.setText(respuestas.getDate());
        }
    }
    public void setOnRecyclerViewItemClickListener(RecyclerViewOnItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}