$(document).ready(function(){
    $('.encabezado #menu').click(function(){
        $('.pnlLeft').css('display','block');
    })
    $('.pnlLeft #closeMenu').click(function(){
        $('.pnlLeft').css('display','none');
    })
})