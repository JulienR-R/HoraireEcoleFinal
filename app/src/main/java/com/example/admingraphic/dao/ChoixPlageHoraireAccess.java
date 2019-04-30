package com.example.admingraphic.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.example.admingraphic.database.ChoixPlageHoraire;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ChoixPlageHoraireAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertChoixPlageHoraire(ChoixPlageHoraire choixPlageHoraire);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChoixPlageHoraires(ArrayList<ChoixPlageHoraire> choixPlageHoraires);
    @Query("SELECT * FROM ChoixPlageHoraire")
    List<ChoixPlageHoraire> getChoixPlageHoraires();
    @Query("SELECT * FROM ChoixPlageHoraire WHERE _id = :choixPlageHoraireId")
    ChoixPlageHoraire getChoixPlageHoraire(int choixPlageHoraireId);
    @Update()
    int updateChoixPlageHoraire(ChoixPlageHoraire choixPlageHoraire);
    @Delete
    int deleteChoixPlageHoraire(ChoixPlageHoraire choixPlageHoraire);
}
