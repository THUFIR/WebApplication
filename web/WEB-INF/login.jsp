<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <img src="${sessionScope.auth.duke}"> 
        my name is ${requestScope.controller.myName}, ${requestScope.controller.myId}, what's yours?
        <form name="user_name_form" action="controller" method="POST">
            login: <input type="text" name="login" value="" size='20' />
            <p>
                <input type="submit">
            </p>
        </form>
        ${sessionScope.auth.greeting}
        <p></p>
        <p></p>
        <p></p>
        <p></p>
        <img src="${sessionScope.auth.image}"> 
    </body>
</html>