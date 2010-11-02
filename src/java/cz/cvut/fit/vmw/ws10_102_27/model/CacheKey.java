package cz.cvut.fit.vmw.ws10_102_27.model;

/**
 * Třída <code>FlickrAPI</code>
 *
 * @author chadijir, masekji4
 */
public class CacheKey {

    String keyword;
    int perPage;
    int page;

    /**
     * 
     * @param keyword
     * @param perPage
     * @param page
     */
    public CacheKey(String keyword, int perPage, int page) {
        this.keyword = keyword;
        this.perPage = perPage;
        this.page = page;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CacheKey other = (CacheKey) obj;
        if ((this.keyword == null) ? (other.keyword != null) : !this.keyword.equals(other.keyword)) {
            return false;
        }
        if (this.perPage != other.perPage) {
            return false;
        }
        if (this.page != other.page) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.keyword != null ? this.keyword.hashCode() : 0);
        hash = 31 * hash + this.perPage;
        hash = 31 * hash + this.page;
        return hash;
    }
}
