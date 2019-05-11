package com.example.horaire.user;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.horaire.R;
import com.example.horaire.database.PlageHoraire;
import com.example.horaire.database.User;

import java.util.ArrayList;

public class Horaire_Choice_Adapter extends ListAdapter<Horaire_Choice_Item, Horaire_Choice_Adapter.Horaire_Choice_Holder> {


    private OnItemClickListener listener;

    public Horaire_Choice_Adapter(){
        super(DIFF_CALLBACK);

    }
    private static final DiffUtil.ItemCallback<Horaire_Choice_Item> DIFF_CALLBACK = new DiffUtil.ItemCallback<Horaire_Choice_Item>() {
        @Override
        public boolean areItemsTheSame(Horaire_Choice_Item oldItem, Horaire_Choice_Item newItem) {
            return oldItem.getmDescription() == newItem.getmDescription();
        }

        @Override
        public boolean areContentsTheSame(Horaire_Choice_Item oldItem, Horaire_Choice_Item newItem) {
            return String.valueOf(oldItem.getmImageResource()).equals(String.valueOf(newItem.getmImageResource())) &&
                    oldItem.getmHeureFin().equals(newItem.getmHeureFin()) &&
                    oldItem.getmDate() == newItem.getmDate();


        }
    };


    @NonNull
    @Override
    public Horaire_Choice_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.horaire_choice_items, viewGroup, false);
            return new Horaire_Choice_Holder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull Horaire_Choice_Holder holder, int position) {
        Horaire_Choice_Item item = getItem(position);
        holder.imageView.setImageResource(item.getmImageResource());
        holder.tDescription.setText(item.getmDescription());
        holder.tDate.setText("Date: "+item.getmHeureDebut());
        holder.tHeureDebut.setText("Heure début: " + item.getmHeureDebut());
        holder.tHeureFin.setText("Heure fini: " + item.getmHeureFin());

    }


    public interface OnItemClickListener{
        void onItemClick(Horaire_Choice_Item horaire_choice_item);
    }

    public void setOnItemClickListener(OnItemClickListener mListener){

        listener = mListener;

    }

    public Horaire_Choice_Item getChoiceAt(int position) {
        return getItem(position);
    }




 class Horaire_Choice_Holder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView tDescription;
        private TextView tDate;
        private TextView tHeureFin;
        private TextView tHeureDebut;

        public Horaire_Choice_Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tDescription = itemView.findViewById(R.id.description);
            tDate = itemView.findViewById(R.id.date );
            tHeureFin = itemView.findViewById(R.id.heureFin );
            tHeureDebut = itemView.findViewById(R.id.heureDebut );

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
