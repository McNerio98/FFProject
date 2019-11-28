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
        <link rel="stylesheet" href="css/estiloIndex.css">
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
                <li><a href=""> <i class="fas fa-briefcase"></i> Inicio</a></li>
                <li><a href=""> <i class="fas fa-tools"></i> Productos</a></li>
                <li><a href=""> <i class="fas fa-money-bill-wave"></i> Ofertas</a></li>
                <li><a href=""><i class="fas fa-cubes"></i> Bloger</a></li>
                <li><a href=""> <i class="fas fa-phone-alt"></i> Contactenos</a></li>
            </ul>
        </nav>  
        <section class="row">
            <div class="presentacion">

            </div>
            <div class="row subSeccion1">
                <div class="col-lg-6 col-sm-12"  id="tenue1">
                    <div>
                        <div class="col-sm-5">
                            <img src="products/0.png" alt="">
                        </div>
                        <div class="col-sm-7">
                            <p class="description">
                                Black & Decker SS12C 12-Volt Cordless Drill-Driver 
                            </p>
                            <p> From $ 110.00 $ 135.00 </p>
                            <a href="#">ADD TO CART</a>
                        </div>
                    </div>
                    <div class="pagination1">
                        <div></div>
                        <div></div>
                        <div></div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6" id="tenue2">
                    <i class="fas fa-dolly icon"></i>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
                </div>
                <div class="col-lg-3 col-md-6" id="tenue3">
                    <i class="fas fa-business-time icon"></i>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>                
                </div>
            </div>
        </section>