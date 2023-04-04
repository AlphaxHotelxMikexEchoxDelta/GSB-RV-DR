package fr.gsb.consulter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import fr.gsb.R;
import fr.gsb.rv.technique.Session;

public class RechercheRvActivity extends AppCompatActivity{

    ArrayList<String> list_date = new ArrayList<>() ;
    ArrayList<String> lesOffres = new ArrayList<>() ;
    Spinner date ;
    Button valider, listEchan ;
    TextView rapports, offre ;
    String numRapport ;

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
        offre = findViewById(R.id.Offre) ;
        listEchan = findViewById(R.id.buttonEchan) ;

        offre.setVisibility(View.INVISIBLE);
        offre.setVisibility(View.GONE);
        listEchan.setVisibility(View.INVISIBLE);
        listEchan.setVisibility(View.GONE);
    }

    @SuppressLint("SetTextI18n")
    public void AfficherRapports(View vue){

        for( int i=0; i<Session.session.visiteur.getRapports().size(); i++ ){
            if( date.getSelectedItem().toString().equals( "numero: "+Session.session.visiteur.getRapport(i).getNum()+" du "+Session.session.visiteur.getRapport(i).getDate()) ) {
                rapports.setText(
                        "Rapports numero: " + Session.session.visiteur.getRapport(i).getNum() + "  date: " + Session.session.visiteur.getRapport(i).getDate() +
                                "\n-> Praticien concerner: " + Session.session.visiteur.getRapport(i).getNom_Praticien() + " " + Session.session.visiteur.getRapport(i).getPrenom_Praticien() +
                                "\n-> bilan[ " + Session.session.visiteur.getRapport(i).getBilan() + " ]"
                );
                numRapport = Session.session.visiteur.getRapport(i).getNum();
                listEchan.setVisibility(View.VISIBLE);
                lesOffres.clear();
                offre.setText("");
            }

        }
    }

    @SuppressLint("SetTextI18n")
    public void listeEchantillons(View vue ){

        String url = String.format("http://172.20.50.176:5000/rapports/echantillons/%s/%s/%s", Session.session.visiteur.id, Session.session.visiteur.mdp, numRapport);
        Response.Listener<JSONArray> ecouteurReponse = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    if(response.length() != 0) {
                        lesOffres.clear();

                        for (int i = 0; i < response.length(); i++) {
                            lesOffres.add(
                                    "-> " + response.getJSONObject(i).getString("off_quantite") +
                                            " de " + response.getJSONObject(i).getString("med_nomcommercial") + "\n"
                            );
                        }
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

        };
        Response.ErrorListener ecouteurErreur = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        };

        JsonArrayRequest requete = new JsonArrayRequest(Request.Method.GET, url, null, ecouteurReponse, ecouteurErreur);
        RequestQueue f = Volley.newRequestQueue(this);
        f.add(requete);

        offre.setText("");
        offre.setVisibility(View.VISIBLE);
        System.out.println(lesOffres);
        if( !lesOffres.isEmpty() ) {
            for (String s : lesOffres) {
                if (offre.getText().toString().equals("")) {
                    offre.setText(s);
                } else {
                    offre.setText(offre.getText().toString() + s);
                }
            }
        }
        else {
            offre.setText("Aucun medicaments n'a etait offer !");
        }
    }

}