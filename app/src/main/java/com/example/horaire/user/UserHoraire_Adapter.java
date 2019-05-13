package com.example.horaire.user;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.horaire.R;
import com.example.horaire.database.AttributionPlageHoraire;

public class UserHoraire_Adapter extends ListAdapter<AttributionPlageHoraire, UserHoraire_Adapter.Horaire_Holder> {



    private UserHoraire_Adapter.OnItemClickListener listener;

    public UserHoraire_Adapter(){
        super(DIFF_CALLBACK);

    }
    private static final DiffUtil.ItemCallback<AttributionPlageHoraire> DIFF_CALLBACK = new DiffUtil.ItemCallback<AttributionPlageHoraire>() {
        @Override
        public boolean areItemsTheSame(AttributionPlageHoraire oldItem, AttributionPlageHoraire newItem) {
            return oldItem.get_id() == newItem.get_id();
        }

        @Override
        public boolean areContentsTheSame(AttributionPlageHoraire oldItem, AttributionPlageHoraire newItem) {
            return String.valueOf(oldItem.getIdChoixPlageHoraire()).equals(String.valueOf(newItem.getIdChoixPlageHoraire()));



        }
    };


    @NonNull
    @Override
    public UserHoraire_Adapter.Horaire_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_horaireview_item, viewGroup, false);
        return new UserHoraire_Adapter.Horaire_Holder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull UserHoraire_Adapter.Horaire_Holder holder, int position) {
        AttributionPlageHoraire item = getItem(position);

        holder.tDescription.setText(item.toString());


    }

    public interface OnItemClickListener{
        void onItemClick(AttributionPlageHoraire attributionPlageHoraire);
    }

    public void setOnItemClickListener(UserHoraire_Adapter.OnItemClickListener mListener){

        listener = mListener;

    }

    public AttributionPlageHoraire getChoiceAt(int position) {
        return getItem(position);
    }

    class Horaire_Holder extends RecyclerView.ViewHolder{

        private TextView tDescription;


        public Horaire_Holder(@NonNull View itemView) {
            super(itemView);

            tDescription = itemView.findViewById(R.id.content);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if(listener != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(getItem(position));
                        }
                    }

                }
            });
        }
    }
}



