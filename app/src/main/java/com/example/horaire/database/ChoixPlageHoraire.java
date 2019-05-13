package com.example.horaire.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "choixPlageHoraire"/*, foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "_id",
        childColumns = "userId", onDelete = CASCADE),
        @ForeignKey(entity = PlageHoraire.class, parentColumns = "_id",
                childColumns = "plageHoraireId", onDelete = CASCADE)}*/)
public class ChoixPlageHoraire implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long _id;
    @ColumnInfo(name = "userId")
    long userId;
    @ColumnInfo(name = "plageHoraireId")
    long plageHoraireId;
    @ColumnInfo(name = "priority")
    int priority;
    @ColumnInfo(name = "actif")
    boolean actif;


    public ChoixPlageHoraire(long userId, long plageHoraireId) {
        setUserId(userId);
        setPlageHoraireId(plageHoraireId);
        setActif(true);
    }


    protected ChoixPlageHoraire(Parcel in) {
        _id = in.readLong();
        userId = in.readLong();
        plageHoraireId = in.readLong();
        priority = in.readInt();
        actif = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeLong(userId);
        dest.writeLong(plageHoraireId);
        dest.writeInt(priority);
        dest.writeByte((byte) (actif ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ChoixPlageHoraire> CREATOR = new Creator<ChoixPlageHoraire>() {
        @Override
        public ChoixPlageHoraire createFromParcel(Parcel in) {
            return new ChoixPlageHoraire(in);
        }

        @Override
        public ChoixPlageHoraire[] newArray(int size) {
            return new ChoixPlageHoraire[size];
        }
    };

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPlageHoraireId() {
        return plageHoraireId;
    }

    public void setPlageHoraireId(long plageHoraireId) {
        this.plageHoraireId = plageHoraireId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean getActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }
}
