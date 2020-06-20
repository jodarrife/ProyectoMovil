package com.example.optimitour.Auxiliares;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.optimitour.Entidades.Turismo;
import com.example.optimitour.R;

import java.util.ArrayList;

public class AdapterTurismo extends RecyclerView.Adapter<AdapterTurismo.ViewHolder> implements View.OnClickListener {

    ArrayList<Turismo> model;
    LayoutInflater inflater;
    //Listener
    private View.OnClickListener listener;

    public AdapterTurismo(Context context, ArrayList<Turismo> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.turismo_list, parent, false);
        view.setOnClickListener(this);
        return new AdapterTurismo.ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTurismo.ViewHolder holder, int position) {
        String nombre = model.get(position).getNombre_turismo();
        int imagen = model.get(position).getImagenId();
        holder.nombreTurismo.setText(nombre);
        holder.imagen.setImageResource(imagen);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombreTurismo;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTurismo = itemView.findViewById(R.id.turismoName);
            imagen = itemView.findViewById(R.id.turismoImage);
        }
    }
}
