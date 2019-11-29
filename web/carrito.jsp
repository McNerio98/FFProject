<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal-Car-Screen">
    <div class="modal-Car">
        <div class="header">
            <div class="title">
                <span>DESCRIPCION DE LA FACTURA</span>
                <span id="close"><i class="fas fa-times"></i></span>
            </div>
            <div class="description">

            </div>
        </div>
        <div class="list">

            <c:if test="${pCarrito != null}">
                <c:forEach var="iteracion" items="${pCarrito}">
                    <div class="row">
                        <div class="pictureProd">
                            <img src="${pageContext.servletContext.contextPath}/ImgProductos${iteracion.rutaImg}">
                        </div>
                        <div class="descriptionProd">
                            <a href="${pageContext.servletContext.contextPath}View?id=${iteracion.idProducto}">${iteracion.nombreProducto}</a>
                            <p class="parrafo">${iteracion.descripcionProducto}</p>
                            <div class="opcProduc row">
                                <span class="col-sm-4">P.U: $<span class="bold">${iteracion.precioComun}</span></span>
                                <span class="col-sm-4">
                                    Cantidad: <span class="bold">${iteracion.unidades}</span>
                                </span>
                                <input type="hidden" name="txtIdProducto" id="txtIdProducto">
                                <span class="col-sm-4">Subtotal: $ <span class="bold">${iteracion.subTotal}</span> </span>
                            </div>
                        </div>
                    </div>                       
                </c:forEach>                
            </c:if>
            <c:if test="${pCarrito == null}">
                <span style="margin: 10px 0px; display: block; text-align: center;">No Hay Productos</span>
            </c:if>
        </div>
        <div class="opciones">
            <a href="${pageContext.servletContext.contextPath}/Compras">Facturar</a>
            <a href="#">Continuar</a>
        </div>
    </div>
</div>