package com.example.horaire.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.horaire.database.ChoixPlageHoraire;


import java.util.List;

@Dao
public interface ChoixPlageHoraireAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertChoixPlageHoraire(ChoixPlageHoraire choixPlageHoraire);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChoixPlageHoraires(List<ChoixPlageHoraire> choixPlageHoraires);
    @Query("SELECT * FROM choixPlageHoraire")
    List<ChoixPlageHoraire> getChoixPlageHoraires();
    @Query("SELECT * FROM choixPlageHoraire WHERE _id = :choixPlageHoraireId")
    ChoixPlageHoraire getChoixPlageHoraire(int choixPlageHoraireId);
    @Query("SELECT * FROM choixPlageHoraire WHERE userId = :userId AND plageHoraireId = :plageHoraireId")
    ChoixPlageHoraire getChoixPlageHoraire(long userId, long plageHoraireId);
    @Update()
    int updateChoixPlageHoraire(ChoixPlageHoraire choixPlageHoraire);
    @Delete
    int deleteChoixPlageHoraire(ChoixPlageHoraire choixPlageHoraire);
}

