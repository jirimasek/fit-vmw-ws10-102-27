<%-- 
    Document   : search
    Created on : Nov 2, 2010, 11:52:38 AM
    Author     : chadijir, masekji4
--%>

<%@page import="java.util.Date"%>
<%@page import="com.aetrion.flickr.photos.Photo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.aetrion.flickr.photos.PhotoList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flickr - feature-based reranking</title>
    </head>
    <body>
        <h1>Flickr - feature-based reranking</h1>

        <form method="get" action="/search">
            <input id="q" name="q" type="text" />
            <input id="submit" name="submit" type="submit" value="Search"/>
        </form>

        <% PhotoList result = (PhotoList) request.getAttribute("result"); %>

        <% if (result != null) { %>
        <% for (Photo photo : (ArrayList<Photo>) result) { %>
        <img src="<%= photo.getSmallUrl() %>" alt="<%= photo.getTitle() %>" />
        <% } %>
        <% } %>
    </body>
</html>