/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.fit.vmw.ws10_102_27.ranking;

import java.util.List;

/**
 *
 * @author gizmo
 */
public interface Sorter<T extends Rankable> {

    public List<T> sort();

}
