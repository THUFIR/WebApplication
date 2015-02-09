<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <img src="${requestScope.duke}"> 
        ${requestScope.me}, what's yours?
        <form name="user_name_form" action="controller" method="POST">
            name <input type="text" name="name" value="skinner" size='20' />
            <p>
                <input type="submit">
            </p>
        </form>
        ${requestScope.message}
        <img src="${pageContext.request.contextPath}/WEB-INF/IMAGES/duke.gif" />
        <img src="/WEB-INF/IMAGES/duke.gif" />
        <img src="http://localhost:8080/WebApplication/WEB-INF/IMAGES/duke.gif" />
        <img src="http://localhost:8080/WebApplication/duke.gif" />
        <img src="${requestScope}"/IMAGES/duke.gif>
    </body>
</html>