/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.vmw.ws10_102_27.ranking.persistence;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 *
 * @author gizmo
 */
@PersistenceCapable
public class MultiColorHistogram {

    @NotPersistent
    public final static int MAX_SIZE = 216;

    // pole sirky n obsahujici hodnoty ranku obrazku
    @Persistent
    private int [] ranks = new int[MAX_SIZE];

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    public MultiColorHistogram() {
    }

    public void increment(int i) {
        if (i >= MAX_SIZE) { return;}
        ranks[i]++;
    }

    public int get(int i) {
        if (i >= MAX_SIZE) { return -1;}
        return ranks[i];
    }

    public int[] getRanks() {
        return ranks;
    }

    public void setRanks(int[] ranks) {
        this.ranks = ranks;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    

}
