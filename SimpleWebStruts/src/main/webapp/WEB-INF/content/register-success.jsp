<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <title>Registration Success</title>
    </head>
    <body>
        <h1>Congrats <s:property value="user.name" />!</h1>
        
        <p>A confirmation email has been sent to <b><s:property value="user.email" /></b></p>
        <s:a href="register">Home</s:a>        
    </body>
</html>
