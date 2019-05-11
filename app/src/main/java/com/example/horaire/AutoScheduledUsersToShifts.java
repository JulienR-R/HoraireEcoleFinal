package com.example.horaire;

import android.content.Context;


import com.example.horaire.database.AttributionPlageHoraire;
import com.example.horaire.database.ChoixPlageHoraire;
import com.example.horaire.database.HorairesDataBase;
import com.example.horaire.database.PlageHoraire;
import com.example.horaire.database.User;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AutoScheduledUsersToShifts {
    /*
    cette classe roulera une fois semaine de façon automatique
    afin d'attribuer selon les priorités pre établies, des quarts
    de travail aux usagers qui auront fait des choix
    Elle aura un thread uniquequi executera l'algorithme
     */

    public AutoScheduledUsersToShifts(Context context){
        HorairesDataBase horairesDataBase = HorairesDataBase.getInstance(context);
        //liste tous les users
        List<User> users = horairesDataBase.userAccess().getUsers();
        //liste tous les users ayant fait au moins 1 choix
        List<User> usersInChoixPlageHoraires = new ArrayList<User>();
        //liste toutes les plages horaires
        List<PlageHoraire> plageHoraires = horairesDataBase.plageHoraireAccess().getPlageHoraires();
        //liste tous les choix horaires
        List<ChoixPlageHoraire> choixPlageHoraires = horairesDataBase.choixPlageHoraireAccess().getChoixPlageHoraires();
        //liste les choix horaires par user
        List<PlageHoraire> userChoixInChoixPlageHoraires;


        /*
        priorité 1 - check le user seniority
        priorité 2 - le shift le plus long a priorité
        priorité 3 - si 35h on passe au user suivant
        étape 1- lister les users ayant fait un choix et les classer par ordre décroissant de priorité
        étape 2- lister les choix de plages horaires par ordre décroissant de durée
         */

        //étape 1.a- lister les users ayant fait un choix et les classer par ordre décroissant de priorité
        for (ChoixPlageHoraire choix:choixPlageHoraires) {
            User userId = horairesDataBase.userAccess().getUser((int) choix.getUserId());
            if(!usersInChoixPlageHoraires.contains(userId)){
                usersInChoixPlageHoraires.add(userId);
            }
        }

        //1.b- tri des users par ordre décroissant de priorite
        User max = usersInChoixPlageHoraires.get(0);
        for (User user1:usersInChoixPlageHoraires) {
            for (User user2:usersInChoixPlageHoraires) {
                if(usersInChoixPlageHoraires.indexOf(user2) > 0){
                    if(user2.getSeniority() > user1.getSeniority()){
                        User temp = user1;
                        int pos1 = usersInChoixPlageHoraires.indexOf(user1);
                        int pos2 = usersInChoixPlageHoraires.indexOf(user2);
                        usersInChoixPlageHoraires.set(pos1,user2);
                        usersInChoixPlageHoraires.set(pos2,temp);
                    }
                }
            }
        }

        //étape 2.a- lister les choix de plages horaires
        for (User user:usersInChoixPlageHoraires) {
            userChoixInChoixPlageHoraires = new ArrayList<PlageHoraire>();
            for (ChoixPlageHoraire choixPlageHoraire:choixPlageHoraires) {
                if(user.get_id() == choixPlageHoraire.getUserId()){
                    PlageHoraire plageHoraire =
                            horairesDataBase.plageHoraireAccess().getPlageHoraire((int) choixPlageHoraire.getPlageHoraireId());
                    userChoixInChoixPlageHoraires.add(plageHoraire);
                }
            }


            //étape 2.a- trier les plages horaires par ordre décroissant de durée
            PlageHoraire top = userChoixInChoixPlageHoraires.get(0);
            for (PlageHoraire plageHoraire1:userChoixInChoixPlageHoraires) {
                for (PlageHoraire plageHoraire2:userChoixInChoixPlageHoraires) {
                    if(userChoixInChoixPlageHoraires.indexOf(plageHoraire2) > 0){
                        if((plageHoraire2.getHeureFin().getTime() - plageHoraire2.getHeureDebut().getTime()) >
                                (plageHoraire1.getHeureFin().getTime() - plageHoraire1.getHeureDebut().getTime())){
                            PlageHoraire temp = plageHoraire1;
                            int pos1 = userChoixInChoixPlageHoraires.indexOf(plageHoraire1);
                            int pos2 = userChoixInChoixPlageHoraires.indexOf(plageHoraire2);
                            userChoixInChoixPlageHoraires.set(pos1,plageHoraire2);
                            userChoixInChoixPlageHoraires.set(pos2,temp);
                        }
                    }
                }
            }


            //étape finale : maintenant que les 2 1ers ordres de priorités sont respectés(seniority et duree),
            // assignons des plages horaires selon la 3e priorité
            Long heureMax = Time.valueOf("35:00:00").getTime();
            Long currTime = Long.valueOf("0");
            for (PlageHoraire plageHoraire:userChoixInChoixPlageHoraires) {
                Long targetTime = plageHoraire.getHeureFin().getTime() - plageHoraire.getHeureDebut().getTime();
                ChoixPlageHoraire choixPlageHoraire = horairesDataBase.choixPlageHoraireAccess().
                        getChoixPlageHoraire(user. get_id(),plageHoraire.get_id());
                AttributionPlageHoraire attributionPlageHoraire;
                if((currTime + targetTime) <= heureMax){
                    attributionPlageHoraire = new AttributionPlageHoraire(choixPlageHoraire.get_id(),true);
                }
                else{
                    attributionPlageHoraire = new AttributionPlageHoraire(choixPlageHoraire.get_id(),false);
                }
                horairesDataBase.attributionPlageHoraireAccess().insertAttributionPlageHoraire(attributionPlageHoraire);
            }
        }
    }
}
