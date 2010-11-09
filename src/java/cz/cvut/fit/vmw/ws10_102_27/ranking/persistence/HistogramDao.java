package cz.cvut.fit.vmw.ws10_102_27.ranking.persistence;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

/**
 * Singleton, basic DAO object for PhotoHistogram class.
 * @author chadijir, masekji4
 */
public class HistogramDao {

    /**
     * instance
     */
    private static HistogramDao instance;

    private HistogramDao() {
    }

    /**
     * Lazy loading getter.
     * @return instance
     */
    public static HistogramDao getInstance() {
        if (instance == null) {
            instance = new HistogramDao();
        }
        return instance;
    }

    /**
     * Tries to save ph with key generated from photoId.
     * No Exceptions are caught.
     * @param ph
     * @param photoId
     */
    public void saveHistogram(PhotoHistogram ph, String photoId) {
        Key k = KeyFactory.createKey(PhotoHistogram.class.getSimpleName(), photoId);
        ph.setKey(k);
        PersistenceManager pm = PMF.get().getPersistenceManager();
        pm.makePersistent(ph);
        pm.close();
    }

    /**
     * Tries to obtain histogram for photoId. If not found, null is returned.
     * JDOOBjectNotFound is caught here.
     * @param photoId flickr photoId
     * @return PhotoHistogram or null
     */
    public PhotoHistogram getHistogram(String photoId) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            Key k = KeyFactory.createKey(PhotoHistogram.class.getSimpleName(), photoId);
            return pm.getObjectById(PhotoHistogram.class, k);
        } catch (JDOObjectNotFoundException ex) {
            return null;
        } finally {
            pm.close();
        }
    }
}
