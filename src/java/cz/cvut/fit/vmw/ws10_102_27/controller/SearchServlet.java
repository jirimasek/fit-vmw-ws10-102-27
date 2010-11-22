package cz.cvut.fit.vmw.ws10_102_27.controller;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.photos.PhotoList;
import cz.cvut.fit.vmw.ws10_102_27.model.FlickrAPI;
import cz.cvut.fit.vmw.ws10_102_27.ranking.ColorRank;
import cz.cvut.fit.vmw.ws10_102_27.ranking.ColorSorter;
import cz.cvut.fit.vmw.ws10_102_27.ranking.RankedPhoto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.xml.sax.SAXException;

/**
 * Servlet <code>SearchServlet</code>
 *
 * @author chadijir, masekji4
 */
public class SearchServlet extends HttpServlet {

    Flickr f;
    REST rest;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FlickrAPI flickrAPI = FlickrAPI.getInstance();
        try {
            PhotoList result = flickrAPI.search(request.getParameter("q"), 50, 1);

            String c = request.getParameter("c");

            ColorRank cr = null;

            // Nekolikanasobny if protoze 1.5 neumi switch. Pry...
            if (c.compareTo("red") == 0) {
                cr = new ColorRank(204, 0, 0); //cc0000
            } else if (c.compareTo("orange") == 0) {
                cr = new ColorRank(251, 148, 11); //fb940b
            } else if (c.compareTo("yellow") == 0) {
                cr = new ColorRank(255, 255, 0); //ffff00
            } else if (c.compareTo("green") == 0) {
                cr = new ColorRank(0, 204, 0); //00cc00
            } else if (c.compareTo("teal") == 0) {
                cr = new ColorRank(3, 192, 198); //03c0c6
            } else if (c.compareTo("blue") == 0) {
                cr = new ColorRank(0, 0, 204); //0000cc
            } else if (c.compareTo("purple") == 0) {
                cr = new ColorRank(118, 44, 167); //762ca7
            } else if (c.compareTo("pink") == 0) {
                cr = new ColorRank(255, 152, 191); //ff98bf
            } else if (c.compareTo("white") == 0) {
                cr = new ColorRank(255, 255, 255); //ffffff
            } else if (c.compareTo("gray") == 0) {
                cr = new ColorRank(118, 118, 118); //767676
            } else if (c.compareTo("black") == 0) {
                cr = new ColorRank(0, 0, 0); //000000
            } else if (c.compareTo("brown") == 0) {
                cr = new ColorRank(136, 84, 24); //885418
            }
           
            if (cr != null) {
                ColorSorter cs = new ColorSorter(result, cr);
                List<RankedPhoto> res = cs.sort();
                PhotoList pl = new PhotoList();
                for (RankedPhoto r : res) {
                    pl.add(r.getPhoto());
                }

                request.setAttribute("result", pl);
            } else {
                request.setAttribute("result", result);
            }
        } catch (SAXException ex) {
        } catch (FlickrException ex) {
        }

        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
