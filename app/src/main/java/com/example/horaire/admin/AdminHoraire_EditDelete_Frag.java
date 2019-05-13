package com.example.horaire.admin;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.horaire.R;
import com.example.horaire.database.HorairesDataBase;
import com.example.horaire.database.PlageHoraire;

import java.lang.ref.WeakReference;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdminHoraire_EditDelete_Frag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AdminHoraire_EditDelete_Frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminHoraire_EditDelete_Frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String HORAIRE = "horaire";

    private PlageHoraire plageHoraire;
    // TODO: Rename and change types of parameters
    private EditText editDescription;
    private EditText editDate;
    private EditText editEffectif;
    private EditText editHeureDebut;
    private EditText editHeureFin;
    private ProgressBar progressBar;
    private AppCompatActivity activity;
    private Button btnSave;

    private OnFragmentInteractionListener mListener;

    public AdminHoraire_EditDelete_Frag() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AdminHoraire_EditDelete_Frag newInstance(PlageHoraire mPlageHoraire) {
        AdminHoraire_EditDelete_Frag fragment = new AdminHoraire_EditDelete_Frag();
        Bundle args = new Bundle();
        args.putParcelable(HORAIRE, mPlageHoraire);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            plageHoraire = getArguments().getParcelable(HORAIRE);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.admin_horaire_edit_frag, container, false);

        activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle("Modifier");

        editDescription = view.findViewById(R.id.edit_description);
        editDate  = view.findViewById(R.id.edit_date);
        editEffectif = view.findViewById(R.id.edit_effectif);
        progressBar = view.findViewById(R.id.progress_bar);
        editHeureDebut =   view.findViewById(R.id.edit_heureDebut);
        editHeureFin = view.findViewById(R.id.edit_heureFin);
        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SaveHoraire);

        editDescription.setText(plageHoraire.getDescription() );
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        editDate.setText(df.format(plageHoraire.getDate()) );
        editHeureDebut.setText(String.valueOf(plageHoraire.getHeureDebut()) );
        editHeureFin.setText(String.valueOf(plageHoraire.getHeureFin()));
        editEffectif.setText(String.valueOf(plageHoraire.getEffectif()));
        editDescription.requestFocus();
        btnSave.setOnClickListener(SaveHoraire);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void closeFragment() {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    View.OnClickListener SaveHoraire = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            plageHoraire.setDescription(editDescription.getText().toString());


            try {
                java.util.Date df = new SimpleDateFormat("yyyy-MM-dd").parse(editDate.getText().toString());
                java.sql.Date du = convertUtilToSql(df);
                plageHoraire.setDate(du);
            }catch (ParseException e){
            }
            plageHoraire.setEffectif(Integer.parseInt(editEffectif.getText().toString()));



            plageHoraire.setHeureDebut(Time.valueOf(editHeureDebut.getText().toString()));
            plageHoraire.setHeureFin(Time.valueOf(editHeureDebut.getText().toString()));

            UpdateHoraireAsync updateHoraireAsync = new UpdateHoraireAsync(AdminHoraire_EditDelete_Frag.this);

            updateHoraireAsync.execute();



        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }


    private class UpdateHoraireAsync extends AsyncTask<Void,Void,Void> {


        private WeakReference<AdminHoraire_EditDelete_Frag> activityWeakReference;


        UpdateHoraireAsync(AdminHoraire_EditDelete_Frag activity) {

            activityWeakReference = new WeakReference<AdminHoraire_EditDelete_Frag>(activity);
        }
        @Override
        protected Void doInBackground(Void... voids) {

            HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(activity);



            horairesDataBase.plageHoraireAccess().updatePlageHoraire(plageHoraire);




            return null;
        }

        @Override
        protected void onPostExecute(Void voids){
            super.onPostExecute(voids);


            AdminHoraire_EditDelete_Frag activity = activityWeakReference.get();
            if (activity == null || activity.isResumed()) {
                mListener.onFragmentInteraction();
                return;
            }



        }


    }
}
