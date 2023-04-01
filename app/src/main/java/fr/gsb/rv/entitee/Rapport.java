package fr.gsb.rv.entitee;

public class Rapport {

    public String date ;
    public String num ;
    public String bilan ;
    public String nom_Praticien ;
    public String prenom_Praticien ;
    public String cp_Praticien ;
    public String ville_Praticien ;

    public Rapport( String date,String num, String bilan, String nom_Praticien, String prenom_Praticien, String cp_Praticien, String ville_Praticien) {
        this.date = date ;
        this.num = num ;
        this.bilan = bilan ;
        this.nom_Praticien = nom_Praticien ;
        this.prenom_Praticien = prenom_Praticien ;
        this.cp_Praticien = cp_Praticien ;
        this.ville_Praticien = ville_Praticien ;
    }

    @Override
    public String toString() {
        return "Rapport{" +
                "date='" + date + '\'' +
                ", num='" + num + '\'' +
                ", bilan='" + bilan + '\'' +
                ", nom_Praticien='" + nom_Praticien + '\'' +
                ", prenom_Praticien='" + prenom_Praticien + '\'' +
                ", cp_Praticien='" + cp_Praticien + '\'' +
                ", ville_Praticien='" + ville_Praticien + '\'' +
                '}';
    }

    public String getVille_Praticien() {
        return ville_Praticien;
    }

    public void setVille_Praticien(String ville_Praticien) {
        this.ville_Praticien = ville_Praticien;
    }

    public String getCp_Praticien() {
        return cp_Praticien;
    }

    public void setCp_Praticien(String cp_Praticien) {
        this.cp_Praticien = cp_Praticien;
    }

    public String getPrenom_Praticien() {
        return prenom_Praticien;
    }

    public void setPrenom_Praticien(String prenom_Praticien) {
        this.prenom_Praticien = prenom_Praticien;
    }

    public String getNom_Praticien() {
        return nom_Praticien;
    }

    public void setNom_Praticien(String nom_Praticien) {
        this.nom_Praticien = nom_Praticien;
    }

    public String getBilan() {
        return bilan;
    }

    public void setBilan(String bilan) {
        this.bilan = bilan;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
