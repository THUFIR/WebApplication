<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <form name="user_name_form" action="controller" method="POST">
            name <input type="text" name="name" value="skinner" size='20' />
            <p>
                <input type="submit">
            </p>
        </form>
    <c:set var="name" value="taintedAttribute" />
    <c:out value="${sessionScope[name]}"/>
    </body>
</html>