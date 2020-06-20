package com.example.optimitour.Auxiliares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.optimitour.Entidades.Favoritos;
import com.example.optimitour.Entidades.Historias;
import com.example.optimitour.R;

import java.util.ArrayList;

public class AdapterFavorito extends RecyclerView.Adapter<AdapterFavorito.ViewHolder> implements View.OnClickListener{

    ArrayList<Favoritos> model;
    LayoutInflater inflater;
    //Listener
    private View.OnClickListener listener;

    public AdapterFavorito(Context context, ArrayList<Favoritos> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }
    @NonNull
    @Override
    public AdapterFavorito.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.favorito_list, parent, false);
        view.setOnClickListener(this);
        return new AdapterFavorito.ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavorito.ViewHolder holder, int position) {
        String nombre = model.get(position).getNombre_Favoritos();
    //    String municipio = model.get(position).getMunicipio_Sitio();
      //  int imagen = model.get(position).getImagenId();
        holder.nombreSitio.setText(nombre);
        //holder.municipioSitio.setText(municipio);
        //holder.imagen.setImageResource(imagen);7
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

