/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fit.vmw.ws10_102_27.ranking;

import cz.cvut.fit.vmw.ws10_102_27.ranking.JPEGDecoder.PixelArray;

/**
 *
 * @author gizmo
 */
public class MyPixelArray implements PixelArray {

    int[] pix;
    int width, height;

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        pix = new int[width * height];
    }

    public void setPixel(int x, int y, int argb) {
        pix[x + y * width] = argb;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    
}
