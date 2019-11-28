<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/tableStyles.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/manager.css">

        <title>Marcas</title>
    </head>
    <body>
        <div id="contenido" style="padding: 10px">
            <h1>Listado de Aviones</h1>
            ${tabla}
            <script>
                function _Seleccionar_(row) {
                    var idmarca = row.cells[0].innerHTML;
                    var marca = row.cells[1].innerHTML;
                    window.opener.setDataMarca(idmarca, marca);
                    window.close();
                }
            </script>
        </div>
    </body>
</html>