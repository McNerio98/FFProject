/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.controladores;

import com.ferreteria.conexion.Conexion;
import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.Marca;
import com.ferreteria.entidad.Producto;
import com.ferreteria.entidad.SubCategoria;
import com.ferreteria.operaciones.Operaciones;
import com.ferreteria.utilerias.Tabla;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author McNerio
 */
@MultipartConfig
public class Productos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            if (request.getSession().getAttribute("resultado") != null) {
                request.setAttribute("resultado", request.getSession().getAttribute("resultado"));
                request.getSession().removeAttribute("resultado");
            }
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();

                String sql = "select p.idProducto,p.NombreProducto,p.PrecioComun,p.PrecioMayorista, \n"
                        + "m.NombreMarca,sc.NombreSubCategoria \n"
                        + "from Producto as p \n"
                        + "inner join Marca as m on p.idMarca = m.idMarca \n"
                        + "inner join SubCategoria as sc on p.idSubCategoria = sc.idSubCategoria";

                String[][] productos = null;
                List<Object> param = new ArrayList();
                if (request.getParameter("idProducto") != null) {
                    param = new ArrayList();
                    param.add(request.getParameter("idProducto"));
                    //request.setAttribute("idorigen", request.getParameter("idorigen"));
                    //request.setAttribute("origen", request.getParameter("origen"));
                    sql += " where p.idProducto = ?";
                    productos = Operaciones.consultar(sql, param);
                } else {
                    productos = Operaciones.consultar(sql, null);
                }
//declaracion de cabeceras a usar en la tabla HTML
                String[] cabeceras = new String[]{
                    "Id Producto",
                    "Producto",
                    "Precio Comun",
                    "Precio Mayoreo",
                    "Marca",
                    "Sub Categoria"
                };
//variable de tipo Tabla para generar la Tabla HTML
                Tabla tab = new Tabla(productos, //array que contiene los datos
                        "100%", //ancho de la tabla px | %
                        Tabla.STYLE.TABLE01, //estilo de la tabla
                        Tabla.ALIGN.CENTER, // alineacion de la tabla
                        cabeceras); //array con las cabeceras de la tabla

                tab.setEliminable(true);
                tab.setModificable(true);

                tab.setPageContext(request.getContextPath());
                tab.setSeleccionable(false);

                tab.setPaginaEliminable("/Productos?accion=eliminar");
                tab.setPaginaModificable("/Productos?accion=modificar");

                tab.setIconoModificable("/img/update-icon.png");
                tab.setIconoEliminable("/img/delete-icon.png");
                tab.setPaginaSeleccionable("/Productos?accion=modificar");

                tab.setPie("Resultado Productos");

                String tabla01 = "No hay datos";
                if (productos != null) {
                    tabla01 = tab.getTabla();
                }
                request.setAttribute("tabla", tabla01);
                //request.setAttribute("Operacion", "Agregar");
                request.getRequestDispatcher("Productos/productos_consultar.jsp").forward(request, response);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (accion.equals("listado_marcas")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();

                String sql = "select idMarca,NombreMarca,RutaImg from Marca";

                String[][] marcas = Operaciones.consultar(sql, null);

                String[] cabeceras = new String[]{
                    "Id Marca",
                    "Nombre",
                    "Presentacion"
                };

                Tabla tab = new Tabla(marcas, //array que contiene los datos
                        "100%", //ancho de la tabla px | %
                        Tabla.STYLE.TABLE01, //estilo de la tabla
                        Tabla.ALIGN.LEFT, // alineacion de la tabla
                        cabeceras); //array con las cabeceras de la tabla

                tab.setPageContext(request.getContextPath());
                tab.setFilaSeleccionable(true);
                tab.setMetodoFilaSeleccionable("_Seleccionar_");
                tab.setRutaImagenes("/Marcas/imgMarcas");

                tab.setColumnasImagenes(new int[]{2});

                tab.setPie("Resultado marcas");

                String tabla01 = "No hay datos";
                if (marcas != null) {
                    tabla01 = tab.getTabla();
                }
                request.setAttribute("tabla", tabla01);
                request.getRequestDispatcher("Productos/lista_marcas.jsp").forward(request,
                        response);
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (accion.equals("listado_subcategorias")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();

                String sql = "select sc.idSubCategoria, sc.NombreSubCategoria, c.NombreCategoria \n"
                        + "from SubCategoria as sc \n"
                        + "inner join Categoria as c on sc.idCategoria = c.idCategoria";

                String[][] sc = Operaciones.consultar(sql, null);

                String[] cabeceras = new String[]{
                    "Id SubCategoria",
                    "Sub Categoria",
                    "Categoria"
                };

                Tabla tab = new Tabla(sc, //array que contiene los datos
                        "100%", //ancho de la tabla px | %
                        Tabla.STYLE.TABLE01, //estilo de la tabla
                        Tabla.ALIGN.LEFT, // alineacion de la tabla
                        cabeceras); //array con las cabeceras de la tabla

                tab.setPageContext(request.getContextPath());
                tab.setFilaSeleccionable(true);
                tab.setMetodoFilaSeleccionable("_Seleccionar_");

                tab.setPie("Resultado SubCategorias");

                String tabla01 = "No hay datos";
                if (sc != null) {
                    tabla01 = tab.getTabla();
                }
                request.setAttribute("tabla", tabla01);
                request.getRequestDispatcher("Productos/lista_subcategorias.jsp").forward(request,
                        response);
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else if(accion.equals("modificar")){
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();

                Producto p = Operaciones.get(Integer.parseInt(request.getParameter("id")), new Producto());

                Marca ma = Operaciones.get(p.getIdMarca(), new Marca());
                SubCategoria sc = Operaciones.get(p.getIdSubCategoria(), new SubCategoria());


                request.setAttribute("p", p);
                request.setAttribute("ma", ma);
                request.setAttribute("sc", sc);


                Operaciones.commit();
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            request.getRequestDispatcher("Productos/productos_consultar.jsp").forward(request, response);
            
        }else if(accion.equals("eliminar")){
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                Producto p = Operaciones.eliminar(Integer.parseInt(request.getParameter("id")), new Producto());
                if (p.getIdProducto() != 0) {
                    request.getSession().setAttribute("resultado", 1);
                } else {
                    request.getSession().setAttribute("resultado", 0);
                }
                Operaciones.commit();
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex1);
                }
                request.getSession().setAttribute("resultado", 0);
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            response.sendRedirect(request.getContextPath() + "/Productos");            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idProducto = request.getParameter("txtIdProducto");
        String nombre = request.getParameter("txtNombre");
        String idMarca = request.getParameter("txtIdMarca");
        String idSc = request.getParameter("txtIdSubCategoria");
        String desc = request.getParameter("txtDescripcion");
        String priceA = request.getParameter("txtPrecio1");
        String priceB = request.getParameter("txtPrecio2");
        
        String auxImgCargada = request.getParameter("rutaImgCargada");
        
        Part imgPart = request.getPart("PartFile");
        
        String nombreImg = "";
        String rutaRelativaAppB = getServletConfig().getServletContext().getRealPath("/");        
        
        if (idProducto != null && !idProducto.equals("")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                Producto p = new Producto();
                p.setIdProducto(Integer.parseInt(idProducto));
                p.setNombreProducto(nombre);
                p.setDescripcionProducto(desc);
                p.setPrecioComun(BigDecimal.valueOf(Double.parseDouble(priceA)));
                p.setPrecioMayorista(BigDecimal.valueOf(Double.parseDouble(priceB)));
                
                        if(imgPart.getSize() > 0 ){
                            //Se cambio la imagen por otra
                            InputStream flujoEntrada = imgPart.getInputStream(); //Siempre traera algo en insertar
                            nombreImg = auxImgCargada;
                            File f = new File(rutaRelativaAppB + "/ImgProductos/" + nombreImg); //*** POSIBLE BUG
                            FileOutputStream ous = new FileOutputStream(f);
                            int dato = flujoEntrada.read();
                            while (dato != -1) {
                                ous.write(dato);
                                dato = flujoEntrada.read();
                            }
                            ous.close();
                            flujoEntrada.close();                            
                        }
                p.setRutaImg(auxImgCargada);
                p.setIdMarca(Integer.parseInt(idMarca));
                p.setIdSubCategoria(Integer.parseInt(idSc));
                        

                p = Operaciones.actualizar(p.getIdProducto(), p);
                if (p.getIdProducto() != 0) {
                    request.getSession().setAttribute("resultado", 1);
                } else {
                    request.getSession().setAttribute("resultado", 0);
                }
                Operaciones.commit();
                response.sendRedirect(request.getContextPath() + "/Productos?idProducto=" + p.getIdProducto());
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex1);
                }
                request.getSession().setAttribute("resultado", 2);
                ex.printStackTrace();
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                Producto p = new Producto();
                p.setNombreProducto(nombre);
                p.setDescripcionProducto(desc);
                p.setPrecioComun(BigDecimal.valueOf(Double.parseDouble(priceA)));
                p.setPrecioMayorista(BigDecimal.valueOf(Double.parseDouble(priceB)));
                
                
                        InputStream flujoEntrada = imgPart.getInputStream(); //Siempre traera algo en insertar
                        nombreImg = "/" + nameGenerated()+".png";
                        File f = new File(rutaRelativaAppB + "/ImgProductos/" + nombreImg);
                        FileOutputStream ous = new FileOutputStream(f);
                        int dato = flujoEntrada.read();
                        while (dato != -1) {
                            ous.write(dato);
                            dato = flujoEntrada.read();
                        }
                        ous.close();
                        flujoEntrada.close();                 
                
                p.setRutaImg(nombreImg);
                p.setIdMarca(Integer.parseInt(idMarca));
                p.setIdSubCategoria(Integer.parseInt(idSc));
                

                p = Operaciones.insertar(p);
                if (p.getIdProducto() != 0) {
                    request.getSession().setAttribute("resultado", 1);
                } else {
                    request.getSession().setAttribute("resultado", 0);
                }
                Operaciones.commit();
                response.sendRedirect(request.getContextPath() + "/Productos?idProducto=" + p.getIdProducto());
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex1);
                }
                request.getSession().setAttribute("resultado", 2);
                ex.printStackTrace();
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    protected String nameGenerated() {
        Date fechaActual = new Date();
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        
        String cod = "";
        
        String tiempo = formatoHora.format(fechaActual).toString();
        String fecha = formatoFecha.format(fechaActual).toString();
        cod = tiempo.replaceAll(":", "") + fecha.replaceAll("/","");        
        return cod;
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
