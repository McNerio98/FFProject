
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../ad_left.jsp" %>


<h3 class="titulo center">Administradores</h3>


<c:if test="${resultado!=null}">
    <c:if test="${resultado==1}">
        <h4 class="notificacion exito">Operacion realizada con exito</h4>
    </c:if>
    <c:if test="${resultado==0}">
        <h4 class="notificacion fail">Operacion no se realizo</h4>
    </c:if>
</c:if>
        
<div class="contenedor flexWrap">
    

    <c:forEach var="Iterador" items="${ListAdmin}">
        <div class="tarjeta1">
            <img src="${pageContext.servletContext.contextPath}/Administradores/Photos${Iterador.photoPath}">
            <span>${Iterador.nombre} <b> ${Iterador.apellido}</b></span>
            <p>${Iterador.usuario}</p>
            <p>Rol | <span>SuperAdmin</span></p>
            <span>${Iterador.correo}</span>                
            <span>Cel | ${Iterador.telefono}</span>
            <div id="opciones">
                <div><a href="${pageContext.servletContext.contextPath}/Administradores?accion=update&id=${Iterador.usuario}"><i class="fas fa-pen-square icon"></i><br><span>Editar</span></a></div>
                <div><a href="${pageContext.servletContext.contextPath}/Administradores?accion=delete&id=${Iterador.usuario}"><i class="fas fa-minus-circle icon"></i><br><span>Eliminar</span></a></div>
                <div><a href=""><i class="fas fa-user-slash icon"></i><br><span>Inhabilitar</span></a></div>
            </div>
        </div>        
    </c:forEach>
    
    <a href="${pageContext.servletContext.contextPath}/Administradores?accion=insert">
        <div class="tarjeta1Add" id="nuevoAdmin">
            <i class="fas fa-plus icon"></i>
            <span>Nuevo Administrador</span>
        </div>                        
    </a>   
    

</div>


<%@include file="../ad_rigth.jsp" %>
