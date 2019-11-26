
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../ad_left.jsp" %>

<div class="contenedor">

    <h3 class="titulo left-lg center">Complete la Informacion</h3>

    <c:if test="${resultado!=null}">
        <c:if test="${resultado==1}">
            <p style="color:darkgreen"><strong>Operaci�n realizada correctamente.</strong></p>
        </c:if>
        <c:if test="${resultado==0}">
            <p style="color:darkred"><strong>La operaci�n no se realiz�.</strong></p>
        </c:if>
    </c:if>


    <form name="form_paises"
      action="${pageContext.servletContext.contextPath}/Categorias?accion=insertar_modificar"
      method="POST">
        <table class="tblStyle2">
            <tr>
                <td class="col-sm-4"><label for="txtIdCategoria">Id Categoria</label></td>
                <td class="col-sm-4"><input type="text" value="${categ.idCategoria}" name="txtIdCategoria" id="txtIdCategoria" readonly="readonly"></td>
            </tr>
            <tr>
                <td class="col-sm-4"><label for="txtCategoria">Nombre Categoria</label></td>
                <td class="col-sm-4"><input type="text" value="${categ.nombreCategoria}" name="txtCategoria" id="txtCategoria"></td>
            </tr>
        </table>
        <div class="opciones2 mgtop-20">
            <input type="submit" value="Enviar">
            <input onclick="javascript: return window.history.back()" type="button" value="Regresar">
        </div>
    </form>

</div>

<%@include file="../ad_rigth.jsp" %>