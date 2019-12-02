<%-- 
    Document   : _pnlLeft
    Created on : 28-oct-2019, 9:55:09
    Author     : ZEUS
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession sesion = request.getSession(); %>

<%
    if (request.getSession().getAttribute("Usuario") == null) {
        response.sendRedirect("Manager");
    }
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/manager.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/MpnlAdmin.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/tableStyles.css">
        
        <script src="${pageContext.servletContext.contextPath}/js/all.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery-3.4.1.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/responsiveMenu.js"></script>
        <title>Administrador</title>
    </head>
    <body>
        <div class="pnlLeft">
            <img src="img/Logo.png" alt="">
            <ul>
                <c:forEach var="iteracion" items="${MenuPrincipal}">
                    <li><a href="${pageContext.servletContext.contextPath}${iteracion.controlador}?op=${iteracion.idMenu}" <c:if test="${iteracion.idMenu == numMenu}">class="active"</c:if>><i class="${iteracion.iconPath}"></i> ${iteracion.menu}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="pnlRight">
                
        <div class="row encabezado">
            <div style="width: 15%; align-items: center;">
                <i class="fas fa-bars icon" style="margin-left: 5px;" id="menu"></i>
            </div>
            <div class="dataNopc" style="width: 85%;">
                <div>
                    <a href=""><i class="fas fa-question-circle icon"></i></a>
                </div>
                <div class="usuario">
                    <img src="${pageContext.servletContext.contextPath}/Administradores/Photos${Photo}" alt="">
                    <div>
                        <span>${Usuario}</span>
                        <span class="rol">[SuperAdmin]</span>                        
                    </div>
                </div>
                <div>
                    <a href="ManagerPrincipal?accion=logout"><i class="far fa-arrow-alt-circle-right icon"></i></a>
                </div>                     
            </div>
        </div>                