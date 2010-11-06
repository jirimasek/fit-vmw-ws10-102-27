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

    private static final int EPSILON = 10;
    private static final int HIST_SIZE = 256;

    private double distance;
    private Photo photo;

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;
    private int[][] histogram = new int[3][HIST_SIZE];

    public RankedPhoto(Photo photo) {
        this.photo = photo;
    }

    /**
     * @TODO
     */
    public void countDistance(ColorRank rank) {
        double redAvg = countColorAverage(rank.getRed(), RED);
        double greenAvg = countColorAverage(rank.getGreen(), GREEN);
        double blueAvg = countColorAverage(rank.getBlue(), BLUE);
        // euclidean distance
        // sqrt[ (redAvg-rank.red)^2 + (greenAvg-rank.green)^2 + (blueAvg-rank.blue)^2 ]
        distance = Math.sqrt( Math.pow(redAvg-rank.getRed(), 2) + Math.pow(greenAvg-rank.getGreen(), 2) + Math.pow(blueAvg-rank.getBlue(), 2) );
        //System.out.println(photo.getSmallUrl()+" distance:"+distance);
    }

    private int countColorAverage(int baseIndex, int color) {
        int sum  = 0;
        int values = 0;
        for (int i = baseIndex-EPSILON; i < baseIndex+EPSILON; i++) {
            if (i >= HIST_SIZE) {break;}
            if (i < 0) {continue;}
            sum += (histogram[color][i] * (1/(i-baseIndex ==0 ? 1 : Math.abs(i-baseIndex))));
            ++values;
        }
        //System.out.println("average:"+color+" "+sum/values);
        return sum / values;
    }

    public void countFeatures() {
        try {
            PhotosInterface pi = new PhotosInterface(FlickrAPI.API_KEY, FlickrAPI.SHARED_SECRET, new REST());
            InputStream img = pi.getImageAsStream(photo, Size.MEDIUM);
            JPEGDecoder decoder = new JPEGDecoder();
            MyPixelArray ar = new MyPixelArray();
            try {
                decoder.decode(img, ar);
                this.countHistogram(ar);
                decoder = null;
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

    private void countHistogram(MyPixelArray a) {
        // http://freesrc.com/java/j2me/convert-argb-to-rgb-in-j2me/
        int argb;
        for (int i = 0; i < a.getHeight()*a.getWidth(); i++) {
            argb = a.pix[i];
            histogram[RED][(argb & 0x00FF0000)>>16]++;
            histogram[GREEN][(argb & 0x0000FF00)>>8]++;
            histogram[BLUE][(argb & 0x000000FF)]++;
        }
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public double getDistance() {
        return distance;
    }

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
