package fr.gsb.rv.entitee;

import java.util.ArrayList;

public class Visiteur {

    public String id ;
    public String nom ;
    public String prenom ;
    public String mdp ;

    public ArrayList<Rapport> lesRapports = new ArrayList<Rapport>() ;

    public Visiteur( String id, String nom, String prenom, String mdp ) {
        this.id = id ;
        this.nom = nom ;
        this.prenom = prenom ;
        this.mdp = mdp ;
    }

    public ArrayList<Rapport> getRapports() {
        return lesRapports ;
    }

    public Rapport getRapport(int i) {
        for( Rapport r : lesRapports ){
            if( lesRapports.get(i) == r ){
                return r ;
            }
        }
        return null ;
    }

    public void delRapports(Rapport rapport) { this.lesRapports.remove(rapport);}

    public void addRapport(Rapport rapport) {
        this.lesRapports.add(rapport);
    }

    @Override
    public String toString() {
        return "Visiteur{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}

