<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>success</h1>
        <jsp:useBean id="userinfo" class="beginnersbook.com.Details"></jsp:useBean> 
        <jsp:setProperty property="*" name="userinfo"/> 
        You have enterted below details:<br> 
        <jsp:getProperty property="username" name="userinfo"/><br> 
        <jsp:getProperty property="password" name="userinfo"/><br> 
        <jsp:getProperty property="age" name="userinfo" /><br>
    </body>
    
</html>
