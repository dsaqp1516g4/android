package edu.upc.eetac.dsa.music4you.client.entity;

import java.util.List;

/**
 * Created by hicham.az on 13/06/2016.
 */
public class user {
    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public void setLoginSuccesful(Boolean loginSuccesful) {
        this.loginSuccesful = loginSuccesful;
    }

    public Boolean getLoginSuccesful() {
        return loginSuccesful;
    }


    private Boolean loginSuccesful;

    private List<Link> links;
    private String id;
    private String loginid;
    private String email;
    private String fullname;

}
