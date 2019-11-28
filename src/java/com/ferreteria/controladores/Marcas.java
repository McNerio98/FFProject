/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.controladores;

import com.ferreteria.conexion.Conexion;
import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.Marca;
import com.ferreteria.operaciones.Operaciones;
import com.ferreteria.utilerias.Tabla;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
public class Marcas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                String sql = "";
                if (request.getParameter("txtBusqueda") != null) {
                    sql = "select * from Marca where NombreMarca like ?";
                } else {
                    sql = "select * from Marca";
                }
                String[][] mrcas = null; //En esta variable se guardaran los datos que se pararan para modelar la tabla 
                if (request.getParameter("txtBusqueda") != null) {
                    List<Object> params = new ArrayList<>();
                    params.add("%" + request.getParameter("txtBusqueda").toString() + "%");
                    mrcas = Operaciones.consultar(sql, params);
                } else {
                    mrcas = Operaciones.consultar(sql, null);
                }
//declaracion de cabeceras a usar en la tabla HTML
                String[] cabeceras = new String[]{
                    "Id Marca",
                    "Nombre Marca",
                    "Presentacion",
                    "Descripcion"
                };
//variable de tipo Tabla para generar la Tabla HTML
                Tabla tab = new Tabla(mrcas, //array que contiene los datos
                        "100%", //ancho de la tabla px | %
                        Tabla.STYLE.TABLE01, //estilo de la tabla
                        Tabla.ALIGN.LEFT, // alineacion de la tabla
                        cabeceras); //array con las cabeceras de la tabla
//boton eliminar
                tab.setEliminable(true);
//boton actualizar
                tab.setModificable(true);
//url del proyecto
                tab.setPageContext(request.getContextPath());
//pagina encargada de eliminar
                tab.setPaginaEliminable("/Marcas?accion=eliminar");
//pagina encargada de actualizacion
                tab.setPaginaModificable("/Marcas?accion=modificar");
//pagina encargada de seleccion para operaciones
                tab.setPaginaSeleccionable("/Marcas?accion=modificar");
//icono para modificar y eliminar
                tab.setRutaImagenes("/Marcas/imgMarcas");
                tab.setIconoModificable("/img/update-icon.png");
                tab.setIconoEliminable("/img/delete-icon.png");
//columnas seleccionables
                //tab.setColumnasSeleccionables(new int[]{1});
                tab.setColumnasImagenes(new int[]{2});
//pie de tabla
                tab.setPie("Resultado Marcas");
//imprime la tabla en pantalla
                String tabla01 = "No hay datos";
                if (mrcas != null) {
                    tabla01 = tab.getTabla();
                }
                request.setAttribute("tabla", tabla01);
                request.setAttribute("valor", request.getParameter("txtBusqueda"));
                request.getRequestDispatcher("Marcas/marcas_consultar.jsp").forward(request, response);
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (accion.equals("insertar")) {
            request.getRequestDispatcher("Marcas/insert_update.jsp").forward(request, response);
        } else if (accion.equals("modificar")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                Marca m = Operaciones.get(Integer.parseInt(request.getParameter("id")), new Marca());
                request.setAttribute("m", m);
                Operaciones.commit();
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            request.getRequestDispatcher("Marcas/insert_update.jsp").forward(request, response);
        } else if (accion.equals("eliminar")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                Marca m = Operaciones.eliminar(Integer.parseInt(request.getParameter("id")), new Marca());

                if (m.getIdMarca() != 0) {
                    request.getSession().setAttribute("resultado", 1);
                } else {
                    request.getSession().setAttribute("resultado", 0);
                }
                Operaciones.commit();
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex1);
                }
                request.getSession().setAttribute("resultado", 0);
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            response.sendRedirect(request.getContextPath() + "/Marcas");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        switch (accion) {
            case "insertar_modificar": {
                String idMarca = request.getParameter("txtIdMarca");
                String marca = request.getParameter("txtMarca");
                String descrip = request.getParameter("txaDescripcion");
                Part imgPart = request.getPart("PartFile");
                String auxImgCargada = request.getParameter("rutaImgCargada"); //Contiene la ruta de la imagen que se cargo en update
                String nombreImg = "";
                String rutaRelativaAppB = getServletConfig().getServletContext().getRealPath("/");

                try {
                    Conexion conn = new ConexionPool();
                    conn.conectar();
                    Operaciones.abrirConexion(conn);
                    Operaciones.iniciarTransaccion();
                   

                    if (idMarca != null && !idMarca.equals("")) {
                        //auxImgCargada es el inpu de tipo hidden que contiene la ruta, aqui ya no se sube el archivo porque ya esta
                        
                        if(imgPart.getSize() > 0 ){
                            //Se cambio la imagen por otra
                            InputStream flujoEntrada = imgPart.getInputStream(); //Siempre traera algo en insertar
                            nombreImg = auxImgCargada;
                            File f = new File(rutaRelativaAppB + "/Marcas/imgMarcas/" + nombreImg);
                            FileOutputStream ous = new FileOutputStream(f);
                            int dato = flujoEntrada.read();
                            while (dato != -1) {
                                ous.write(dato);
                                dato = flujoEntrada.read();
                            }
                            ous.close();
                            flujoEntrada.close();                            
                        }
                        
                        
                        Marca m = new Marca(Integer.parseInt(idMarca), marca, auxImgCargada, descrip);
                        m = Operaciones.actualizar(m.getIdMarca(), m);
                        if (m.getIdMarca() != 0) { //Al obtener el id del pais, si esta diferente de 0 entonces si hay un registro
                            request.getSession().setAttribute("resultado", 1);
                        } else {
                            request.getSession().setAttribute("resultado", 0);
                        }
                    } else {//Si esta en blanco el campo del id entonces se procedera a insertar un nuevo registro
                        
                        //Se boliga a subir un archivo al usuario
                        //Se sabe que seleciono un archivo
                        InputStream flujoEntrada = imgPart.getInputStream(); //Siempre traera algo en insertar
                        nombreImg = "/" + nameGenerated()+".png";
                        File f = new File(rutaRelativaAppB + "/Marcas/imgMarcas/" + nombreImg);
                        FileOutputStream ous = new FileOutputStream(f);
                        int dato = flujoEntrada.read();
                        while (dato != -1) {
                            ous.write(dato);
                            dato = flujoEntrada.read();
                        }
                        ous.close();
                        flujoEntrada.close(); 
                        
                        Marca m = new Marca();
                        m.setNombreMarca(marca);
                        m.setRutaImg(nombreImg);
                        m.setDescripcion(descrip);

                        m = Operaciones.insertar(m);
                        if (m.getIdMarca() != 0) {
                            request.getSession().setAttribute("resultado", 1);
                        } else {
                            request.getSession().setAttribute("resultado", 0);
                        }
                    }
                    Operaciones.commit();
                } catch (Exception ex) {
                    try {
                        Operaciones.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    request.getSession().setAttribute("resultado", 2);
                    ex.printStackTrace();
                } finally {
                    try {
                        Operaciones.cerrarConexion();
                    } catch (SQLException ex) {
                        Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                response.sendRedirect(request.getContextPath() + "/Marcas");
                break;
            }
            case "eliminar": {
                break;
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
