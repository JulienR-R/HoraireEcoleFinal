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
    @ColumnInfo(name = "placesDisponibles")
    int placesDisponibles;
    @ColumnInfo(name = "actif")
    boolean actif;


    public PlageHoraire(String description, Date date, Time heureDebut, Time heureFin, int effectif) {
        setDescription(description);
        setDate(date);
        setHeureDebut(heureDebut);
        setHeureFin(heureFin);
        setEffectif(effectif);
        setPlacesDisponibles(effectif);
        setActif(true);
    }

    protected PlageHoraire(Parcel in) {
        _id = in.readLong();
        description = in.readString();
        effectif = in.readInt();
        placesDisponibles = in.readInt();
        actif = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeString(description);
        dest.writeInt(effectif);
        dest.writeInt(placesDisponibles);
        dest.writeByte((byte) (actif ? 1 : 0));
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

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public void setPlacesDisponibles(int placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    @Override
    public String toString() {
        return  "description='" + description + "\n" +
                "date=" + date + "\n" +
                "debut=" + heureDebut + "\t" +
                "fin=" + heureFin + "\n" +
                 "Effectif=" + effectif+ "\n" +
                "Places disponible=" + placesDisponibles;
    }
}
