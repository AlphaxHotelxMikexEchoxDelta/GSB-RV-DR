package fr.gsb.rv.entitee;

import fr.gsb.rv.technique.Session;

public class Praticien {

    public String num ;
    public String identitee ;

    public Praticien(String num, String identitee) {
        this.num = num;
        this.identitee = identitee ;
    }

    @Override
    public String toString() {
        return "Praticien{" +
                "num='" + num + '\'' +
                ", identitee='" + identitee + '\'' +
                '}';
    }

    public String getIdentitee() {
        return identitee;
    }

    public void setIdentitee(String identitee) {
        this.identitee = identitee;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
