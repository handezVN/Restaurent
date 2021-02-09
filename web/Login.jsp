<%-- 
    Document   : Login
    Created on : Jan 18, 2021, 8:05:25 AM
    Author     : handez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="LoginServlet">
            <input type="number" name="number" value="${param.number}" required="" placeholder="Number"><br>
            <input type="password" name="password" value="${param.password}" required="" placeholder="PassWord"><br>
            <input type="submit" value="Login">
        </form>
            <c:if test="${not empty Err}">
                <Strong style="color: red">${Err}</Strong>
            </c:if>
                <a href="Register.jsp">Register</a>
    </body>
</html>
