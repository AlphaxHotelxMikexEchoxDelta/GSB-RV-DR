package fr.gsb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import fr.gsb.consulter.RechercheRvActivity;
import fr.gsb.rv.entitee.LesPraticiens;
import fr.gsb.rv.entitee.Praticien;
import fr.gsb.rv.entitee.Rapport;
import fr.gsb.rv.technique.Session;
import fr.gsb.saisir.SaisieRvActivity;


public class MenuRvActivity extends AppCompatActivity {

    TextView userName ;
    Button deconnexion, consulter, saisir ;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        userName = findViewById(R.id.UserName) ;
        deconnexion = findViewById(R.id.Deconnexion) ;
        consulter = findViewById(R.id.consulter) ;
        saisir = findViewById(R.id.saisir) ;
        userName.setText(Session.session.visiteur.getPrenom()+" "+Session.session.visiteur.getNom());
    }

    public void seDeconnecter(View vue){
        Intent intention = new Intent( this, MainActivity.class ) ;
        Session.fermer();
        startActivity( intention );
    }

    public void consulterRapports(View vue){

        if( consulter.getText().equals("Consultation disponible !") ){
            Intent intention = new Intent( this, RechercheRvActivity.class ) ;
            startActivity( intention );
        }
        else {
            String url = String.format("http://172.20.50.176:5000/rapports/%s/%s", Session.session.visiteur.id, Session.session.visiteur.mdp);
            Response.Listener<JSONArray> ecouteurReponse = new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {

                        for (int i = 0; i < response.length(); i++) {
                            Session.session.visiteur.addRapport(new Rapport(
                                    response.getJSONObject(i).getString("date"),
                                    response.getJSONObject(i).getString("num"),
                                    response.getJSONObject(i).getString("bilan"),
                                    response.getJSONObject(i).getString("nom_Praticien"),
                                    response.getJSONObject(i).getString("prenom_Praticien"),
                                    response.getJSONObject(i).getString("cp_Praticien"),
                                    response.getJSONObject(i).getString("ville_Praticien")
                            ));
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

            consulter.setText("Consultation disponible !");
        }
    }

    @SuppressLint("SetTextI18n")
    public void saisirRapports(View vue){

        if( saisir.getText().equals("Rapport pres pour saisir !") ){
            Intent intention = new Intent( this, SaisieRvActivity.class ) ;
            startActivity( intention );
        }
        else {
            String url = "http://172.20.50.176:5000/praticiens" ;
            Response.Listener<JSONArray> ecouteurReponse = new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {

                        for (int i = 0; i < response.length(); i++) {
                            LesPraticiens.addListe(new Praticien(
                                    response.getJSONObject(i).getString("pra_num"),
                                    response.getJSONObject(i).getString("pra_nom")+" "+response.getJSONObject(i).getString("pra_prenom")
                            ));
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

            saisir.setText("Rapport pres pour saisir !");
            System.out.println(LesPraticiens.liste.toString());
        }
    }

}
