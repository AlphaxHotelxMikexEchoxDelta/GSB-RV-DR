package fr.gsb.consulter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fr.gsb.R;
import fr.gsb.rv.technique.Session;

public class RechercheRvActivity extends AppCompatActivity {

    ArrayList<String> list_date = new ArrayList<>() ;
    Spinner date ;
    Button valider ;
    TextView rapports ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rechercherv);

        for( int i=0; i<Session.session.visiteur.getRapports().size(); i++ ){
            list_date.add("numero: "+Session.session.visiteur.getRapport(i).getNum()+" du "+Session.session.visiteur.getRapport(i).getDate());
        }

        date = ( Spinner ) findViewById(R.id.spinner1) ;
        ArrayAdapter< String > adpDate = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_date );
        adpDate.setDropDownViewResource( android.R.layout.simple_spinner_item );
        date.setAdapter( adpDate );

        valider = findViewById(R.id.afficher) ;
        rapports = findViewById(R.id.Rapport) ;

    }

    @SuppressLint("SetTextI18n")
    public void AfficherRapports(View vue){

        for( int i=0; i<Session.session.visiteur.getRapports().size(); i++ ){
            if( date.getSelectedItem().toString().equals( "numero: "+Session.session.visiteur.getRapport(i).getNum()+" du "+Session.session.visiteur.getRapport(i).getDate() ) ){
                rapports.setText(
                        "Rapports numero: "+Session.session.visiteur.getRapport(i).getNum()+"  date: "+Session.session.visiteur.getRapport(i).getDate()+
                        "\n-> Praticien concerner: "+Session.session.visiteur.getRapport(i).getNom_Praticien()+" "+Session.session.visiteur.getRapport(i).getPrenom_Praticien()+
                        "\n-> bilan[ "+Session.session.visiteur.getRapport(i).getBilan()+" ]"
                );
            }
        }
    }

    public void listeEchantillons( View vue ){
    }

}