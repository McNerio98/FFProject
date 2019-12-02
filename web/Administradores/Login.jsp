<%-- 
    Document   : loginAdministrador
    Created on : 22-oct-2019, 14:31:19
    Author     : ZEUS
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% HttpSession sesion = request.getSession();%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>:.: Iniciar Sesion</title>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/login.css">
    </head>
    <body>
        <div class="contenedor">
            <div class="loginPnl row">
                <div class="col-sm-5 banner">
                    <img src="${pageContext.servletContext.contextPath}/img/SubLogo.png" alt="">
                </div>
                <div class="col-sm-7 datos">
                    <span class="color2">Iniciar Sesion con tu cuenta</span>
                    <c:if test="${error!=null}">
                        <c:if test="${error==2}">
                            <p><strong style="color: darkred; font-size: 80%;">Usuario y/o contraseña incorrectos</strong></p>
                        </c:if>
                    </c:if>

                    <form action="Manager" method="POST">
                        <label class="mgtop-20" for="">Nombre de Usuario</label>
                        <input type="text" name="txtUsuario">
                        <label class="mgtop-20" for="">Contraseña</label>
                        <input type="password" name="txtClave">
                        <div class="mgtop-20">
                            <input type="checkbox" name="" id="recordarClave">
                            <label for="recordarClave">Recordar Contraseña</label>
                        </div>
                        <input type="submit" value="Ingresar" class="mgtop-20">
                    </form>
                </div>
            </div>
            <span class="color2 mgtop-20">&copy; COPYRIGHT 2019, FAST&FORCE | <a href="" class="color1">Terminos</a></span>
        </div>
    </body>
</html>
