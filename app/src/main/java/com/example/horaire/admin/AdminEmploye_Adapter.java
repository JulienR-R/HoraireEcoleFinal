package com.example.horaire.admin;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import com.example.horaire.R;
import com.example.horaire.database.User;

import java.util.ResourceBundle;

public class AdminEmploye_Adapter extends ListAdapter<User, AdminEmploye_Adapter.EmpHolder> {

    private OnItemClickListener listener;

    public AdminEmploye_Adapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(User oldItem, User newItem) {
            return oldItem.get_id() == newItem.get_id();
        }

        @Override
        public boolean areContentsTheSame(User oldItem, User newItem) {
            return oldItem.getNom().equals(newItem.getNom()) &&
                    oldItem.getPrenom().equals(newItem.getPrenom()) &&
                    oldItem.getSeniority() == newItem.getSeniority() &&
                    oldItem.getUserId() == newItem.getUserId();


        }
    };

    @NonNull
    @Override
    public EmpHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_emp_detail, parent, false);
        return new EmpHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpHolder holder, int position) {
        User currentUser = getItem(position);
        Context temp = holder.itemView.getContext();
        holder.textViewNom.setText(temp.getString(R.string.nomEmp) + " : " + currentUser.getNom());
        holder.textViewPrenom.setText(temp.getString(R.string.prenomEmp) + " : " + currentUser.getPrenom());
        holder.textViewEmail.setText(temp.getString(R.string.emailEmp) + " : " + currentUser.getUserId());
        holder.textViewSenority.setText(temp.getString(R.string.seniorityEmp) + " : " + String.valueOf(currentUser.getSeniority()));

    }





    public User getClientAt(int position) {
        return getItem(position);
    }


    class EmpHolder extends RecyclerView.ViewHolder {
        private TextView textViewNom;
        private TextView textViewPrenom;
        private TextView textViewEmail;
        private TextView textViewSenority;
        private ImageView imageView;


        public EmpHolder(View itemView) {
            super(itemView);
            textViewNom = itemView.findViewById(R.id.text_view_nom);
            textViewPrenom = itemView.findViewById(R.id.text_view_prenom);
            textViewEmail = itemView.findViewById(R.id.text_view_courriel );
            textViewSenority = itemView.findViewById(R.id.text_view_anciennete );
            imageView = itemView.findViewById(R.id.image_create);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onCreateClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(User user);
        void onCreateClick(User user);
    }





    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}



