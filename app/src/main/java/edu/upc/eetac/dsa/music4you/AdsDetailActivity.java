package edu.upc.eetac.dsa.music4you;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.music4you.client.Cliente;
import edu.upc.eetac.dsa.music4you.client.Music4youClientException;
import edu.upc.eetac.dsa.music4you.client.entity.Anuncio;

public class AdsDetailActivity extends AppCompatActivity {


    GetAnuncioTask mGetAnuncioTask = null;

    private final static String TAG = AdsDetailActivity.class.toString();
    String uri = null;
    String name = null;
    String algo = null;
    String description = null;
    TextView textViewName= null;
    TextView textViewDescription= null;
    TextView textViewAlgo = null ;

    Button btcomments = null;






    class GetAnuncioTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetAnuncioTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonSting = null;
            try {
                jsonSting = Cliente.getInstance().GetAnuncio(uri);
            } catch (Music4youClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonSting;
        }

        @Override
        protected void onPostExecute(String jsonAnuncio) {
            Log.d(TAG, jsonAnuncio);
            Anuncio anuncio = (new Gson()).fromJson(jsonAnuncio, Anuncio.class);


            name= anuncio.getCreator ();
            description= anuncio.getDescription();
            algo= anuncio.getCreator ();


            textViewName.setText(name);
            textViewDescription.setText(description);
            textViewAlgo.setText(algo);


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads_detail);

        uri = (String) getIntent().getExtras().get("uri");


        textViewName= (TextView) findViewById(R.id.textViewName);
        textViewDescription= (TextView) findViewById(R.id.textViewDescription);
        textViewAlgo = (TextView) findViewById(R.id.algo);



        // Execute AsyncTask
        mGetAnuncioTask = new GetAnuncioTask(uri);
        mGetAnuncioTask.execute((Void) null);

        btcomments = (Button) findViewById(R.id.Comments);
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


