<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="css/estilo1.css">
        <link rel="stylesheet" href="css/estilo2.css">
        <link rel="stylesheet" href="css/footer.css">

        <script src="js/all.js"></script>
        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/indexScript1.js"></script>
        <title>:.: Inicio</title>
    </head>
    <body>
        <div class="listaFactura">
            <c:if test="${pCarrito != null}">

                <div class="info row">
                    <div class="col-lg-4">
                        <h5>DATOS DE FACTURA</h5>
                        <span><b>Total Productos: </b>${pTotal}</span><br>
                        <span><b>Total Unidades: </b>${pUnidades}</span><br>
                        <span><b>Monto Total: </b>$ ${monto}</span><br>
                        <span><b>Forma de Pago: </b>Contado</span><br>
                    </div>
                    <div class="col-lg-4">
                        <img src="${pageContext.servletContext.contextPath}/img/Logo.png" alt="">
                    </div>            
                    <div class="col-lg-4">
                        <h5>DATOS DE COMPRADOR</h5>
                        <span><b>Identificador: </b>${Cliente}</span><br>
                        <c:if test="${titular != null}">
                            <span><b>Titular: </b>${titular}</span><br>    
                        </c:if>
                        <span><b>Contacto: </b>${contacto}</span>
                    </div>
                </div>

                <c:forEach var="iteracion" items="${pCarrito}">
                    <div class="row">
                        <div class="col-lg-3" style="text-align: center;">
                            <img src="${pageContext.servletContext.contextPath}/ImgProductos${iteracion.rutaImg}">
                        </div>
                        <div class="col-lg-5">
                            <span><b>Nombre Producto | </b>${iteracion.nombreProducto}</span><br><br>
                            <p class="parrafo" style="text-align: justify;">${iteracion.descripcionProducto}</p>
                        </div>            
                        <div class="col-lg-4" style="text-align: center;">
                            <br>
                            <span><b>Precio Unidad | ${iteracion.precioComun} </b></span><br>
                            <span><b>Numero de Unidades | ${iteracion.unidades} </b></span><br>
                            <span><b>Sub Total | ${iteracion.subTotal} </b></span><br>
                        </div>            
                    </div>                
                </c:forEach>
                <div class="row opciones">
                    <a href="${pageContext.servletContext.contextPath}/Compras?accion=facturar&id=${Cliente}">Confirmar</a>
                    <a href="${pageContext.servletContext.contextPath}">Cancelar</a>
                </div>                

            </c:if>

            <c:if test="${pCarrito == null}">
                <span style="margin: 10px 0px; display: block; text-align: center;">No Hay Productos</span>
            </c:if>
        </div>
    </body>
</html>