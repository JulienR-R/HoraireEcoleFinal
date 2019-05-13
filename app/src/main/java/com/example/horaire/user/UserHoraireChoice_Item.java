package com.example.horaire.user;

public class UserHoraireChoice_Item {

    private int mImageResource;
    private int mPlageHoraireId;
    private String mDescription;
    private String mDate;
    private String mHeureDebut;
    private String mHeureFin;


    public UserHoraireChoice_Item(int imageResource, String description, String date, String heureDebut, String heureFin){


        mDescription = description;
        mDate = date;
        mHeureFin = heureFin;
        mHeureDebut = heureDebut;
        mImageResource = imageResource;


    }

    public int getmPlageHoraireId() {
        return mPlageHoraireId;
    }

    public void setmPlageHoraireId(int mPlageHoraireId) {
        this.mPlageHoraireId = mPlageHoraireId;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmHeureDebut() {
        return mHeureDebut;
    }

    public String getmHeureFin() {
        return mHeureFin;
    }




}
