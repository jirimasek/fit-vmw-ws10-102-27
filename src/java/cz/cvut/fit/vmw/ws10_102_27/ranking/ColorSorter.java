/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.vmw.ws10_102_27.ranking;

import com.aetrion.flickr.photos.Photo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Sorts all images by color. A public accessor to whole color ranking system.
 *
 * @author chadijir, masekji4
 */
public class ColorSorter implements Sorter<RankedPhoto> {

    private List<RankedPhoto> photos;

    /**
     * For every photo an instance of RankedPhoto is created, then the features
     * and distance are counted.
     * @param photos
     * @param rank
     */
    public ColorSorter(Collection<Photo> photos, ColorRank rank) {
        this.photos = new ArrayList<RankedPhoto>();
        for (Photo p : photos) {
            RankedPhoto pp = new RankedPhoto(p);
            pp.countFeatures();
            pp.countDistance(rank);
            this.photos.add( pp );
        }
    }

    /**
     * Uses Collections.sort method.
     * @return sorted collection (based on compareTo method of RankedPhoto)
     */
    public List<RankedPhoto> sort() {
        Collections.sort(photos);
        return photos;
    }



}
