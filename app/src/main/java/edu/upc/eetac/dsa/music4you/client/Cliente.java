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
 * Created by hicham.az on 10/06/2016.
 */
public class Cliente {
    private final static String BASE_URI = "http://80.103.156.84:8080/music4you";
    private static Cliente instance;
    private Root root;
    private ClientConfig clientConfig = null;
    private Client client = null;
    private AuthToken authToken = null;
    private final static String TAG = Cliente.class.toString();

    private Cliente() {
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


    public String getSting(String uri) throws Music4youClientException {
        return null;
    }

    public static Cliente getInstance() {
        if (instance == null)
            instance = new Cliente();
        return instance;
    }

    private void loadRoot() {
        WebTarget target = client.target(BASE_URI);
        Response response = target.request().get();

        String json = response.readEntity(String.class);
        root = (new Gson()).fromJson(json, Root.class);
    }

    public String getAnuncios(String uri) throws Music4youClientException {
        if(uri==null){
            uri = BASE_URI + "/anuncio";//getLink(authToken.getLinks(), "current-stings").getUri().toString();
        }
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