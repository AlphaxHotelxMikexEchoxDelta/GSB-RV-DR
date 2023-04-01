package fr.gsb.consulter;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fr.gsb.R;
import fr.gsb.rv.technique.Session;

public class RechercheRvActivity extends AppCompatActivity {

    ArrayList<String> list_date = new ArrayList<>() ;
    Spinner date ;
    Button valider ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rechercherv);

        for( int i=0; i<Session.session.visiteur.getRapports().size(); i++ ){
            list_date.add("numero: "+Session.session.visiteur.getRapport(i).getNum()+" du "+Session.session.visiteur.getRapport(i).getDate());
        }


        System.out.println(Session.session.visiteur.getRapports().toString());

        date = ( Spinner ) findViewById(R.id.spinner1) ;
        ArrayAdapter< String > adpDate = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_date );
        adpDate.setDropDownViewResource( android.R.layout.simple_spinner_item );
        date.setAdapter( adpDate );

        valider = findViewById(R.id.afficher) ;

    }

    public void AfficherRapports(View vue){



    }

}