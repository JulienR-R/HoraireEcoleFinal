package com.example.horaire.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.horaire.dao.AttributionPlageHoraireAccess;
import com.example.horaire.dao.ChoixPlageHoraireAccess;
import com.example.horaire.dao.PlageHoraireAccess;
import com.example.horaire.dao.UserAccess;

@Database(entities = {User.class,PlageHoraire.class,ChoixPlageHoraire.class,AttributionPlageHoraire.class},
        version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class HorairesDataBase extends RoomDatabase {
    public abstract UserAccess userAccess();
    public abstract PlageHoraireAccess plageHoraireAccess();
    public abstract ChoixPlageHoraireAccess choixPlageHoraireAccess();
    public abstract AttributionPlageHoraireAccess attributionPlageHoraireAccess();
    private static volatile HorairesDataBase INSTANCE;

    public static HorairesDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (HorairesDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HorairesDataBase.class, "imagesDb.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}