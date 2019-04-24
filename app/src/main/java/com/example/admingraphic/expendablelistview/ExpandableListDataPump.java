package com.example.admingraphic.expendablelistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListPlage = new HashMap<>();

        List<String> lundi1 = new ArrayList<>();
        lundi1.add("George");

        List<String> lundi2 = new ArrayList<>();
        lundi2.add("Thérèsa");
        lundi2.add("Mickael");

        expandableListPlage.put("Lundi 25 mars -- réunion + dîner", lundi1);
        expandableListPlage.put("Lundi 25 mars -- autobus", lundi2);


        List<String> mardi1 = new ArrayList<>();
        mardi1.add("George");

        List<String> mardi2 = new ArrayList<>();
        mardi2.add("Thérèsa");

        List<String> mardi3 = new ArrayList<>();
        mardi3.add("Mickael");
        mardi3.add("Robert");

        List<String> mardi4 = new ArrayList<>();
        mardi4.add("Jean");

        List<String> mardi5 = new ArrayList<>();
        mardi5.add("George");

        List<String> mardi6 = new ArrayList<>();
        mardi6.add("Robert");

        List<String> mardi7 = new ArrayList<>();
        mardi7.add("Jean");
        mardi7.add("Mickael");
        mardi7.add("Elizabeth");

        expandableListPlage.put("Mardi 26 mars -- C.sportif", mardi1);
        expandableListPlage.put("Mardi 26 mars -- C.sportif", mardi2);
        expandableListPlage.put("Mardi 26 mars -- Diner", mardi3);
        expandableListPlage.put("Mardi 26 mars -- Sortie profil CrossFit", mardi4);
        expandableListPlage.put("Mardi 26 mars -- Autobus", mardi5);
        expandableListPlage.put("Mardi 26 mars -- Salle de jeux", mardi6);
        expandableListPlage.put("Mardi 26 mars -- dffg", mardi7);


        List<String> mercredi1 = new ArrayList<>();
        mercredi1.add("George");

        List<String> mercredi2 = new ArrayList<>();
        mercredi2.add("Thérèsa");

        List<String> mercredi3 = new ArrayList<>();
        mercredi3.add("Mickael");
        mercredi3.add("Robert");

        List<String> mercredi4 = new ArrayList<>();
        mercredi4.add("Jean");

        expandableListPlage.put("Mercredi 27 mars -- fghfdgjhfk", mercredi1);
        expandableListPlage.put("Mercredi 27 mars -- dfghdfghdfgh", mercredi2);
        expandableListPlage.put("Mercredi 27 mars -- bdfbbgfbg", mercredi3);
        expandableListPlage.put("Mercredi 27 mars -- dbfdfgbdfgb", mercredi4);


        List<String> jeudi1 = new ArrayList<>();
        jeudi1.add("George");

        List<String> jeudi2 = new ArrayList<>();
        jeudi2.add("Thérèsa");

        List<String> jeudi3 = new ArrayList<>();
        jeudi3.add("Mickael");
        jeudi3.add("Robert");

        List<String> jeudi4 = new ArrayList<>();
        jeudi4.add("Jean");
        jeudi4.add("George");

        expandableListPlage.put("Jeudi 28 mars -- dfgbdfgb", jeudi1);
        expandableListPlage.put("Jeudi 28 mars -- sdfgsdfgsdfg", jeudi2);
        expandableListPlage.put("Jeudi 28 mars -- sdfgsdfhjvvbncvbn", jeudi3);
        expandableListPlage.put("Jeudi 28 mars -- SE dfsdfsdf", jeudi4);


        List<String> vendredi1 = new ArrayList<>();
        vendredi1.add("Elizabeth");

        List<String> vendredi2 = new ArrayList<>();
        vendredi2.add("Thérèsa");

        List<String> vendredi3 = new ArrayList<>();
        vendredi3.add("Mickael");
        vendredi3.add("Robert");

        List<String> vendredi4 = new ArrayList<>();
        vendredi4.add("Jean");

        List<String> vendredi5 = new ArrayList<>();
        vendredi5.add("George");
        vendredi5.add("Elizabeth");
        vendredi5.add("Mickael");

        expandableListPlage.put("Vendredi 29 mars -- Autobus", vendredi1);
        expandableListPlage.put("Vendredi 29 mars -- SE sdfdfcv", vendredi2);
        expandableListPlage.put("Vendredi 29 mars -- SE dfgdfg", vendredi3);
        expandableListPlage.put("Vendredi 29 mars -- SE dfdfg", vendredi4);
        expandableListPlage.put("Vendredi 29 mars -- Autobus", vendredi5);


        List<String> samedi1 = new ArrayList<>();
        samedi1.add("George");

        List<String> samedi2 = new ArrayList<>();
        samedi2.add("Thérèsa");

        List<String> samedi3 = new ArrayList<>();
        samedi3.add("Mickael");
        samedi3.add("Robert");

        List<String> samedi4 = new ArrayList<>();
        samedi4.add("Jean");

        List<String> samedi5 = new ArrayList<>();
        samedi5.add("George");

        List<String> samedi6 = new ArrayList<>();
        samedi6.add("Robert");

        expandableListPlage.put("Samedi 30 mars -- C.sportif (Futsal)", samedi1);
        expandableListPlage.put("Samedi 30 mars -- C.sportif (Futsal)", samedi2);
        expandableListPlage.put("Samedi 30 mars -- Coupe Durock", samedi3);
        expandableListPlage.put("Samedi 30 mars -- Coupe Durock", samedi4);
        expandableListPlage.put("Samedi 30 mars -- Coupe Durock", samedi5);
        expandableListPlage.put("Samedi 30 mars -- Coupe Durock", samedi6);

        return expandableListPlage;
    }
}
