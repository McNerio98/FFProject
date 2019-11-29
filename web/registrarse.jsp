
<%@include file="_top.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3 class="mgtop-20">FORMA DE PAGO</h3>
<div class="contenedor row logClient">
    <div class="col-lg-6">
        <h3>Nueva cuenta cliente</h3>

        <form action="${pageContext.servletContext.contextPath}/Usuarios" method="post">
            <p>Para poder registrarte como cliente frecuente debes llenar los siguientes campos.</p>
            <label for="">USUARIO</label>
            <input type="text" name="txtUsuario" id="txtUsuario">
            <label for="">CLAVE</label>
            <input type="password" name="txtClave" id="txtClave">
            <label for="">REPETIR CLAVE</label>
            <input type="password" name="txtClave2" id="txtClave2">
            <label for="">DUI</label>
            <input type="text" name="txtDUI" id="txtDUI">            
            <label for="">Nombre Completo</label>
            <input type="text" name="txtNombre" id="txtNombre">            
            <label for="">Telefono</label>
            <input type="text" name="txtTelefono" id="txtTelefono">                
            <input type="submit" value="Registrar">
        </form>
    </div>
    <div class="col-lg-6">
        <br><br>
        <p style="text-align: center;">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Aut error amet natus a, sit commodi doloremque minus.</p>
        <img style="width: 100%; height: auto; padding: 10px 0px;" src="${pageContext.servletContext.contextPath}/img/2315.jpg" alt="">
        <p style="text-align: center;">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Aut error amet natus a</p>
        <img style="width: 100%; height: auto; padding: 10px 0px;" src="${pageContext.servletContext.contextPath}/img/2316.jpg" alt="">
    </div>
</div>
<%@include file="carrito.jsp"%>
<%@include file="_down.jsp"%>