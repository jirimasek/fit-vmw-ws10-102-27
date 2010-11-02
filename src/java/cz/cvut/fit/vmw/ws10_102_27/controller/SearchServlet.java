package cz.cvut.fit.vmw.ws10_102_27.controller;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.photos.PhotoList;
import cz.cvut.fit.vmw.ws10_102_27.model.FlickrAPI;
import java.io.IOException;
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

    static String apiKey = "aa864b3f386a237b030dcfa76738b26d";
    static String sharedSecret = "aab2da1f8632c7c3";
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
            PhotoList result = flickrAPI.search(request.getParameter("q"), 25, 1);

            request.setAttribute("result", result);;
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
