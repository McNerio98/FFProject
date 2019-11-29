<%-- 
    Document   : _top
    Created on : 27-oct-2019, 14:18:09
    Author     : ZEUS
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <header class="row">
            <div class=" perks">
                <div class="col-md-5">
                    <h1>Ferreteria Fast & Force</h1>
                    <p>Aprovecha todas las ventajas que te ofrecemos en esta temporada</p>
                    <a href="">Comprar Ahora</a>
                </div>
                <div class="col-md-5 layout1">
                    <div class="circle">
                        <span>0</span>
                        <p>dias</p>
                    </div>
                    <div class="circle">
                        <span>0</span>
                        <p>dias</p>
                    </div>
                    <div class="circle">
                        <span>0</span>
                        <p>dias</p>
                    </div>
                    <div class="circle">
                        <span>0</span>
                        <p>dias</p>
                    </div>
                </div>
            </div>

            <div class="layout2" >
                <div class="icon1">
                    <div class="AppDow">
                        <span><i class="fas fa-mobile-alt icon"></i></span>
                        <div>
                            <a href="">Descarga la APP</a>
                            <p>Compra desde tu mobil</p>
                        </div>
                    </div>
                    <span id="menu"><i class="fas fa-bars"></i></span>
                </div>


                <div class="logo">
                    <img src="img/Logo.png" alt="">
                </div>

                <div class="icon2">
                    <div class="elem1">
                        <div class="contenedor">
                            <div class="icon">
                                <span><i class="fas fa-shopping-cart"></i></span>
                            </div>
                            <div class="cantidad">
                                250
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </header>
        <nav class="row">
            <ul>
                <li id="closeMenu">Menu <span><i class="fas fa-times"></i></span></li>
                <li><a href="${pageContext.servletContext.contextPath}/ServletPrincipal?accion=1"> <i class="fas fa-briefcase"></i> Inicio</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/ServletPrincipal?accion=2"> <i class="fas fa-tools"></i> Productos</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/ServletPrincipal?accion=3"> <i class="fas fa-money-bill-wave"></i> Ofertas</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/ServletPrincipal?accion=4"><i class="fas fa-cubes"></i> Bloger</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/ServletPrincipal?accion=5"> <i class="fas fa-phone-alt"></i> Contactenos</a></li>
            </ul>
        </nav>