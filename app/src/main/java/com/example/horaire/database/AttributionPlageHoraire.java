package com.example.horaire.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "attributionPlageHoraire"/*, foreignKeys = @ForeignKey(entity = ChoixPlageHoraire.class, parentColumns = "_id",
        childColumns = "idChoixPlageHoraire", onDelete = CASCADE)*/)
public class AttributionPlageHoraire implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long _id;
    @ColumnInfo(name = "idChoixPlageHoraire")
    public long idChoixPlageHoraire;
    @ColumnInfo(name = "approved")
    boolean approved;
    public String result,titre = "";


    public AttributionPlageHoraire(long idChoixPlageHoraire, boolean approved) {
        setIdChoixPlageHoraire(idChoixPlageHoraire);
        setApproved(approved);
    }



    protected AttributionPlageHoraire(Parcel in) {
        _id = in.readLong();
        idChoixPlageHoraire = in.readLong();
        approved = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeLong(idChoixPlageHoraire);
        dest.writeByte((byte) (approved ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AttributionPlageHoraire> CREATOR = new Creator<AttributionPlageHoraire>() {
        @Override
        public AttributionPlageHoraire createFromParcel(Parcel in) {
            return new AttributionPlageHoraire(in);
        }

        @Override
        public AttributionPlageHoraire[] newArray(int size) {
            return new AttributionPlageHoraire[size];
        }
    };

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getIdChoixPlageHoraire() {
        return idChoixPlageHoraire;
    }

    public void setIdChoixPlageHoraire(long plageHoraireId) {
        this.idChoixPlageHoraire = plageHoraireId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return titre;
    }

    public String details() {
        return result;
    }

    public void infos(final Context ct){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HorairesDataBase db = HorairesDataBase.getInstance(ct);
                ChoixPlageHoraire choixPlageHoraire = db.choixPlageHoraireAccess()
                        .getChoixPlageHoraire((int)getIdChoixPlageHoraire());
                PlageHoraire plageHoraire = db.plageHoraireAccess()
                        .getPlageHoraire((int) choixPlageHoraire.getPlageHoraireId());
                result = plageHoraire.toString() + (approved? "approuvé" : "refused");
                titre = plageHoraire.getDescription() + (approved? "approuvé" : "refused");
            }
        }).start();
    }

}
