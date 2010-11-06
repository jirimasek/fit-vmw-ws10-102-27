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
 * Třída <code>ColorSorter</code>
 * Sorts all images by color.
 *
 * @author chadijir, masekji4
 */
public class ColorSorter implements Sorter<RankedPhoto> {

    private List<RankedPhoto> photos;

    public ColorSorter(Collection<Photo> photos, ColorRank rank) {
        this.photos = new ArrayList<RankedPhoto>();
        for (Photo p : photos) {
            RankedPhoto pp = new RankedPhoto(p);
            pp.countFeatures();
            pp.countDistance(rank);
            this.photos.add( pp );
        }
    }

    public List<RankedPhoto> sort() {
        Collections.sort(photos);
        return photos;
    }



}
