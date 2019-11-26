/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.controladores;

import com.ferreteria.conexion.Conexion;
import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.Categoria;
import com.ferreteria.operaciones.Operaciones;
import com.ferreteria.utilerias.Tabla;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author McNerio
 */
public class Categorias extends HttpServlet {

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
                    sql = "select * from Categoria where NombreCategoria like ?";
                } else {
                    sql = "select * from Categoria";
                }
                String[][] categorias = null; //En esta variable se guardaran los datos que se pararan para modelar la tabla 
                if (request.getParameter("txtBusqueda") != null) {
                    List<Object> params = new ArrayList<>();
                    params.add("%" + request.getParameter("txtBusqueda").toString() + "%");
                    categorias = Operaciones.consultar(sql, params);
                } else {
                    categorias = Operaciones.consultar(sql, null);
                }
//declaracion de cabeceras a usar en la tabla HTML
                String[] cabeceras = new String[]{
                    "Id Categoria",
                    "Nombre Categoria"
                };
//variable de tipo Tabla para generar la Tabla HTML
                Tabla tab = new Tabla(categorias, //array que contiene los datos
                        "75%", //ancho de la tabla px | %
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
                tab.setPaginaEliminable("/Categorias?accion=eliminar");
//pagina encargada de actualizacion
                tab.setPaginaModificable("/Categorias?accion=modificar");
//pagina encargada de seleccion para operaciones
                tab.setPaginaSeleccionable("/Categorias?accion=modificar");
//icono para modificar y eliminar
                tab.setIconoModificable("/img/update-icon.png");
                tab.setIconoEliminable("/img/delete-icon.png");
//columnas seleccionables
                tab.setColumnasSeleccionables(new int[]{1});
//pie de tabla
                tab.setPie("Resultado Categorias");
//imprime la tabla en pantalla
                String tabla01 = "No hay datos";
                if (categorias != null) {
                    tabla01 = tab.getTabla();
                }
                request.setAttribute("tabla", tabla01);
                request.setAttribute("valor", request.getParameter("txtBusqueda"));
                request.getRequestDispatcher("Categorias/categorias_consultar.jsp").forward(request, response);
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (accion.equals("insertar")) {
            request.getRequestDispatcher("Categorias/insert_update.jsp").forward(request, response);
        } else if (accion.equals("modificar")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                Categoria c = Operaciones.get(Integer.parseInt(request.getParameter("id")), new Categoria());
                request.setAttribute("categ", c);
                Operaciones.commit();
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            request.getRequestDispatcher("Categorias/insert_update.jsp").forward(request, response);
        } else if (accion.equals("eliminar")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                Categoria c = Operaciones.eliminar(Integer.parseInt(request.getParameter("id")), new Categoria());
                
                if (c.getIdCategoria() != 0) {
                    request.getSession().setAttribute("resultado", 1);
                } else {
                    request.getSession().setAttribute("resultado", 0);
                }
                Operaciones.commit();
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex1);
                }
                request.getSession().setAttribute("resultado", 0);
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            response.sendRedirect(request.getContextPath() + "/Categorias");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "insertar_modificar": {
                String idCategoria = request.getParameter("txtIdCategoria");
                String categoria = request.getParameter("txtCategoria");

                try {
                    Conexion conn = new ConexionPool();
                    conn.conectar();
                    Operaciones.abrirConexion(conn);
                    Operaciones.iniciarTransaccion();

                    if (idCategoria != null && !idCategoria.equals("")) {
                        Categoria c = new Categoria(Integer.parseInt(idCategoria), categoria);

                        c = Operaciones.actualizar(c.getIdCategoria(), c);
                        if (c.getIdCategoria() != 0) { //Al obtener el id del pais, si esta diferente de 0 entonces si hay un registro
                            request.getSession().setAttribute("resultado", 1);
                        } else {
                            request.getSession().setAttribute("resultado", 0);
                        }
                    } else {//Si esta en blanco el campo del id entonces se procedera a insertar un nuevo registro 
                        Categoria c = new Categoria();
                        c.setNombreCategoria(categoria);
                        c = Operaciones.insertar(c);
                        if (c.getIdCategoria() != 0) {
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
                        Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    request.getSession().setAttribute("resultado", 2);
                    ex.printStackTrace();
                } finally {
                    try {
                        Operaciones.cerrarConexion();
                    } catch (SQLException ex) {
                        Logger.getLogger(Categorias.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                response.sendRedirect(request.getContextPath() + "/Categorias");
                break;
            }

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
