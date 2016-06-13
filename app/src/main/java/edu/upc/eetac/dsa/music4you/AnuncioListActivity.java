package edu.upc.eetac.dsa.music4you;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.upc.eetac.dsa.music4you.client.Music4youClient;
import edu.upc.eetac.dsa.music4you.client.Music4youClientException;
import edu.upc.eetac.dsa.music4you.client.entity.Anuncio;
import edu.upc.eetac.dsa.music4you.client.entity.AnuncioCollection;

public class AnuncioListActivity extends AppCompatActivity {
    private final static String TAG = AnuncioListActivity.class.toString();
    private GetAnunciosTask mGetStingsTask = null;
    private AnuncioCollection anuncios = new AnuncioCollection();
    private AnuncioListAdapter adapter = null;
    private TextView res;
    String URL_base = "http://eetacdsa2a.upc.es:8080/music4you/anuncio";
    String URL_base2 = "http://eetacdsa2a.upc.es:8080/music4you/";

    private LogOutTask mLogOutTask = null;


    class GetAnunciosTask extends AsyncTask<Void, Void, String> {
        private String uri;
        public GetAnunciosTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonAnuncioCollection = null;
            try{
                jsonAnuncioCollection = Music4youClient.getInstance().getAnuncios();
                Log.d("json es", jsonAnuncioCollection);

            }catch(Music4youClientException e){
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonAnuncioCollection;
        }


        @Override
        protected void onPostExecute(String jsonAnuncioCollection) {
            Log.d(TAG, jsonAnuncioCollection);
            AnuncioCollection AnuncioCollection = (new Gson()).fromJson(jsonAnuncioCollection, AnuncioCollection.class);
          /*  JSONObject jo = null;
            JSONArray resta = null;
            try {
                jo = new JSONObject(jsonAnuncioCollection);
                resta = jo.getJSONArray("links");
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
            Log.d(TAG, String.valueOf(AnuncioCollection));
            for(Anuncio anuncio : AnuncioCollection.getStings()){
                anuncios.getStings().add(anuncios.getStings().size(), anuncio);

            }
            if(anuncios.getStings().size() == 0){
                Log.d(TAG,"No hay anuncios");
            }
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_anuncio_list);
        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);

        // Execute AsyncTask
        mGetStingsTask = new GetAnunciosTask(null);
        mGetStingsTask.execute((Void) null);

        ListView list = (ListView)findViewById(R.id.list);
        adapter = new AnuncioListAdapter(this, anuncios);
        list.setAdapter(adapter);
        // res =  (TextView) findViewById(R.id.textViewMainText);

        // set list OnItemClick listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AnuncioListActivity.this, AnunciosDetailActivity.class);
                String ide = anuncios.getStings().get(position).getId().toString();       // Music4youClient.getLink(anuncios.getStings().get(position).getId(), "self").getUri().toString();
                String urlid = URL_base + "/" + ide;
               // String uri = Music4youClient.getLink(anuncios.getStings().get(position).getLinks(), "self").getUri().toString();
                intent.putExtra("uri", urlid);
                startActivity(intent);
            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AnuncioListActivity.this, WriteAnuncioActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = null;
                mLogOutTask = new LogOutTask(uri);
                mLogOutTask.execute((Void)null);
            }
        });
    }

    public class LogOutTask extends AsyncTask<Void, Void, Boolean> {
        String uri;
        public LogOutTask(String uri) {
            this.uri = uri;
        }

        @Override
        protected Boolean doInBackground(Void... params){
            Log.d(TAG, "DO IN BACKGROUND");
            Boolean ok = false;
            try {
                ok = Music4youClient.getInstance().LogOut(uri);
            } catch (Music4youClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return ok;
        }

        @Override
        protected void onPostExecute(Boolean ok) {
            if(ok)
            {
                Intent intent = new Intent(AnuncioListActivity.this, LoginActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getBaseContext(), "No se ha podido cerrar sesión",
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            mLogOutTask = null;
        }
    }

}
