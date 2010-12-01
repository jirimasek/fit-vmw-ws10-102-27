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

    public static final int SEARCH_LIMIT = 50;

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
            PhotoList result = flickrAPI.search(request.getParameter("q"), SEARCH_LIMIT, 1);

            String c = request.getParameter("c");

            // transform color from hex to RGB
            int color = Integer.parseInt( c.toLowerCase(), 16 );
            int rr = (color & 0x00FF0000) >> 16;
            int gg =(color & 0x0000FF00) >> 8;
            int bb = (color & 0x000000FF);
            ColorRank cr = new ColorRank(rr, gg, bb);

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
