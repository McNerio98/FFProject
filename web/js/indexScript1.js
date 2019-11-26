$(document).ready(main);

function main(){
    $("#menu").click(function(){
        $('nav').fadeIn(100,function(){
            $('nav ul').animate({
                left: '0'
            },300)
        });
    })

    $('#closeMenu span').click(function(){
        $('nav ul').animate({
            left: '-70%'
        },300,function(){
            $('nav').fadeOut(100);
        })
    })

    function closeModalCar(){
        $('.modal-Car').slideUp(300,function(){
            $('.modal-Car-Screen').fadeOut(400);
        })
    }

    $('.title #close').click(closeModalCar);

    $('.layout2 .elem1 .icon').click(function(){
        $('.modal-Car-Screen').fadeIn(300,function(){
            $('.modal-Car').slideDown(300);
        });

        $('.modal-Car-Screen').css("display",'flex');

    })
}

