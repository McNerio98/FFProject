<%@include file="_top.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<h3>PRODUCTOS POPULARES</h3>

${listado}

<%@include file="_modalWindows.jsp"%>
<%@include file="carrito.jsp"%>
<%@include file="_down.jsp"%>

