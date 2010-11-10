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
            <input id="red" name="c" type="radio" value="red" checked="checked" /><label for="red"><span>Red</span></label>
            <input id="orange" name="c" type="radio" value="orange" /><label for="orange"><span>Orange</span></label>
            <input id="yellow" name="c" type="radio" value="yellow" /><label for="yellow"><span>Yellow</span></label>
            <input id="green" name="c" type="radio" value="green" /><label for="green"><span>Green</span></label>
            <input id="teal" name="c" type="radio" value="teal" /><label for="teal"><span>Teal</span></label>
            <input id="blue" name="c" type="radio" value="blue" /><label for="blue"><span>Blue</span></label>
            <input id="purple" name="c" type="radio" value="purple" /><label for="purple"><span>Purple</span></label>
            <input id="pink" name="c" type="radio" value="pink" /><label for="pink"><span>Pink</span></label>
            <input id="white" name="c" type="radio" value="white" /><label for="white"><span>White</span></label>
            <input id="gray" name="c" type="radio" value="gray" /><label for="gray"><span>Gray</span></label>
            <input id="black" name="c" type="radio" value="black" /><label for="black"><span>Black</span></label>
            <input id="brown" name="c" type="radio" value="brown" /><label for="brown"><span>Brown</span></label>
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