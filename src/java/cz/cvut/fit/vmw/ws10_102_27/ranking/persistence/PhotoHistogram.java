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
 * Class is ready for histogram binning, when the HIST_SIZE can be set to a
 * different value than 256 (typically lower). incrementValue and getValue
 * methods are capable of mapping index to appropriate bin.
 * @author chadijir, masekji4
 */
@PersistenceCapable
public class PhotoHistogram {

    /**
     * Key should be probably generated from some unique value, like photo link
     * or filename.
     */
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    /**
     * Number of bins, i. e. size of underlying arrays.
     */
    @NotPersistent
    public static final int HIST_SIZE = 256;
    /**
     * Total max size of any index that may be passed to this class. For RGB,
     * it is 256.
     */
    @NotPersistent
    private static final int MAX_INDEX_SIZE = 256;
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
       int realindex = index/(MAX_INDEX_SIZE/HIST_SIZE);
        switch (color) {
            case RED:
                redHistogram[realindex]++;
                break;
            case GREEN:
                greenHistogram[realindex]++;
                break;
            case BLUE:
                blueHistogram[realindex]++;
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
        int realindex = index/(MAX_INDEX_SIZE/HIST_SIZE);
        switch (color) {
            case RED:
                return redHistogram[realindex];
            case GREEN:
                return greenHistogram[realindex];
            case BLUE:
                return blueHistogram[realindex];
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
