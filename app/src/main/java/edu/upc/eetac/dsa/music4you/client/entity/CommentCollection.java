package edu.upc.eetac.dsa.music4you.client.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hicham.az on 12/06/2016.
 */
public class CommentCollection {

        private List<Link> links;
        private long newestTimestamp;
        private long oldestTimestamp;
        private List<Comment> comments = new ArrayList<>();
}
