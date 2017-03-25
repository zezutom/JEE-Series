<%-- 
    Document   : response
    Created on : Mar 25, 2017, 8:11:47 AM
    Author     : tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello</title>
    </head>
    <body>
        <jsp:useBean id="nameHandler" scope="session" class="org.zezutom.web.NameHandler" />
        <jsp:setProperty name="nameHandler" property="name" />
        <h1>Hello <jsp:getProperty name="nameHandler" property="name" />!</h1>
    </body>
</html>
