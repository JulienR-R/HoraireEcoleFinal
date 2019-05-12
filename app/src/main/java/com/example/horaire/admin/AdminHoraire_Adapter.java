package com.example.horaire.admin;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.horaire.R;
import com.example.horaire.database.PlageHoraire;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AdminHoraire_Adapter extends ListAdapter<PlageHoraire, AdminHoraire_Adapter.HoraireHolder> {

    private OnItemClickListener listener;

    public AdminHoraire_Adapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<PlageHoraire> DIFF_CALLBACK = new DiffUtil.ItemCallback<PlageHoraire>() {
        @Override
        public boolean areItemsTheSame(PlageHoraire oldItem, PlageHoraire newItem) {
            return oldItem.get_id() == newItem.get_id();
        }

        @Override
        public boolean areContentsTheSame(PlageHoraire oldItem, PlageHoraire newItem) {
            return oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.getHeureDebut() == newItem.getHeureDebut() &&
                    oldItem.getHeureFin() == newItem.getHeureFin() &&
                    oldItem.getEffectif() == newItem.getEffectif();


        }
    };

    @NonNull
    @Override
    public HoraireHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_horaire_detail, parent, false);
        return new HoraireHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HoraireHolder holder, int position) {
        PlageHoraire plageHoraire = getItem(position);

        Context temp = holder.itemView.getContext();
        holder.textViewDescription.setText(temp.getString(R.string.descriptionHoraire) + " : " + plageHoraire.getDescription());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        holder.textViewDate.setText(temp.getString(R.string.dateHoraire) + " : " + dateFormat.format(plageHoraire.getDate()));
        holder.textViewHeureDebut.setText(temp.getString(R.string.timeBeginHoraire) + " : " + String.valueOf(plageHoraire.getHeureDebut()));
        holder.textViewHeureFin.setText(temp.getString(R.string.timeEndHoraire) + " : " + String.valueOf(plageHoraire.getHeureFin()));
        holder.textViewEffectif.setText(temp.getString(R.string.nbEmployeHoraire) + " : " + String.valueOf(plageHoraire.getEffectif()));

    }





    public PlageHoraire getPlageAt(int position) {
        return getItem(position);
    }


    class HoraireHolder extends RecyclerView.ViewHolder {
        private TextView textViewDescription;
        private TextView textViewDate;
        private TextView textViewHeureDebut;
        private TextView textViewHeureFin;
        private TextView textViewEffectif;


        public HoraireHolder(View itemView) {
            super(itemView);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewDate = itemView.findViewById(R.id.text_view_date);
            textViewHeureDebut = itemView.findViewById(R.id.text_view_heure_debut );
            textViewHeureFin = itemView.findViewById(R.id.text_view_heure_fin );
            textViewEffectif = itemView.findViewById(R.id.text_view_effectif );

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PlageHoraire plageHoraire);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}



