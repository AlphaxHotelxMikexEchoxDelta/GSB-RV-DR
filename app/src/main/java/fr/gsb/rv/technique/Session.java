package fr.gsb.rv.technique;

import fr.gsb.rv.entitee.Visiteur;

public class Session {

    public static Session session = null ;
    public Visiteur visiteur ;

    public Session(Visiteur visiteur) {
        this.visiteur = visiteur;
    }

    public Visiteur getVisiteur() {
        return visiteur;
    }

    public void setVisiteur(Visiteur visiteur) {
        this.visiteur = visiteur;
    }

    public Session getSession() {
        return session ;
    }

    public static Session ouvrir( Visiteur visiteur ){
        if( session == null ){
            session = new Session( visiteur ) ;
        }
        return session ;
    }

    public static void fermer(){
        session = null ;
    }

    @Override
    public String toString() {
        return "Session{" +
                "visiteur=" + visiteur +
                '}';
    }
}
