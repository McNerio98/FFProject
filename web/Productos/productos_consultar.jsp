
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../ad_left.jsp" %>

<script>
    function abrirVentana(URL) {
        window.open(URL, "ventana1", "width=700,height=400,scrollbars=YES,statusbar=YES,top=150,left=300")
    }
    

</script>

<div class="contenedor">
    <h3 class="titulo">Maestro/Detalle Producto</h3>
    
    <c:if test="${resultado!=null}">
        <c:if test="${resultado==1}">
            <p style="color:darkgreen"><strong>Operación realizada correctamente.</strong></p>
        </c:if>
        <c:if test="${resultado==0}">
            <p style="color:darkred"><strong>La operación no se realizó.</strong></p>
        </c:if>
    </c:if>
            
    <form method="post" class="master1"
           action="${pageContext.servletContext.contextPath}/Productos"
           onsubmit="return validar();"
           enctype="multipart/form-data">
        <div>
            <h4>Datos Generales de Producto</h4>
            <table>
                <tr>
                    <td class="col-lg-2">Id Producto</td>
                    <td class="col-lg-4"><input type="text" placeholder="AUTOMATICO" id="txtIdProducto" name="txtIdProducto" readonly="readonly" value="${p.idProducto}"></td>
                    <td class="col-lg-2">Marca</td>
                    <td class="col-lg-4">
                        <input type="text" name="txtIdMarca" id="txtIdMarca" size="3" readonly="readonly" value="${ma.idMarca}">
                        <input type="text" name="txtMarca" id="txtMarca" readonly="readonly" value="${ma.nombreMarca}">
                        <input type="button" value="..."
                               onclick="abrirVentana('${pageContext.servletContext.contextPath}/Productos?accion=listado_marcas');">
                    </td>                                                                                
                </tr>
                <tr>
                    <td class="col-lg-2">Nombre Producto</td>
                    <td class="col-lg-4"><input type="text" name="txtNombre" id="txtNombre" value="${p.nombreProducto}"></td>
                    <td class="col-lg-2">Sub Categoria</td>
                    <td class="col-lg-4">
                        <input type="text" name="txtIdSubCategoria" id="txtIdSubCategoria" size="3" readonly="readonly" value="${sc.idSubCategoria}">
                        <input type="text" name="txtSubCategoria" id="txtSubCategoria" readonly="readonly" value="${sc.nombreSubCategoria}">
                        <input type="button" value="..."
                               onclick="abrirVentana('${pageContext.servletContext.contextPath}/Productos?accion=listado_subcategorias');">
                    </td>                                                                                                          
                </tr>
                <tr>
                    <td colspan="2" class="col-lg-6">Descripcion</td>
                    <td colspan="2" class="col-lg-6" ><label for="choose_file" style="cursor: pointer; font-weight: bold;"><span id="file_name">Click Subir Presentacion...</span></label></td>                                                                         
                </tr>                                              
                <tr>
                    <td colspan="2" class="col-lg-6"><textarea name="txtDescripcion" id="txtDescripcion" cols="40" rows="5">${p.descripcionProducto}</textarea></td>
                    <td colspan="2" class="col-lg-6">
                        <div style="background-color: #dddddd; text-align: center;">
                                 <c:if test="${p != null}">
                                        <img src="${pageContext.servletContext.contextPath}/ImgProductos${p.rutaImg}" class="imgPresentacion" id="imgPreView">
                                  </c:if>
                                  <c:if test="${p == null}">
                                            <img src="${pageContext.servletContext.contextPath}/img/preview2.png" class="imgPresentacion" id="imgPreView">
                                  </c:if>                           
                        </div>
                    </td>                                                                         
                </tr>                                                                     
            </table>
            <input type="file" name="PartFile" id="choose_file" class="inputfile" style="display:none">
            <input type="hidden" name="rutaImgCargada" value="${p.rutaImg}" id="auxPathImg">
            
        </div>              
        <div>
            <h4>Precio de Producto</h4>
            <table>
                <tr>
                    <td class="col-lg-2">Precio Unidad</td>
                    <td class="col-lg-4">$ <input type="text" name="txtPrecio1" pattern="[0.0-9]+" id="txtPrecio1" value="${p.precioComun}"></td>
                    <td class="col-lg-2">Precio Mayorista</td>
                    <td class="col-lg-4">$ <input type="text" name="txtPrecio2" pattern="[0.0-9]+" id="txtPrecio2" value="${p.precioMayorista}"></td>                                               
                </tr>
                <tr>
                    <td colspan="2" class="col-lg-6"></td>
                    <td colspan="2" class="col-lg-6 opciones"><input type="submit" value="Aceptar" id="btnEnviar"></td>                                                                         
                </tr>                                               
            </table>    
        </div>               
    </form>
    <br><br>
    
    
    ${tabla}
    
    <script src="${pageContext.servletContext.contextPath}/js/customerInputFile.js"></script>
    
    <script>
        
        /*var idProducto = document.getElementById('txtIdProducto');
        var elementoEnlace = document.getElementById('urlBuscar');
        var btnEnviar = document.getElementById('btnEnviar');
        idProducto.onfocus = function(){
            elementoEnlace.style.display = 'inline';
            btnEnviar.style.display = 'none';
        }
        idProducto.onblur = function(){
            if(this.value.length == 0){
                elementoEnlace.style.display = 'none';
                btnEnviar.disabled = false;
                btnEnviar.style.display = 'block';
            }
        }*/
        
        
        function setDataMarca(idmarca, marca) {
            document.getElementById("txtIdMarca").value = idmarca;
            document.getElementById("txtMarca").value = marca;
        }
        
        function setDataSubCat(idCampo, campo) {
            document.getElementById("txtIdSubCategoria").value = idCampo;
            document.getElementById("txtSubCategoria").value = campo;
        }
        
        function validar(){
            var campoNombre = document.getElementById('txtNombre');
            var campoMarca = document.getElementById('txtIdMarca');
             var campoSC = document.getElementById('txtIdSubCategoria');
             var campoDes = document.getElementById('txtDescripcion');
             var precioA = document.getElementById('txtPrecio1');
             var precioB = document.getElementById('txtPrecio2');
             
             var imgMarca = document.querySelector("#choose_file");
             var auxPath = document.querySelector("#auxPathImg");
             
             const archivos = imgMarca.files;
             
                if(campoNombre.value.length == 0){
                    campoNombre.focus();
                    alert("Selecione el Nombre");
                    return false;
                }else if (campoMarca.value.length == 0) {
                    alert("Selecione una Marca");
                    return false;
                } else if (campoSC.value.length == 0) {
                    alert("Selecione la Sub Categoria");
                    return false;
                }else if(campoDes.value.length == 0){
                    campoDes.focus();
                    alert("Debe Porporcionar una Descripcion");
                    return false;                    
                } else if(precioA.value.length == 0){
                    precioA.focus();
                    alert("Ingrese Precio Unidad");
                    return false;
                }else if(precioB.value.length == 0){
                    precioB.focus();
                    alert("Ingrese Precio Mayorista");
                    return false;
                }else if(!(auxPath.value.length > 0)){
                    if(!archivos[0]){
                        alert("Debe Cargar una Imagen");
                        return false;                
                    }
                }  
        }
    
  </script>    


</div>

<%@include file="../ad_rigth.jsp" %>