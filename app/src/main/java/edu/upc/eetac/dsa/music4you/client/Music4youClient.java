package edu.upc.eetac.dsa.music4you.client;

import android.util.Log;

import com.google.gson.Gson;

import org.glassfish.jersey.client.ClientConfig;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.upc.eetac.dsa.music4you.client.entity.AuthToken;
import edu.upc.eetac.dsa.music4you.client.entity.Link;
import edu.upc.eetac.dsa.music4you.client.entity.Root;

/**
 * Created by root on 12/06/16.
 */
public class Music4youClient {
    private final static String BASE_URI = "http://80.103.156.84:8080/music4you";
    private static Music4youClient instance;
    private Root root;
    private ClientConfig clientConfig = null;
    private Client client = null;
    private AuthToken authToken = null;
    private final static String TAG = Music4youClient.class.toString();

    private Music4youClient() {
        clientConfig = new ClientConfig();
        client = ClientBuilder.newClient(clientConfig);
        loadRoot();
    }

    public String GetAnuncio (String uri) throws Music4youClientException {
        WebTarget target = client.target(uri);
        Response response = target.request().get();
        if (response.getStatus() == Response.Status.OK.getStatusCode())
            return response.readEntity(String.class);
        else
            throw new Music4youClientException(response.readEntity(String.class));
    }

    public static Music4youClient getInstance() {
        if (instance == null)
            instance = new Music4youClient();
        return instance;
    }

    private void loadRoot() {
        WebTarget target = client.target(BASE_URI);
        Response response = target.request().get();

        String json = response.readEntity(String.class);
        root = (new Gson()).fromJson(json, Root.class);
    }

    public String getAnuncios() throws Music4youClientException {
        //if(uri==null){
        //    uri = BASE_URI + "/anuncio";
        //}
        String uri = BASE_URI + "/anuncio";
        WebTarget target = client.target(uri);
        Response response = target.request().get();
        if (response.getStatus() == Response.Status.OK.getStatusCode())
            return response.readEntity(String.class);
        else
            throw new Music4youClientException(response.readEntity(String.class));
    }


    public boolean login(String userid, String password) {
        //String loginUri = getLink(root.getLinks(), "login").getUri().toString();
        String loginUri = BASE_URI + "/login";
        WebTarget target = client.target(loginUri);
        Form form = new Form();
        form.param("login", userid);
        form.param("password", password);
        String json = target.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
        authToken = (new Gson()).fromJson(json, AuthToken.class);
        Log.d(TAG, json);
        return true;
    }


    public String getAnuncio(String uri) throws Music4youClientException {
        WebTarget target = client.target(uri);
        Response response = target.request().get();
        if (response.getStatus() == Response.Status.OK.getStatusCode())
            return response.readEntity(String.class);
        else
            throw new Music4youClientException(response.readEntity(String.class));
    }



    public Root getRoot() {
        return root;
    }

    public final static Link getLink(List<Link> links, String rel){
        for(Link link : links){
            if(link.getRels().contains(rel)) {
                return link;
            }
        }
        return null;
    }
}
