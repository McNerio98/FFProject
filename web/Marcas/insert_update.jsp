
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="../ad_left.jsp" %>

<div class="contenedor">
    <h3 class="titulo">Llene los campos para la Marca</h3>

    <form name="form1" onsubmit="return validar();"
          action="${pageContext.servletContext.contextPath}/Marcas?accion=insertar_modificar"
          method="POST"
          enctype="multipart/form-data">        
        <table class="tblStyle2">
            <tr>
                <td class="col-sm-4"><label for="">Id Marca</label></td>
                <td class="col-sm-4"><input type="text" name="txtIdMarca" id="txtIdMarca" readonly="readonly" value="${m.idMarca}"></td>
            </tr>
            <tr>
                <td class="col-sm-4"><label for="">Nombre de la Marca</label></td>
                <td class="col-sm-4"><input type="text" name="txtMarca" id="txtMarca" value="${m.nombreMarca}"></td>
            </tr>
            <tr>
                <td class="col-sm-4"><label for="">Descripcion</label></td>
                <td class="col-sm-4"><textarea name="txaDescripcion" id="txaDescripcion" cols="30" rows="7">${m.descripcion}</textarea></td>
            </tr>                                
            <tr>
                <td class="col-sm-4"><label for="">Presentacion</label></td>
                <td class="col-sm-4"><label for="choose_file" style="cursor: pointer;"><span id="file_name" >...Clik Aqui para subir imagen</span></label></td>
            </tr>                    
            <tr>
                <td class="col-sm-4"><label for="">Vista Previa</label></td>
                <td class="col-sm-4">
                    <c:if test="${m != null}">
                        <img src="${pageContext.servletContext.contextPath}/Marcas/imgMarcas${m.rutaImg}" alt="" id="imgPreView">
                    </c:if>
                    <c:if test="${m == null}">
                        <img src="${pageContext.servletContext.contextPath}/img/preview.png" alt="" id="imgPreView">
                    </c:if>                    
                </td>
            </tr>
        </table>
            <input type="file" name="PartFile" id="choose_file" class="inputfile" style="display:none">
        <input type="hidden" name="rutaImgCargada" value="${m.rutaImg}" id="auxPathImg">
        <div class="opciones2 mgtop-20">
            <input type="submit" value="Enviar">
            <input type="button" value="Regresar" onclick="javascript: return window.history.back()">
        </div>
    </form>
</div>

<script src="${pageContext.servletContext.contextPath}/js/customerInputFile.js"></script>


<script>

    function validar() {
        var campoMarca = document.getElementById('txtMarca');
        var campoDescripcion = document.getElementById('txaDescripcion');
        var imgMarca = document.querySelector("#choose_file");
        var auxPath = document.querySelector("#auxPathImg");
        
        const archivos = imgMarca.files;


        if (campoMarca.value.length == 0) {
            campoMarca.focus();
            alert("Ingrese el Nombre de la Marca");
            return false;
        } else if (campoDescripcion.value.length == 0) {
            campoDescripcion.focus();
            alert("Debe Porporcionar la Descripcion de la Marca");
            return false;
        }
        else if(!(auxPath.value.length > 0)){
            if(!archivos[0]){
                alert("Debe Cargar una Imagen");
                return false;                
            }
        }

        return true;
    }

</script>

<%@include file="../ad_rigth.jsp" %>