package fr.gsb.rv.entitee;

import java.util.ArrayList;

public class LesPraticiens {

    public static ArrayList<Praticien> liste = new ArrayList<>() ;

    public static ArrayList<Praticien> getListe() {
        return liste;
    }

    public static void addListe(Praticien praticien) {
        liste.add(praticien);
    }

    @Override
    public String toString() {
        return "LesPraticiens{+"+
                liste.toString()
                +"}";
    }
}
