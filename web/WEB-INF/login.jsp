<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <img src="${requestScope.filterToken.duke}"> 
        <p>
            ${requestScope.filterToken.duke}
        <p>
            my name is ${requestScope.controllerToken.myName}, ${requestScope.controllerToken.myId}, what's yours?
        <form name="user_name_form" action="controller" method="POST">
            name <input type="text" name="name" value="" size='20' />
            <p>
                <input type="submit">
            </p>
        </form>
      
    </body>
</html>