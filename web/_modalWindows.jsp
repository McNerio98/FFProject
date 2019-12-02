<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input type="hidden" name="" id="status" value="${agregado}">    

<div class="notificacion1 registrar">
    <h4>REGISTRO REQUERIDO</h4>
    <br>
    <p>Para poder contar con las opciones y precios que ofrecemos a otras empresas que se dedican a vender nuestros productos, solicitamos se registren con los datos de su empresa.</p>
    <br>
    <div class="opciones4">
        <input type="button" value="Registrarme">
        <input type="button" value="Cancelar" onclick="closeNot();">
    </div>
</div>


<div class="notificacion1 confirmar">
    <i class="fas fa-clipboard-check icon"></i>
    <br>
    <h4>PRODUCTO AGREGADO CON EXITO</h4>
    <br>
    <br>
    <div class="opciones4">
        <input type="button" value="Aceptar" onclick="closeNot();">
    </div>
</div>