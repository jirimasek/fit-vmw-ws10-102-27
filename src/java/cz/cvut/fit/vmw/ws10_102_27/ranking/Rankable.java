/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.vmw.ws10_102_27.ranking;

/**
 * Simple interface ensuring measurability and sorting capability of items.
 * @author chadijir, masekji4
 */
public interface Rankable<T extends Rank> {

    /**
     *
     * @return number characterizing the similarity of referential rank and
     * this object
     */
    public double getDistance();
    /**
     * Counts distance that is then returned by getDistance() method
     * @param rank
     */
    public void countDistance(T rank);
    /**
     * In this method, all magic should happen. Features are extracted, so
     * later on a distance can be counted from them.
     */
    public void countFeatures();

}
