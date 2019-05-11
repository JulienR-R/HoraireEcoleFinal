package com.example.horaire.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;
import java.sql.Time;


@Entity(tableName = "plageHoraire")
public class PlageHoraire implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long _id;
    @ColumnInfo(name = "description")
    String description;
    @ColumnInfo(name = "date")
    Date date;
    @ColumnInfo(name = "heureDebut")
    Time heureDebut;
    @ColumnInfo(name = "heureFin")
    Time heureFin;
    @ColumnInfo(name = "effectif")
    int effectif;

    //public PlageHoraire(){}

    public PlageHoraire(String description,Date date,Time heureDebut,Time heureFin,int effectif) {
        setDescription(description);
        setDate(date);
        setHeureDebut(heureDebut);
        setHeureFin(heureFin);
        setEffectif(effectif);
    }

    protected PlageHoraire(Parcel in) {
        _id = in.readLong();
        description = in.readString();
        effectif = in.readInt();
        date = new Date(in.readLong());
        heureDebut = new Time(in.readLong());
        heureFin = new Time(in.readLong());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeString(description);
        dest.writeInt(effectif);
        dest.writeLong(date.getTime());
        dest.writeLong(heureDebut.getTime());
        dest.writeLong(heureFin.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlageHoraire> CREATOR = new Creator<PlageHoraire>() {
        @Override
        public PlageHoraire createFromParcel(Parcel in) {
            return new PlageHoraire(in);
        }

        @Override
        public PlageHoraire[] newArray(int size) {
            return new PlageHoraire[size];
        }
    };

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }
}