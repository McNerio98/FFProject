
<%@page import="com.ferreteria.entidad.Menu"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../ad_left.jsp" %>

<style>
    #opciones li a{
        text-decoration: none;
        font-size: 14px;
    }
</style>


<h3 class="titulo center">Selecionar tipo de operacion</h3>

<div class="contenedor flexWrap">
    <c:forEach var="iteracion" items="${PermisosAsignados}">
        <a href="${pageContext.servletContext.contextPath}${iteracion.controlador}?op=${iteracion.menu}" class="cicle1"><i class="${iteracion.iconPath}"></i><span style="text-align: center;"> ${iteracion.menu}</span></a>
    </c:forEach>
</div>


<%@include file="../ad_rigth.jsp" %>
