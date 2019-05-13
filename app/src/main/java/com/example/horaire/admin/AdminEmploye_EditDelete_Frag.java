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
import com.example.horaire.database.User;

import java.lang.ref.WeakReference;


public class AdminEmploye_EditDelete_Frag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String USAGER = "user";


    // TODO: Rename and change types of parameters
    private User user;

    private EditText editNom;
    private EditText editPrenom;
    private EditText editEmail;
    private EditText editSenority;

    private ProgressBar progressBar;
    private AppCompatActivity activity;
    private Button btnSave;



    private OnFragmentInteractionListener mListener;

    public AdminEmploye_EditDelete_Frag() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AdminEmploye_EditDelete_Frag newInstance(User mUser) {
        AdminEmploye_EditDelete_Frag fragment = new AdminEmploye_EditDelete_Frag();
        Bundle args = new Bundle();
        args.putParcelable(USAGER, mUser);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(USAGER);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.admin_emp_edit_frag, container, false);


        activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle("Modifier");


        editEmail = view.findViewById(R.id.edit_email);
        editNom  = view.findViewById(R.id.edit_nom);
        editPrenom = view.findViewById(R.id.edit_prenom);
        progressBar = view.findViewById(R.id.progress_bar);
        editSenority =   view.findViewById(R.id.edit_senority);
        btnSave = view.findViewById(R.id.btnSave);

        editEmail.setText(user.getUserId() );
        editSenority.setText(String.valueOf(user.getSeniority()) );
        editPrenom.setText(user.getPrenom() );
        editNom.setText(user.getNom());
        editNom.requestFocus();
        btnSave.setOnClickListener(SaveUser);

        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void closeFragment( ) {

        if (mListener != null) {
            mListener.onFragmentInteraction();

        }
    }



    View.OnClickListener SaveUser = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                user.setPrenom(editPrenom.getText().toString());
                user.setNom(editNom.getText().toString());
                user.setSeniority(Integer.parseInt(editSenority.getText().toString()));
                user.setUserId(editEmail.getText().toString());
                UpdateUserAsync updateUserAsync = new UpdateUserAsync(AdminEmploye_EditDelete_Frag.this);

                updateUserAsync.execute();



        }
    };





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment_CreationHoraire_InteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    private class UpdateUserAsync extends AsyncTask<Void,Void,Void> {


        private WeakReference<AdminEmploye_EditDelete_Frag> activityWeakReference;


        UpdateUserAsync(AdminEmploye_EditDelete_Frag activity) {

             activityWeakReference = new WeakReference<AdminEmploye_EditDelete_Frag>(activity);
        }
        @Override
        protected Void doInBackground(Void... voids) {

            HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(activity);



            horairesDataBase.userAccess().updateUser(user);




            return null;
        }

        @Override
        protected void onPostExecute(Void voids){
            super.onPostExecute(voids);


            AdminEmploye_EditDelete_Frag activity = activityWeakReference.get();
            if (activity == null || activity.isResumed()) {
                mListener.onFragmentInteraction();
                return;
            }



        }


    }




    }


