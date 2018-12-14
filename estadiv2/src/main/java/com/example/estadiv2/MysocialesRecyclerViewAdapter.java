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

public class MysocialesRecyclerViewAdapter extends RecyclerView.Adapter<MysocialesRecyclerViewAdapter.MysocialesRecyclerViewAdapterViewHolder> {

    private ArrayList<Sociales> socialeslist;
    private RecyclerViewOnItemClickListener onRecyclerViewItemClickListener;


    public MysocialesRecyclerViewAdapter(ArrayList<Sociales> socialeslist) {
        this.socialeslist = socialeslist;
    }

    @NonNull
    @Override
    public MysocialesRecyclerViewAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_sociales,parent,false);
        return new MysocialesRecyclerViewAdapterViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MysocialesRecyclerViewAdapterViewHolder holder, int position) {
        Sociales sociales=socialeslist.get(position);
        holder.bindSociales(sociales);
    }

    @Override
    public int getItemCount() {
        return socialeslist.size();
    }

    public class MysocialesRecyclerViewAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tPregunta,tUsuario,tdate;
        private LinearLayout click;

        public MysocialesRecyclerViewAdapterViewHolder(View itemView) {
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

        public void bindSociales(Sociales sociales){
            tPregunta.setText(" "+sociales.getPregunta());
            tUsuario.setText("Usuario: "+sociales.getUsuario());
            tdate.setText(sociales.getDate());
        }
    }
    public void setOnRecyclerViewItemClickListener(RecyclerViewOnItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
