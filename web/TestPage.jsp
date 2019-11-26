<%-- 
    Document   : TestPage
    Created on : 05-nov-2019, 15:12:09
    Author     : ZEUS
--%>

<%@page import="com.ferreteria.entidad.Rol"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title
    </head>
    <body>    
    <input type="file" name="filePhoto"/>
    <input type="text" name="txtNombre"/>
    <br/>
    <a href="${pageContext.servletContext.contextPath}/ServletPrueba">Enviar datos</a>
    </body>
</html>
