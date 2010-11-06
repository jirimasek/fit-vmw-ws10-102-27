/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.vmw.ws10_102_27.ranking;

/**
 *
 * @author chadijir, masekji4
 */
public interface Rankable<T extends Rank> {

    public double getDistance();
    public void countDistance(T rank);
    public void countFeatures();

}
