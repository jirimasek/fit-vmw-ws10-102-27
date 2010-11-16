/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.vmw.ws10_102_27.ranking;

import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.PhotosInterface;
import com.aetrion.flickr.photos.Size;
import cz.cvut.fit.vmw.ws10_102_27.model.FlickrAPI;
import cz.cvut.fit.vmw.ws10_102_27.ranking.decoder.JPEGDecoder;
import cz.cvut.fit.vmw.ws10_102_27.ranking.decoder.MyPixelArray;
import cz.cvut.fit.vmw.ws10_102_27.ranking.persistence.HistogramDao;
import cz.cvut.fit.vmw.ws10_102_27.ranking.persistence.PhotoHistogram;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Třída <code>RankedPhoto</code>
 *
 * @author chadijir, masekji4
 */
public class RankedPhoto implements Rankable<ColorRank>, Comparable<RankedPhoto> {

    /**
     * This constant represents how many siblings in histogram we take into account.
     */
    private static final int EPSILON = 256;
    /**
     * Total distance from referential color.
     */
    private double distance;
    /**
     * Photo from flickr
     */
    private Photo photo;
    /**
     * Histogram object, may be counted, or retrieved from database
     */
    private PhotoHistogram histogram;
    /**
     * Flag whether the histogram object has to be persisted after counting
     */
    private boolean saveHistogram = false;

    /**
     * We try to obtain histogram form persistence layer. If not found, new
     * is created and saveHistogram flag is set to true.
     * @param photo
     */
    public RankedPhoto(Photo photo) {
        this.photo = photo;
        histogram = HistogramDao.getInstance().getHistogram(photo.getId());
        if (histogram == null) {
            histogram = new PhotoHistogram();
            saveHistogram = true;
            System.out.println("Histogram not from DB, counting");
        } else {
            System.out.println("Histogram from DB, not counting");
        }
    }

    /**
     * Counts euclidian distance from average color to rank color and sets
     * distance field.
     * @param rank Referential color
     */
    public void countDistance(ColorRank rank) {
//        double redAvg = countColorAverage(histogram.getRedMax(), PhotoHistogram.RED);
//        double greenAvg = countColorAverage(histogram.getGreenMax(), PhotoHistogram.GREEN);
//        double blueAvg = countColorAverage(histogram.getBlueMax(), PhotoHistogram.BLUE);
//        // euclidian distance
//        // sqrt[ (redAvg-rank.red)^2 + (greenAvg-rank.green)^2 + (blueAvg-rank.blue)^2 ]
        //distance = Math.sqrt(Math.pow(redAvg - rank.getRed(), 2) + Math.pow(greenAvg - rank.getGreen(), 2) + Math.pow(blueAvg - rank.getBlue(), 2));
        
        distance = Math.sqrt(Math.pow(histogram.getRedMax() - rank.getRed(), 2) + Math.pow(histogram.getGreenMax() - rank.getGreen(), 2) + Math.pow(histogram.getBlueMax() - rank.getBlue(), 2));
        System.out.println(photo.getSmallUrl()+" distance:"+distance);
    }

    /**
     * Shorthand to counting average number from a range of histogram. Range is of
     * size baseIndex-EPSILON..baseIndex+EPSILON. Boundaries (0, HIST_SIZE) are checked.
     * @param baseIndex Center of the range of the histogram.
     * @param color RED, GREEN or BLUE (constants from PhotoHistogram class)
     * @return arithmetical average of all values in given range
     */
    private double countColorAverage(int baseIndex, int color) {
        int sum = 0;
        int values = 0;
        for (int i = baseIndex - EPSILON; i < baseIndex + EPSILON; i++) {
            if (i >= PhotoHistogram.HIST_SIZE) {
                break;
            }
            if (i < 0) {
                continue;
            }
            sum += (histogram.getValue(color, i) * ( 1/ (i - baseIndex == 0 ? 1 : Math.abs(i - baseIndex))) );
            ++values;
        }
        //System.out.println("average:"+color+" "+sum/values);
        return (sum / values == 0.0 ? Double.MAX_VALUE : sum / values);
    }

    /**
     * If histogram is already present in DB, it is not counted. Otherwise
     * an image stream from flickr is obtained, JPEG is decoded, histogram is
     * counted and then saved into DB.
     */
    public void countFeatures() {
        if (saveHistogram) {
            try {
                PhotosInterface pi = new PhotosInterface(FlickrAPI.API_KEY, FlickrAPI.SHARED_SECRET, new REST());
                InputStream img = pi.getImageAsStream(photo, Size.MEDIUM);
                JPEGDecoder decoder = new JPEGDecoder();
                MyPixelArray ar = new MyPixelArray();
                try {
                    decoder.decode(img, ar);
                    this.countHistogram(ar);
                    decoder = null;
                    HistogramDao.getInstance().saveHistogram(histogram, photo.getId());
                    saveHistogram = false;
                } catch (Exception ex) {
                    Logger.getLogger(RankedPhoto.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(RankedPhoto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RankedPhoto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FlickrException ex) {
                Logger.getLogger(RankedPhoto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Walks through MyPixelArray and counts histogram for all values.
     * @param a
     */
    private void countHistogram(MyPixelArray a) {
        // http://freesrc.com/java/j2me/convert-argb-to-rgb-in-j2me/
        int argb;
        for (int i = 0; i < a.getHeight() * a.getWidth(); i++) {
            argb = a.pix[i];
            histogram.incrementValue(PhotoHistogram.RED, (argb & 0x00FF0000) >> 16);
            histogram.incrementValue(PhotoHistogram.GREEN, (argb & 0x0000FF00) >> 8);
            histogram.incrementValue(PhotoHistogram.BLUE, (argb & 0x000000FF));
        }

        int redMax = histogram.getValue(0, 0);
        int greenMax = histogram.getValue(1, 0);
        int blueMax = histogram.getValue(2, 0);

        for (int i = 0; i < PhotoHistogram.HIST_SIZE; i++) {
            if (histogram.getValue(0, i) > redMax) {
                redMax = histogram.getValue(0, i);
                histogram.setRedMax(i);
            }
            if (histogram.getValue(1, i) > greenMax) {
                greenMax = histogram.getValue(1, i);
                histogram.setGreenMax(i);
            }
            if (histogram.getValue(2, i) > blueMax) {
                blueMax = histogram.getValue(2, i);
                histogram.setBlueMax(i);
            }
        }
        System.out.println("redmax" + histogram.getRedMax());
        System.out.println("greenmax" + histogram.getGreenMax());
        System.out.println("bluemax" + histogram.getBlueMax());
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    @Override
    public double getDistance() {
        return distance;
    }

    /**
     * Compares objects by distance.
     * @param o
     * @return 1 if o.distance &lt; this.distance
     */
    @Override
    public int compareTo(RankedPhoto o) {
        if (o.getDistance() == this.getDistance()) {
            return 0;
        } else if (o.getDistance() < this.getDistance()) {
            return 1;
        } else {
            return -1;
        }
    }
}
