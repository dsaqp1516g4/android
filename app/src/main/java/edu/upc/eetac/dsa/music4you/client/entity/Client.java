package edu.upc.eetac.dsa.music4you.client.entity;

import android.util.Log;

import com.google.gson.Gson;

import org.glassfish.jersey.client.ClientConfig;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by hicham.az on 10/06/2016.
 */
public class Client {
    private final static String BASE_URI = "http://192.168.1.37:8080/beeter";
    private static Client instance;
    private Root root;
    private ClientConfig clientConfig = null;
    private javax.ws.rs.client.Client client = null;
    private AuthToken authToken = null;
    private final static String TAG = Client.class.toString();

    private Client() {
        clientConfig = new ClientConfig();
        client = ClientBuilder.newClient(clientConfig);
        loadRoot();
    }

    public static Client getInstance() {
        if (instance == null)
            instance = new Client();
        return instance;
    }

    private void loadRoot() {
        WebTarget target = client.target(BASE_URI);
        Response response = target.request().get();

        String json = response.readEntity(String.class);
        root = (new Gson()).fromJson(json, Root.class);
    }
    public boolean login(String userid, String password) {
        String loginUri = getLink(root.getLinks(), "login").getUri().toString();
        WebTarget target = client.target(loginUri);
        Form form = new Form();
        form.param("login", "b1");
        form.param("password", "1234");
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