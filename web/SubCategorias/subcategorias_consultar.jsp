
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../ad_left.jsp" %>

<div class="contenedor">



    <h3 class="titulo left-lg center">Listado de Categorias</h3>
    
    <c:if test="${resultado!=null}">
        <c:if test="${resultado==1}">
            <p style="color:darkgreen"><strong>Operación realizada correctamente.</strong></p>
        </c:if>
        <c:if test="${resultado==0}">
            <p style="color:darkred"><strong>La operación no se realizó.</strong></p>
        </c:if>
    </c:if>
            
    <div class="busqueda1" style="width: 75%; text-align: right">
        <form action="${pageContext.servletContext.contextPath}/SubCategorias" method="get">
            <input type="text" name="txtBusqueda" id="txtBusqueda" value="${valor}" />
            <input type="submit" value="Buscar"/>
        </form>
        <div class="buttons mgtop-20" >
            <a href="${pageContext.servletContext.contextPath}/SubCategorias?accion=insertar">Nuevo</a>
        </div>            
    </div>

    ${tabla}

</div>

<%@include file="../ad_rigth.jsp" %>