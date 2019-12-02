    <footer class="mgtop-20 row">
        <div class="contenedor row">
            <div class="col-sm-6 col-lg-3">
                <b>Contactenos</b><br>
                <span>Telefono</span><span>+ 503 245-8496</span><br>
                <span>Email</span><span>+ ferretriaff@outlook.es</span><br>
                <span>Instagram</span><span> FastNForce</span><br>
            </div>
            <div class="col-sm-6 col-lg-3">
                <b>Encuentranos en</b><br>
                <i class="icon fab fa-app-store-ios"></i>
                <i class="icon fas fa-audio-description"></i>
                <i class="icon fas fa-bell"></i><br>
                <i class="icon fas fa-camera-retro"></i>
                <i class="icon fab fa-amazon"></i>
            </div>
            <div class="col-sm-6 col-lg-3">
                    <b>Envianos tu sugerencia</b><br>
                    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit.</p>
            </div>
            <div class="col-sm-6 col-lg-3">
                <b>Nuestro Objetivo</b><br>
                <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Cupiditate, at, molestias reprehenderit suscipit, ut id provident amet in sequi nulla odio. Vero eveniet amet est, esse soluta blanditiis quasi eum?</p>
            </div>
        </div>
        <div class="pie"><p> © 2019 Aplicativo Web |Desarrollo de Software|</p></div>        
    </footer>    
</body>

    <script>
        $(document).ready(function(){
            $('.slcCantidad').change(function(e){
                let unidades = parseInt($(this).val());
                let padre = $(this).parent();
                let precio = parseFloat($(padre).children('#preciouni').val());
                let elemento = $(padre).children('#price');
                if(unidades != 0){
                    $(elemento).text("$ " + (unidades * precio).toFixed(2));
                }else{
                    $('.notificacion1.registrar').fadeIn();
                    $(this).val(1);
                }
                
            })

            var insertSatutus = $('#status').val();
            if(insertSatutus.length != 0){
                $('.notificacion1.confirmar').css('display','block');
                setTimeout(closeNot,2000);
            }

            var numComprados = $('#list').children('div').length;
            $('#cantidad').text(numComprados);
        })
    </script>
    
</html>