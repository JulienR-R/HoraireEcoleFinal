package com.example.horaire.database;

import android.content.Context;

import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;

public class PopulateDatabase {
    HorairesDataBase horairesDataBase;
    public PopulateDatabase(Context context){
        context.deleteDatabase("horairesDb.db");
        horairesDataBase = HorairesDataBase.getInstance(context);
        if(isEmptyDatabase()){
            addUsers();
            addPlageHoraire();
            addChoixPlageHoraire();
        }
    }

    public void addUsers(){
        List<User> userList = Arrays.asList(new User[]{
                new User("marco@gmail.com", "password"),
                new User("venom@gmail.com", "password1"),
                new User("party@gmail.com", "password2"),
                new User("loope@gmail.com", "password3"),
                new User("gino@gmail.com", "password4"),
                new User("math@gmail.com", "password5"),
                new User("louiz@gmail.com", "password6"),
                new User("phillipe@gmail.com", "password7"),
                new User("karl@gmail.com", "password8"),
                new User("papounet@gmail.com", "password9")
        });
        userList.get(0).setIsAdmin(true);
        horairesDataBase.userAccess().insertUsers(userList);
    }

    public void addPlageHoraire(){
        List<PlageHoraire> plageHoraires = Arrays.asList(new PlageHoraire[]{
                new PlageHoraire("Surveillance Examen", Date.valueOf("2019-4-30"), Time.valueOf("09:30:00"), Time.valueOf("17:30:00"), 2),
                new PlageHoraire("Examen Final", Date.valueOf("2019-5-10"), Time.valueOf("08:00:00"), Time.valueOf("16:30:00"), 1),
                new PlageHoraire("Activités Plein Air", Date.valueOf("2019-6-5"), Time.valueOf("11:05:00"), Time.valueOf("18:00:00"), 3),
                new PlageHoraire("Ski", Date.valueOf("2019-6-22"), Time.valueOf("07:30:00"), Time.valueOf("18:30:00"), 4),
                new PlageHoraire("Tournoi Soccer", Date.valueOf("2019-7-1"), Time.valueOf("10:00:00"), Time.valueOf("20:30:00"), 2)
        });
        horairesDataBase.plageHoraireAccess().insertPlageHoraires(plageHoraires);
    }

    public void addChoixPlageHoraire(){
        List<ChoixPlageHoraire> choixPlageHoraires = Arrays.asList(new ChoixPlageHoraire[]{
                new ChoixPlageHoraire(5, 5),
                new ChoixPlageHoraire(4, 1),
                new ChoixPlageHoraire(6, 3),
                new ChoixPlageHoraire(5, 1),
                new ChoixPlageHoraire(4, 5),
                new ChoixPlageHoraire(6, 5),
                new ChoixPlageHoraire(5, 3),
                new ChoixPlageHoraire(4, 2),
                new ChoixPlageHoraire(6, 1),
                new ChoixPlageHoraire(4, 3)
        });
        horairesDataBase.choixPlageHoraireAccess().insertChoixPlageHoraires(choixPlageHoraires);
    }

    public boolean isEmptyDatabase(){
        if(horairesDataBase.userAccess().getUsers().size() == 0 &&
        horairesDataBase.plageHoraireAccess().getPlageHoraires().size() == 0 &&
        horairesDataBase.choixPlageHoraireAccess().getChoixPlageHoraires().size() == 0){
            return true;
        }
        return false;
    }
}
