package fr.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import fr.gsb.rv.entitee.Visiteur;
import fr.gsb.rv.technique.Session;


public class MainActivity extends AppCompatActivity {

    EditText login, mdp ;
    TextView t ;
    Button valider ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.Login) ;
        mdp = findViewById(R.id.Mdp);
        t = findViewById(R.id.error) ;
        valider = findViewById(R.id.Valider) ;

        /** A ENLEVER POUR E5 **/

        login.setText("a131");
        mdp.setText("azerty");

        /** A ENLEVER POUR E5 **/

    }

    @SuppressLint("SetTextI18n")
    public void seConnecter(View vue){

        if( valider.getText().equals("Profile") ){
            Intent intention = new Intent( this, MenuRvActivity.class ) ;
            startActivity( intention );
        }
        else {
            if (!login.getText().toString().isEmpty() && !mdp.getText().toString().isEmpty()) {

                String url = String.format("http://192.168.0.12:5000/visiteurs/%s/%s", login.getText(), mdp.getText());
                Response.Listener<JSONObject> ecouteurReponse = new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            t.setText("Connection reussie !\nvous pouvez acceder a votre profile");
                            valider.setText("Profile");
                            Session.ouvrir(new Visiteur(
                                    login.getText().toString(),
                                    response.getString("vis_nom"),
                                    response.getString("vis_prenom"),
                                    mdp.getText().toString()
                            ));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Response.ErrorListener ecouteurErreur = new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        t.setText("Erreur survenue lors de la connection !\nveuillez ressayer");
                    }
                };

                JsonObjectRequest requete = new JsonObjectRequest(Request.Method.GET, url, null, ecouteurReponse, ecouteurErreur);
                RequestQueue f = Volley.newRequestQueue(this);
                f.add(requete);
            } else {
                t.setText("Un champ de saisi est vide\nveuillez le remplir");
            }
        }
    }

    public void annuler(View vue){
        login.setText("");
        mdp.setText("");
        t.setText("");
        valider.setText("Valider");
    }

}