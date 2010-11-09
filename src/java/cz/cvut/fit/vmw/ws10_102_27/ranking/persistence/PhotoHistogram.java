package cz.cvut.fit.vmw.ws10_102_27.ranking.persistence;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Simple entity class, represents Photo's histogram. Flickr photo Id is used
 * as a base for unique key generation. Histogram is represented by three
 * separate arrays, because Google App Engine seems to **not** support n-D
 * arrays. Some useful constants are present here as well.
 * @author chadijir, masekji4
 */
@PersistenceCapable
public class PhotoHistogram {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @NotPersistent
    public static final int HIST_SIZE = 256;
    @NotPersistent
    public static final int RED = 0;
    @NotPersistent
    public static final int GREEN = 1;
    @NotPersistent
    public static final int BLUE = 2;
    @Persistent
    private int[] redHistogram = new int[HIST_SIZE];
    @Persistent
    private int[] greenHistogram = new int[HIST_SIZE];
    @Persistent
    private int[] blueHistogram = new int[HIST_SIZE];

    public PhotoHistogram() {
    }

    /**
     * Increments histogram for given color and index by one.
     * @param color
     * @param index
     */
    public void incrementValue(int color, int index) {
        switch (color) {
            case RED:
                redHistogram[index]++;
                break;
            case GREEN:
                greenHistogram[index]++;
                break;
            case BLUE:
                blueHistogram[index]++;
                break;
        }
    }

    /**
     * Returns value of given color and index.
     * @param color
     * @param index
     * @return value
     */
    public int getValue(int color, int index) {
        switch (color) {
            case RED:
                return redHistogram[index];
            case GREEN:
                return greenHistogram[index];
            case BLUE:
                return blueHistogram[index];
        }
        return 0;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public int[] getBlueHistogram() {
        return blueHistogram;
    }

    public void setBlueHistogram(int[] blueHistogram) {
        this.blueHistogram = blueHistogram;
    }

    public int[] getGreenHistogram() {
        return greenHistogram;
    }

    public void setGreenHistogram(int[] greenHistogram) {
        this.greenHistogram = greenHistogram;
    }

    public int[] getRedHistogram() {
        return redHistogram;
    }

    public void setRedHistogram(int[] redHistogram) {
        this.redHistogram = redHistogram;
    }
}
