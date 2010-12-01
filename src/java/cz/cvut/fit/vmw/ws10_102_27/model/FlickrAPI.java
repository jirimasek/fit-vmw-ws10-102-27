package cz.cvut.fit.vmw.ws10_102_27.model;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.photos.PhotoList;
import com.aetrion.flickr.photos.SearchParameters;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Třída <code>FlickrAPI</code>
 *
 * @author chadijir, masekji4
 */
public class FlickrAPI {

    private static FlickrAPI instance;
    public static final String API_KEY = "aa864b3f386a237b030dcfa76738b26d";
    public static final String SHARED_SECRET = "aab2da1f8632c7c3";
    Flickr flickr;
    REST rest;
    Hashtable<CacheKey, PhotoList> cache;

    /**
     *
     */
    private FlickrAPI() {
        try {
            flickr = new Flickr(API_KEY, SHARED_SECRET, new REST());
        } catch (ParserConfigurationException ex) {
        }

        cache = new Hashtable<CacheKey, PhotoList>();
    }

    /**
     *
     * @return
     */
    public static FlickrAPI getInstance() {
        if (instance == null) {
            instance = new FlickrAPI();
        }

        return instance;
    }

    /**
     *
     * @param keyword
     * @param perPage
     * @param page
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws FlickrException
     */
    public PhotoList search(String keyword, int perPage, int page) throws IOException, SAXException,
            FlickrException {

        CacheKey key = new CacheKey(keyword, perPage, page);

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        SearchParameters sp = new SearchParameters();
        
        sp.setText(keyword);

        PhotoList result = flickr.getPhotosInterface().search(sp, perPage, page);

        if (cache.size() > 49) {
            Enumeration keys = cache.keys();

            while (keys.hasMoreElements()) {
                CacheKey k = (CacheKey) keys.nextElement();

                if (!keys.hasMoreElements()) {
                    cache.remove(k);
                }
            }
        }

        cache.put(key, result);
        
        return result;
    }

    /**
     *
     * @return
     */
    public List<String> getHistory() {
        List<String> history = new ArrayList<String>();

        for (CacheKey cacheKey : cache.keySet()) {
            history.add(cacheKey.keyword);
        }

        Collections.reverse(history);

        return history;
    }
}
