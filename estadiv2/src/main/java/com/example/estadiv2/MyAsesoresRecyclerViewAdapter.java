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

public class MyAsesoresRecyclerViewAdapter extends RecyclerView.Adapter<MyAsesoresRecyclerViewAdapter.MyAsesoresRecyclerViewAdapterViewHolder> {

    private ArrayList<Asesores> asesoreslist;
    private RecyclerViewOnItemClickListener onRecyclerViewItemClickListener;



    public MyAsesoresRecyclerViewAdapter(ArrayList<Asesores> asesoreslist) {
        this.asesoreslist = asesoreslist;
    }

    @NonNull
    @Override
    public MyAsesoresRecyclerViewAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_asesores,parent,false);
        return new MyAsesoresRecyclerViewAdapterViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAsesoresRecyclerViewAdapterViewHolder holder, int position) {
        Asesores asesores=asesoreslist.get(position);
        holder.bindAsesores(asesores);
    }

    @Override
    public int getItemCount() {
        return asesoreslist.size();
    }

    public class MyAsesoresRecyclerViewAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tUsuario,tAsesoria;
        private LinearLayout click;

        public MyAsesoresRecyclerViewAdapterViewHolder(View itemView) {
            super(itemView);

            tUsuario = itemView.findViewById(R.id.tUsuario);
            tAsesoria=itemView.findViewById(R.id.tAsesoria);

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

        public void bindAsesores(Asesores asesores){
            tUsuario.setText("Usuario: "+asesores.getNombre());
            tAsesoria.setText("Asesoría: "+asesores.getAsesoria());
        }
    }
    public void setOnRecyclerViewItemClickListener(RecyclerViewOnItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
