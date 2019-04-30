package com.example.admingraphic.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "user", indices = {@Index(value = "userId", unique = true)})
public class User implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long _id;
    @ColumnInfo(name = "userId")
    String userId;
    @ColumnInfo(name = "pwd")
    String pwd;
    @ColumnInfo(name = "nom")
    String nom;
    @ColumnInfo(name = "prenom")
    String prenom;
    @ColumnInfo(name = "seniority")
    int seniority;
    @ColumnInfo(name = "isAdmin")
    boolean isAdmin;

    //public User(){}

    public User(String userId, String pwd) {
        setUserId(userId);
        setPwd(pwd);
    }

    protected User(Parcel in) {
        _id = in.readLong();
        userId = in.readString();
        pwd = in.readString();
        nom = in.readString();
        prenom = in.readString();
        seniority = in.readInt();
        isAdmin = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(_id);
        dest.writeString(userId);
        dest.writeString(pwd);
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeInt(seniority);
        dest.writeByte((byte) (isAdmin ? 1 : 0));
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", seniority=" + seniority +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
