package edu.upc.eetac.dsa.music4you.client.entity;

import java.util.List;

/**
 * Created by hicham.az on 12/06/2016.
 */
public class Comment {


    private List<Link> links;
    private String id;
    private String userid;
    private String creator;
    private String anuncioid;
    private String eventiid;
    private String comment;
    private long creation_timestamp;
    private long lastModified;





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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAnuncioid() {
        return anuncioid;
    }

    public void setAnuncioid(String anuncioid) {
        this.anuncioid = anuncioid;
    }

    public String getEventiid() {
        return eventiid;
    }

    public void setEventiid(String eventiid) {
        this.eventiid = eventiid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCreation_timestamp() {
        return creation_timestamp;
    }

    public void setCreation_timestamp(long creation_timestamp) {
        this.creation_timestamp = creation_timestamp;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }


}
