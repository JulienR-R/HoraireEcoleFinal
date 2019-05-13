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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UserHoraireChoice_Adapter extends ListAdapter<UserHoraireChoice_Item, UserHoraireChoice_Adapter.Horaire_Choice_Holder> {


    private OnItemClickListener listener;

    public UserHoraireChoice_Adapter(){
        super(DIFF_CALLBACK);

    }
    private static final DiffUtil.ItemCallback<UserHoraireChoice_Item> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserHoraireChoice_Item>() {
        @Override
        public boolean areItemsTheSame(UserHoraireChoice_Item oldItem, UserHoraireChoice_Item newItem) {
            return oldItem.getmDescription() == newItem.getmDescription();
        }

        @Override
        public boolean areContentsTheSame(UserHoraireChoice_Item oldItem, UserHoraireChoice_Item newItem) {
            return String.valueOf(oldItem.getmImageResource()).equals(String.valueOf(newItem.getmImageResource())) &&
                    oldItem.getmHeureFin().equals(newItem.getmHeureFin()) &&
                    oldItem.getmDate() == newItem.getmDate();


        }
    };


    @NonNull
    @Override
    public Horaire_Choice_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_horairechoice_item, viewGroup, false);
            return new Horaire_Choice_Holder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull Horaire_Choice_Holder holder, int position) {
        UserHoraireChoice_Item item = getItem(position);
        holder.imageView.setImageResource(item.getmImageResource());
        holder.tDescription.setText(item.getmDescription());
        holder.tDate.setText("Date: "+item.getmDate());
        holder.tHeureDebut.setText("Heure début: " + item.getmHeureDebut());
        holder.tHeureFin.setText("Heure fini: " + item.getmHeureFin());

    }


    public interface OnItemClickListener{
        void onItemClick(UserHoraireChoice_Item userHoraire_choice_item);
    }

    public void setOnItemClickListener(OnItemClickListener mListener){

        listener = mListener;

    }

    public UserHoraireChoice_Item getChoiceAt(int position) {
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
