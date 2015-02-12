<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <img src="${requestScope.filterToken.duke}"> 

        my name is ${requestScope.controllerToken.myName}, ${requestScope.controllerToken.myId}, what's yours?
        <form name="user_name_form" action="controller" method="POST">
            login: <input type="text" name="login" value="" size='20' />
            <p>
                <input type="submit">
            </p>
        </form>
        ${sessionScope.authorization.greeting}
    </body>
</html>