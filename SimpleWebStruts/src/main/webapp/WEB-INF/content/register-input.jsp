<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <title>New User</title>        
    </head>
    <body>
        <h1>Registration Form</h1>
        <s:form action="register" method="post" validate="true">
            <s:textfield label="Username" name="name" />
            <s:textfield label="Email" name="email" />
            <s:password label="Password" name="password" />
            <s:submit />
        </s:form>
    </body>
</html>
