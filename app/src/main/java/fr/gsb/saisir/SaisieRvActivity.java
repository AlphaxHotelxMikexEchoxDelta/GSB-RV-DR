package fr.gsb.saisir;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fr.gsb.R;
import fr.gsb.rv.entitee.LesPraticiens;
import fr.gsb.rv.technique.Session;

public class SaisieRvActivity extends AppCompatActivity {

    String[] list_bilan = {"RAS", "NOK"} ;
    ArrayList<String> list_prat = new ArrayList<>() ;
    Spinner prat ;
    Spinner bilan ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saisierv);
        System.out.println(LesPraticiens.getListe());

/** ##################### Praticiens ##################### */
        for(int i = 0; i< LesPraticiens.liste.size(); i++ ){
            list_prat.add("numero: "+LesPraticiens.liste.get(i).getNum()+" du "+LesPraticiens.liste.get(i).getIdentitee());
        }
        prat = (Spinner) findViewById(R.id.praticiens) ;
        ArrayAdapter< String > adpPrat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_prat );
        adpPrat.setDropDownViewResource( android.R.layout.simple_spinner_item );
        prat.setAdapter( adpPrat );
/** ##################### Praticiens ##################### */
/** ##################### Bilan ##################### */
        bilan = (Spinner) findViewById(R.id.bilan) ;
        ArrayAdapter< String > adpBilan = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list_bilan);
        adpBilan.setDropDownViewResource( android.R.layout.simple_spinner_item );
        bilan.setAdapter( adpBilan );
/** ##################### Bilan ##################### */

    }
}
