package com.example.optimitour.Auxiliares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.optimitour.Entidades.Municipio;
import com.example.optimitour.R;

import java.util.ArrayList;

public class AdapterMunicipios extends RecyclerView.Adapter<AdapterMunicipios.ViewHolder> implements View.OnClickListener{

    ArrayList<Municipio> model;
    LayoutInflater inflater;
    //Listener
    private View.OnClickListener listener;

    public AdapterMunicipios(Context context, ArrayList<Municipio> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_municipios, parent, false);
        view.setOnClickListener(this);
        return new AdapterMunicipios.ViewHolder(view);
    }


    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombre = model.get(position).getNombre_Municipio();
        int imagen = model.get(position).getImagenId();
        holder.nombreMunicipio.setText(nombre);
        holder.imagenMunicipio.setImageResource(imagen);
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

        TextView nombreMunicipio;
        ImageView imagenMunicipio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreMunicipio = itemView.findViewById(R.id.nombreMuni);
            imagenMunicipio = itemView.findViewById(R.id.imagenMuni);
        }
    }
}

