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
 * RankedPhoto is an implementation of Rankable and Comparable interfaces.
 * It encapsulates the flickr photo and provides methods for comparing
 * the photo to the referential color. It uses ...
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
     * Flag whether the histogram object has to be persisted
     */
    private boolean saveHistogram = false;

    /**
     * We try to obtain histogram form persistence layer. If not found, new one
     * is created and saveHistogram flag is set to true.
     * @param photo Photo object from flickr
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
     * Counts distance. In this implementation, a simple sum of all
     * characteristic numbers for R/G/B channels is set as the distance.
     * The lower the number is, the closer the image is to the rank.
     * @param rank Referential color
     */
    public void countDistance(ColorRank rank) {
        double redAvg = countColorCharacteristicNumber(rank.getRed(), PhotoHistogram.RED);
        double greenAvg = countColorCharacteristicNumber(rank.getGreen(), PhotoHistogram.GREEN);
        double blueAvg = countColorCharacteristicNumber(rank.getBlue(), PhotoHistogram.BLUE);
        distance = redAvg+greenAvg+blueAvg;
    }

    /**
     * Shorthand to counting characteristic number from a range of histogram. Range is of
     * size baseIndex-EPSILON..baseIndex+EPSILON. Boundaries (0, HIST_SIZE) are checked.
     * @param baseIndex Center of the range of the histogram. I. e. R/G/B value of referential color.
     * @param color RED, GREEN or BLUE (constants from PhotoHistogram class)
     * @return characteristic number for given color. The further the value is
     * from baseIndex, the more it adds into resulting average. If the resulting
     * number is 0, Double.MAX_VALUE is returned instead.
     */
    private double countColorCharacteristicNumber(int baseIndex, int color) {
        int sum = 0;
        int values = 0;
        for (int i = baseIndex - EPSILON; i < baseIndex + EPSILON; i++) {
            if (i >= PhotoHistogram.HIST_SIZE) {
                break;
            }
            if (i < 0) {
                continue;
            }
            sum += (histogram.getValue(color, i) * (i - baseIndex == 0 ? 1 : Math.abs(i - baseIndex)));
            ++values;
        }
        return (sum / values == 0.0 ? Double.MAX_VALUE : sum / values);
    }

    /**
     * If histogram is already present in DB, it is not counted. Otherwise
     * an image stream from flickr is obtained, JPEG is decoded, histogram is
     * counted and then saved into DB.
     * JPEGDecoder was borrowed and very slightly modified from
     * http://jcs.mobile-utopia.com/jcs/33411_JPEGDecoder.java. Thank you.
     * It is because App Engine does not support BufferedImage. If you can, use
     * that instead, the software will be probably faster.
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
     * Walks through MyPixelArray and counts histogram for all pixels and all
     * three RGB channels.
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
