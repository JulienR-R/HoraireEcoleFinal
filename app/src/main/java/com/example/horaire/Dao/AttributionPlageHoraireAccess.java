package com.example.horaire.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.horaire.database.AttributionPlageHoraire;


import java.util.List;


@Dao
public interface AttributionPlageHoraireAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAttributionPlageHoraire(AttributionPlageHoraire attributionPlageHoraire);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAttributionPlageHoraires(List<AttributionPlageHoraire> attributionPlageHoraires);
    @Query("SELECT * FROM attributionPlageHoraire")
    List<AttributionPlageHoraire> getAttributionPlageHoraires();
    @Query("SELECT * FROM attributionPlageHoraire WHERE _id = :attributionPlageHoraire")
    AttributionPlageHoraire getAttributionPlageHoraire(int attributionPlageHoraire);
    @Update()
    int updateAttributionPlageHoraire(AttributionPlageHoraire attributionPlageHoraire);
    @Delete
    int deleteAttributionPlageHoraire(AttributionPlageHoraire attributionPlageHoraire);
}
