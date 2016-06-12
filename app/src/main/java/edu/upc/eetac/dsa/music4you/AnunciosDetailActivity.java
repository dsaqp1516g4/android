package edu.upc.eetac.dsa.music4you;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.music4you.client.Music4youClient;
import edu.upc.eetac.dsa.music4you.client.Music4youClientException;
import edu.upc.eetac.dsa.music4you.client.entity.Anuncio;

public class AnunciosDetailActivity extends AppCompatActivity {

    GetAnunciosTask mGetAnunciosTask = null;
    private final static String TAG = AnunciosDetailActivity.class.toString();
    TextView textViewName= null;
    TextView textViewDescription= null;
    TextView textViewLikes= null ;
    TextView textViewaddress= null ;
    TextView textViewPhone= null;
    Button btcomments = null;
    String uri = null;
    String subject = null;
    String precio = null;
    String description = null;


    class GetAnunciosTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetAnunciosTask(String uri) {
            this.uri = uri;

        }

            @Override
            protected String doInBackground (Void...params){
                String jsonSting = null;
                try {
                    jsonSting = Music4youClient.getInstance().getAnuncio(uri);
                } catch (Music4youClientException e) {
                    // TODO: Handle gracefully
                    Log.d(TAG, e.getMessage());
                }
                return jsonSting;
            }

            @Override
            protected void onPostExecute (String jsonAnuncio){
                Log.d(TAG, jsonAnuncio);
                Anuncio anuncio = (new Gson()).fromJson(jsonAnuncio, Anuncio.class);


                subject = anuncio.getSubject();
                description = anuncio.getDescription();
                precio = anuncio.getSubject();

                textViewName.setText(subject);
                textViewDescription.setText(description);
                textViewaddress.setText(precio);


            }


        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anuncios_detail);

        uri = (String) getIntent().getExtras().get("uri");


        textViewName= (TextView) findViewById(R.id.textViewName);
        textViewDescription= (TextView) findViewById(R.id.textViewDescription);
        textViewaddress= (TextView) findViewById(R.id.textViewAddress);


        // Execute AsyncTask
        mGetAnunciosTask = new GetAnunciosTask(uri);
        mGetAnunciosTask.execute((Void) null);

        // set list OnItemClick listener
        /*btcomments.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                Intent intent = new Intent(RestaurantDetailActivity.this, RestaurantCommentsActivity.class);
                intent.putExtra("uri", uri);
                startActivity(intent);
            }
        });*/

    }
    }

