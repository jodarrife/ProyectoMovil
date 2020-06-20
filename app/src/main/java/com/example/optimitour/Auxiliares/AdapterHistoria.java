package com.example.optimitour.Auxiliares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.optimitour.Entidades.Historias;
import com.example.optimitour.Entidades.Sitios;
import com.example.optimitour.R;

import java.util.ArrayList;

public class AdapterHistoria extends RecyclerView.Adapter<AdapterHistoria.ViewHolder> implements View.OnClickListener{

    ArrayList<Historias> model;
    LayoutInflater inflater;
    //Listener
    private View.OnClickListener listener;

    public AdapterHistoria(Context context, ArrayList<Historias> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.historia_list, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre = model.get(position).getNombre_Historia();
        String municipio = model.get(position).getMunicipio_Historia();
        int imagen = model.get(position).getImagenId();
        holder.nombreHistoria.setText(nombre);
        holder.municipioHistoria.setText(municipio);
        holder.imagenHistoria.setImageResource(imagen);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombreHistoria, municipioHistoria;
        ImageView imagenHistoria;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreHistoria = itemView.findViewById(R.id.historiaName);
            municipioHistoria = itemView.findViewById(R.id.municipioHistoria);
            imagenHistoria = itemView.findViewById(R.id.historiaImage);
        }
    }
}
