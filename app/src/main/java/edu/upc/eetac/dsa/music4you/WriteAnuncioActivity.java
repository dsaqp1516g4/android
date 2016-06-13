package edu.upc.eetac.dsa.music4you;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import edu.upc.eetac.dsa.music4you.client.Music4youClient;
import edu.upc.eetac.dsa.music4you.client.Music4youClientException;
import edu.upc.eetac.dsa.music4you.client.entity.Anuncio;

public class WriteAnuncioActivity extends AppCompatActivity {

    private final static String TAG = Music4youClient.class.toString();
    private CreateEventTask mAuthTask = null;
    private EditText mSubject;

    private EditText mDescription;
    private EditText mType;
    private EditText mPrecio;

    private Anuncio anuncio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_anuncio);

        mSubject = (EditText) findViewById(R.id.Subject);
        mDescription = (EditText) findViewById(R.id.textViewDescription);
        mType = (EditText) findViewById(R.id.precio);
        mPrecio = (EditText) findViewById(R.id.type);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        FloatingActionButton fabdel = (FloatingActionButton) findViewById(R.id.fabdel);
        fabdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WriteAnuncioActivity.this, AnuncioListActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fabok = (FloatingActionButton) findViewById(R.id.fabok);
        fabok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptCreate();
            }
        });

    }









    private void attemptCreate(){
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mSubject.setError(null);

        mDescription.setError(null);
        mType.setError(null);
        mPrecio.setError(null);

        //GetText
        String subject = mSubject.getText().toString();
        Log.d(TAG, "Subject: " + subject);

        String desciption = mDescription.getText().toString();
        Log.d(TAG, "descripcion: "+desciption);
        String type = mType.getText().toString();
        Log.d(TAG,"type: "+ type);
        String precio = mPrecio.getText().toString();
        Log.d(TAG,"precio: "+ precio);

        boolean cancel = false;
        View focusView = null;


        //Comprobar que no haya campos vacios
        if(TextUtils.isEmpty(subject)){
            mSubject.setError(getString(R.string.error_field_required));
            focusView = mSubject;
            cancel = true;
        }
        if(TextUtils.isEmpty(desciption)){
            mDescription.setError(getString(R.string.error_field_required));
            focusView = mDescription;
            cancel = true;
        }
        if(TextUtils.isEmpty(type)){
            mType.setError(getString(R.string.error_field_required));
            focusView = mType;
            cancel = true;
        }
        if(TextUtils.isEmpty(precio)){
            mPrecio.setError(getString(R.string.error_field_required));
            focusView = mPrecio;
            cancel = true;
        }



        if (cancel) {
            // There was an error; don't attempt register and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            Anuncio anuncio = new Anuncio();
            anuncio.setSubject(subject);
            anuncio.setDescription(desciption);
            anuncio.setPrecio(Double.valueOf(precio));
            anuncio.setType(Integer.valueOf( type));
            mAuthTask = new CreateEventTask(anuncio);
            mAuthTask.execute((Void) null);
        }

    }
    public class CreateEventTask extends AsyncTask<Void, Void, Boolean> {
        String subject;
        String description;
        String precio;
        String type;

        Anuncio event = new Anuncio();

        CreateEventTask(Anuncio anuncio) {
            this.event = anuncio;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Boolean ok=false;
            Music4youClient client = Music4youClient.getInstance();
            try {
                ok = client.newEvent(event);
            } catch (Music4youClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return ok;

        }

        @Override
        protected void onPostExecute(final Boolean bool) {
            if(bool){
                startActivity(new Intent(WriteAnuncioActivity.this, AnuncioListActivity.class));
            }
            else
                Toast.makeText(getBaseContext(), "No se ha podido crear el evento",
                        Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onCancelled() {
            Log.d(TAG, "cancelled y peto");
            mAuthTask = null;

        }
    }
}
