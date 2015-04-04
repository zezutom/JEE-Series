<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Response Page</title>
    </head>
    <body>
        <jsp:useBean id="nameHandler" 
                     scope="session" 
                     class="org.zezutom.blog.series.jee.webjsp.NameHandler" />
        <jsp:setProperty name="nameHandler" property="name" />        
        <h1>Hello <jsp:getProperty name="nameHandler" property="name" />!</h1>
        <a href="index.jsp">Reset</a>
    </body>
</html>
