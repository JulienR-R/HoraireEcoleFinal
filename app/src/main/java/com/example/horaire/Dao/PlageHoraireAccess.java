package com.example.horaire.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.horaire.database.PlageHoraire;


import java.util.List;

@Dao
public interface PlageHoraireAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPlageHoraire(PlageHoraire plageHoraire);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlageHoraires(List<PlageHoraire> plageHoraires);
    @Query("SELECT * FROM plageHoraire")
    List<PlageHoraire> getPlageHoraires();
    @Query("SELECT * FROM plageHoraire WHERE _id = :plageHoraireId")
    PlageHoraire getPlageHoraire(int plageHoraireId);
    @Update()
    int updatePlageHoraire(PlageHoraire plageHoraire);
    @Delete
    int deletePlageHoraire(PlageHoraire plageHoraire);
}
