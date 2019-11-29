
<%@include file="_top.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3 class="mgtop-20">FORMA DE PAGO</h3>
<div class="contenedor row logClient">
    <div class="col-lg-6">
        <h3>Pagar como cliente</h3>

        <form action="${pageContext.servletContext.contextPath}/Usuarios?accion=cliente" method="post">
            <p>Si ya posees una cuenta, por favor ingresa tu dirección de correo electrónico y clave a continuación.</p>
            <label for="">USUARIO</label>
            <input type="text" name="txtUsuario" id="txtUsuario">
            <label for="">CLAVE</label>
            <input type="text" name="txtClave" id="txtClave">
            <input type="submit" value="Iniciar Sesion">
        </form>
    </div>
    <div class="col-lg-6">
        <h3>Pagar como Invitado</h3>
        <form action="${pageContext.servletContext.contextPath}/Usuarios?accion=invitado" method="post">
            <p>Puede finalizar tu compra sin tener necesidad de crear una cuenta.</p>
            <label for="">DUI</label>
            <input type="text" name="txtDUI" id="txtDUI">                       
            <label for="">Telefono</label>
            <input type="text" name="txtTelefono" id="txtTelefono">
            <input type="submit" value="Aceder">
            <span style="font-weight: bold;">¿TODAVÍA NO TIENES CUENTA?</span>
            <p>Por favor, tomese un minuto para completar nuestro proceso de registro corto y disfrute de los muchos beneficios de una cuenta de Vidrí.</p>
            <a href="${pageContext.servletContext.contextPath}/Usuario?accion=registrar">Crear Cuenta</a>
        </form>

    </div>
</div>

<%@include file="carrito.jsp"%>
<%@include file="_down.jsp"%>