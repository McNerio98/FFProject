<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../ad_left.jsp"%>


<div class="contenedor fieldUser">
    <h3 class="titulo">Registrar Administrador</h3>
    <form name="form1" onsubmit="return validar();"
          action="${pageContext.servletContext.contextPath}/Administradores?accion=insertar_modificar&Operacion=${Operacion}"
          method="POST"
          enctype="multipart/form-data">
        <div class="col-sm-6">
            <table>
                <tr>
                    <td class="icon"><i class="fas fa-user-tag"></i></td>
                    <td class="in"><input type="text" name="txtUsuario" id="txtUsuario" placeholder="Usuario" value="${ad.usuario}"></td>
                </tr>
                <tr>
                    <td class="icon"><i class="fas fa-id-card-alt icon"></i></td>
                    <td class="in"><input type="text" name="txtNombre" id="txtNombre" placeholder="Nombre" value="${ad.nombre}"></td>
                </tr>
                <tr>
                    <td class="icon"><i class="fas fa-id-card-alt icon"></i></td>
                    <td class="in"><input type="text" name="txtApellido" id="txtApellido" placeholder="Apellido" value="${ad.apellido}"></td>
                </tr>
                <tr>
                    <td class="icon"><i class="fas fa-key icon"></i></td>
                    <td class="in"><input type="password" name="txtClave" id="txtClave" placeholder="Contraseña"></td>
                </tr>                                                                        
                <tr>
                    <td class="icon"><i class="fas fa-redo icon"></i></td>
                    <td class="in"><input type="password" name="" id="txtClave2" placeholder="Repetir Contraseña"></td>
                </tr>                                                                                                
            </table>
        </div>
        <div class="col-sm-6">
            <table>
                <tr>
                    <td class="icon"><i class="fas fa-envelope-open"></i></td>
                    <td class="in"><input type="text" name="txtEmail" id="txtEmail" placeholder="Correo Electronico" value="${ad.correo}"></td>
                </tr>
                <tr>
                    <td class="icon"><i class="fas fa-phone-alt"></i></td>
                    <td class="in"><input type="text" name="txtTelefono" id="txtTelefono" placeholder="Telefono Contacto" value="${ad.telefono}"></td>
                </tr>
                <tr>
                    <td class="icon"><i class="fas fa-user-tie"></i></td>
                    <td>
                        <select name="valSelectRol" id="selectRol">
                            <option value="0">---SELECIONAR ROL---</option>
                            <c:forEach var="Iterador" items="${ListRoles}">
                                <option value="${Iterador.idRol}">${Iterador.rol}</option>
                            </c:forEach>                            
                        </select>
                    </td>
                </tr>                                                  
                <tr>
                    <td class="icon up"><label for="choose_file"><i class="fas fa-arrow-circle-up"></i></label></td>
                    <td class="in tenue"><label for="choose_file"  class="nameFile"><span id="file_name">CLICK PARA SUBIR IMAGEN</span></label></td>
                </tr>                                                 
                <tr>
                    <td colspan="2">
                        <div class="preview">
                            <c:if test="${ad != null}">
                                <img src="Administradores/Photos${ad.photoPath}" alt="" id="imgPreView">
                            </c:if>
                            <c:if test="${ad == null}">
                                <img src="Administradores/Photos/avatar.jpg" alt="" id="imgPreView">
                            </c:if>
                            
                        </div>
                    </td>
                </tr>

            </table>
            <input type="file" name="archivoPart" id="choose_file" class="inputfile">
            <div class="opciones">
                <input type="submit" value="Aceptar">
                <input type="button" value="Regresar" onclick="javascript: return window.history.back()">                        
            </div>
        </div>
    </form>
</div>   



<script src="${pageContext.servletContext.contextPath}/js/customerInputFile.js"></script>

<script>
        document.getElementsByName('valSelectRol')[0].value = ${ad.idRol};

    function validar(){
        var usuario = document.getElementById('txtUsuario');
        var nombre = document.getElementById('txtNombre');
        var apellido = document.getElementById('txtApellido');
        var clave = document.getElementById('txtClave');
        var clave2 = document.getElementById('txtClave2');
        var email = document.getElementById('txtEmail');
        var tel = document.getElementById('txtTelefono');
        var rol = document.getElementById('selectRol');
        
        if (usuario.value.length == 0) {
            usuario.focus();
            alert("Ingrese el Usuario");
            return false;
        }else if(nombre.value.length == 0){
            nombre.focus();
            alert("Digite nombre el Nombre");
            return false;            
        }else if(apellido.value.length == 0){
            apellido.focus();
            alert("Digite el Apellido");
            return false;            
        }else if(clave.value.length == 0){
            clave.focus();
            alert("Digite la Clave");
            return false;            
        }else if(clave2.value.length == 0){
            clave2.focus();
            alert("Debe repetir clave");
            return false;            
        }else if(email.value.length == 0){
            email.focus();
            alert("Digite el Correo electronico");
            return false;            
        }else if(tel.value.length == 0){
            tel.focus();
            alert("Digite el Telefono");
            return false;            
        }else if(rol.value == 0){
            rol.focus();
            alert("Debe Selecionar un rol");
            return false;
        }else if(clave.value != clave2.value){
            alert("Las contrasenas no coinciden");
            return false;
        }
            
        return true;        
    }
        
</script>


<%@include file="../ad_rigth.jsp" %>
