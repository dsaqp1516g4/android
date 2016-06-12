package edu.upc.eetac.dsa.music4you;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.upc.eetac.dsa.music4you.client.entity.Anuncio;
import edu.upc.eetac.dsa.music4you.client.entity.AnuncioCollection;
import edu.upc.eetac.dsa.music4you.client.Music4youClient;
import edu.upc.eetac.dsa.music4you.client.Music4youClientException;

public class AnunciosListActivity extends AppCompatActivity {
    private final static String TAG = AnunciosListActivity.class.toString();
    private GetAnunciosTask mGetStingsTask = null;
    private AnuncioCollection anuncios = new AnuncioCollection();
    private AnuncioCollectionAdapter adapter = null;
    private TextView res;

    class GetAnunciosTask extends AsyncTask<Void, Void, String> {
        private String uri;
        public GetAnunciosTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonAnuncioCollection = null;
            try{
                jsonAnuncioCollection = Music4youClient.getInstance().getAnuncios(uri);
                Log.d("json es", jsonAnuncioCollection);

            }catch(Music4youClientException e){
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonAnuncioCollection;
        }


        @Override
        protected void onPostExecute(String jsonAnuncioCollection) {
            JSONObject jo = null;

            JSONArray resta = null;
            try {
                jo = new JSONObject(jsonAnuncioCollection);
                resta = jo.getJSONArray("stings");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d(TAG, jsonAnuncioCollection);
            AnuncioCollection AnuncioCollection = (new Gson()).fromJson(jsonAnuncioCollection, AnuncioCollection.class);

            for(Anuncio a : AnuncioCollection.getAnuncios()){
                anuncios.getAnuncios().add(anuncios.getAnuncios().size(), a);

            }
            if(anuncios.getAnuncios().size() == 0){
                Log.d(TAG,"No hay anuncios");
            }
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ads_list);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        // Execute AsyncTask
        mGetStingsTask = new GetAnunciosTask(null);
        mGetStingsTask.execute((Void) null);


        ListView list = (ListView)findViewById(R.id.list);
        adapter = new AnuncioCollectionAdapter(this, anuncios);
        list.setAdapter(adapter);
       // res =  (TextView) findViewById(R.id.textViewMainText);

        // set list OnItemClick listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AnunciosListActivity.this, AnunciosDetailActivity.class);
                String uri = Music4youClient.getLink(anuncios.getAnuncios().get(position).getLinks(), "self").getUri().toString();
                intent.putExtra("uri", uri);
                startActivity(intent);
            }
        });








       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


}
