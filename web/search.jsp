<%-- 
    Document   : search
    Created on : Nov 2, 2010, 11:52:38 AM
    Author     : chadijir, masekji4
--%>

<%@page import="cz.cvut.fit.vmw.ws10_102_27.model.FlickrAPI"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="com.aetrion.flickr.photos.Photo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.aetrion.flickr.photos.PhotoList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="cs-cz" lang="cs-cz">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="/style/global.css" type="text/css" media="all" />
        <script type="text/javascript" src="/js/global.js"></script>
        <script type="text/javascript" src="/js/jscolor.js"></script>
        <script type="text/javascript" src="/js/clearbox.js"></script>
        <title>Flickr - feature-based reranking</title>
    </head>
    <body onload="init();">
        <h1><a href="/"><span>Flickr - feature-based reranking</span></a></h1>

        <form method="get" action="/search">
            <input id="q" name="q" type="text" value="<%= request.getParameter("q") %>" />
            <input id="c" name="c" class="color" value="<% if (request.getParameter("c") != null) { %><%= request.getParameter("c") %><% } else { %>DA1549<% } %>" />
            <input id="submit" name="submit" type="submit" value=""/>
        </form>

        <% List<String> history = FlickrAPI.getInstance().getHistory(); %>

        <% if (!history.isEmpty()) { %>
        <ul class="history">
        <% for (String keyword : history) { %>
        <li<% if (request.getParameter("q") != null && keyword.compareTo((String)request.getParameter("q")) == 0) { %> class="active"<% } %>><a href="/search?q=<%= keyword %>&c=FFFFFF&submit=Search"><%= keyword %></a></li>
        <% } %>
        </ul>
        <% } %>

        <div class="result">

        <% PhotoList result = (PhotoList) request.getAttribute("result"); %>

        <% if (result != null) { %>
        <% for (Photo photo : (ArrayList<Photo>) result) { %>
        <div class="photo">
            <a href="<%= photo.getLargeUrl() %>" title="<%= photo.getTitle() %>" rel="clearbox[gallery=<%= request.getParameter("q") %>,,title=<%= photo.getTitle() %>]"><img src="<%= photo.getSmallUrl() %>" alt="<%= photo.getTitle() %>" /></a>
        </div>
        <% } %>
        <% } %>
        </div>
    </body>
</html>