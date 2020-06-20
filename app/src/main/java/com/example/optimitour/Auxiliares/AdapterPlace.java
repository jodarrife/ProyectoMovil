package com.example.optimitour.Auxiliares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.optimitour.Entidades.Sitios;
import com.example.optimitour.R;

import java.util.ArrayList;

public class AdapterPlace extends RecyclerView.Adapter<AdapterPlace.ViewHolder> implements View.OnClickListener{

    ArrayList<Sitios> model;
    LayoutInflater inflater;
    //Listener
    private View.OnClickListener listener;

    public AdapterPlace(Context context, ArrayList<Sitios> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.place_list, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre = model.get(position).getNombre_Sitio();
        String municipio = model.get(position).getMunicipio_Sitio();
        int imagen = model.get(position).getImagenId();
        holder.nombreSitio.setText(nombre);
        holder.municipioSitio.setText(municipio);
        holder.imagen.setImageResource(imagen);
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

        TextView nombreSitio, municipioSitio;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreSitio = itemView.findViewById(R.id.placeName);
            municipioSitio = itemView.findViewById(R.id.municipioSitio);
            imagen = itemView.findViewById(R.id.placeImage);
        }
    }
}
