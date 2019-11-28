'use strict';
$(function () {
  $('.inputfile').each(function () {
    var $input = $(this);
    $input.on('change', function (e) {
      var fileName = '';
      if (e.target.value){
        fileName = e.target.value.split('\\').pop();
      }
      if (fileName){
        var $fileName = $('#file_name');
        $fileName.html(fileName);
      } else {
        $fileName.html('');
      }
    });
  });
});

// Obtener referencia al input y a la imagen
const $seleccionArchivos = document.querySelector("#choose_file"),
$imagenPrevisualizacion = document.querySelector("#imgPreView");

// Escuchar cuando cambie
$seleccionArchivos.addEventListener("change", () => {
  // Los archivos seleccionados, pueden ser muchos o uno
  const archivos = $seleccionArchivos.files;
  // Si no hay archivos salimos de la función y quitamos la imagen
  if (!archivos || !archivos.length) {
    $imagenPrevisualizacion.src = "";
    return;
  }
  // Ahora tomamos el primer archivo, el cual vamos a previsualizar
  const primerArchivo = archivos[0];
  // Lo convertimos a un objeto de tipo objectURL
  const objectURL = URL.createObjectURL(primerArchivo);
  // Y a la fuente de la imagen le ponemos el objectURL
  $imagenPrevisualizacion.src = objectURL;
});

