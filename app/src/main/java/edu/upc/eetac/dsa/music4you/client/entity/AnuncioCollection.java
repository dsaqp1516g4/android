package edu.upc.eetac.dsa.music4you.client.entity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by root on 12/06/16.
 */
public class AnuncioCollection {
    private List<Link> links;
    private long newestTimestamp;
    private long oldestTimestamp;
    private List<Anuncio> stings = new ArrayList<>();

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public long getNewestTimestamp() {
        return newestTimestamp;
    }

    public void setNewestTimestamp(long newestTimestamp) {
        this.newestTimestamp = newestTimestamp;
    }

    public long getOldestTimestamp() {
        return oldestTimestamp;
    }

    public void setOldestTimestamp(long oldestTimestamp) {
        this.oldestTimestamp = oldestTimestamp;
    }

    public List<Anuncio> getStings() {
        return stings;
    }

    public void setStings(List<Anuncio> stings) {
        this.stings = stings;
    }
}
