/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.controladores;

import com.ferreteria.conexion.Conexion;
import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.Categoria;
import com.ferreteria.entidad.SubCategoria;
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
 * @author ZEUS
 */
public class SubCategorias extends HttpServlet {

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
                    sql = "select sc.idSubCategoria,sc.NombreSubCategoria,c.NombreCategoria from SubCategoria sc, Categoria c where sc.idCategoria = c.idCategoria and sc.NombreSubCategoria like ?";
                } else {
                    sql = "select sc.idSubCategoria,sc.NombreSubCategoria,c.NombreCategoria from SubCategoria sc, Categoria c where sc.idCategoria = c.idCategoria";
                }
                String[][] subCat = null; //En esta variable se guardaran los datos que se pararan para modelar la tabla 
                if (request.getParameter("txtBusqueda") != null) {
                    List<Object> params = new ArrayList<>();
                    params.add("%" + request.getParameter("txtBusqueda").toString() + "%");
                    subCat = Operaciones.consultar(sql, params);
                } else {
                    subCat = Operaciones.consultar(sql, null);
                }
//declaracion de cabeceras a usar en la tabla HTML
                String[] cabeceras = new String[]{
                    "ID",
                    "Nombre SubCategoria",
                    "Categoria"
                };
//variable de tipo Tabla para generar la Tabla HTML
                Tabla tab = new Tabla(subCat, //array que contiene los datos
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
                tab.setPaginaEliminable("/SubCategorias?accion=eliminar");
//pagina encargada de actualizacion
                tab.setPaginaModificable("/SubCategorias?accion=modificar");
//pagina encargada de seleccion para operaciones
                tab.setPaginaSeleccionable("/SubCategorias?accion=modificar");
//icono para modificar y eliminar
                tab.setIconoModificable("/img/update-icon.png");
                tab.setIconoEliminable("/img/delete-icon.png");
//columnas seleccionables
                tab.setColumnasSeleccionables(new int[]{1});
//pie de tabla
                tab.setPie("Resultado SubCategorias");
//imprime la tabla en pantalla
                String tabla01 = "No hay datos";
                if (subCat != null) {
                    tabla01 = tab.getTabla();
                }
                request.setAttribute("tabla", tabla01);
                request.setAttribute("valor", request.getParameter("txtBusqueda"));
                request.getRequestDispatcher("SubCategorias/subcategorias_consultar.jsp").forward(request, response);
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(SubCategorias.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(SubCategorias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (accion.equals("insertar")) {
            request.setAttribute("ListCategoria", getAllCategorias());
            request.getRequestDispatcher("SubCategorias/insert_update.jsp").forward(request, response);
        } else if (accion.equals("modificar")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                SubCategoria c = Operaciones.get(Integer.parseInt(request.getParameter("id")), new SubCategoria());
                request.setAttribute("sc", c);
                Operaciones.commit();
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(SubCategorias.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(SubCategorias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            request.setAttribute("ListCategoria", getAllCategorias());
            request.getRequestDispatcher("SubCategorias/insert_update.jsp").forward(request, response);
        } else if (accion.equals("eliminar")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                SubCategoria c = Operaciones.eliminar(Integer.parseInt(request.getParameter("id")), new SubCategoria());

                if (c.getIdSubCategoria() != 0) {
                    request.getSession().setAttribute("resultado", 1);
                } else {
                    request.getSession().setAttribute("resultado", 0);
                }
                Operaciones.commit();
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(SubCategorias.class.getName()).log(Level.SEVERE, null, ex1);
                }
                request.getSession().setAttribute("resultado", 0);
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(SubCategorias.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            response.sendRedirect(request.getContextPath() + "/SubCategorias");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "insertar_modificar": {
                String idSub = request.getParameter("txtIdSubCategoria");
                String nombreSub = request.getParameter("txtSubCategoria");
                String idCategoria = request.getParameter("valIdCategoria");

                try {
                    Conexion conn = new ConexionPool();
                    conn.conectar();
                    Operaciones.abrirConexion(conn);
                    Operaciones.iniciarTransaccion();

                    if (idSub != null && !idSub.equals("")) {
                        SubCategoria sc = new SubCategoria(Integer.parseInt(idSub), nombreSub,Integer.parseInt(idCategoria));

                        sc = Operaciones.actualizar(sc.getIdCategoria(), sc);
                        if (sc.getIdSubCategoria() != 0) {
                            request.getSession().setAttribute("resultado", 1);
                        } else {
                            request.getSession().setAttribute("resultado", 0);
                        }
                    } else {//Si esta en blanco el campo del id entonces se procedera a insertar un nuevo registro 
                        SubCategoria sc = new SubCategoria();
                        sc.setNombreSubCategoria(nombreSub);
                        sc.setIdCategoria(Integer.parseInt(idCategoria));
                        sc = Operaciones.insertar(sc);
                        if (sc.getIdSubCategoria() != 0) {
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
                        Logger.getLogger(SubCategorias.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    request.getSession().setAttribute("resultado", 2);
                    ex.printStackTrace();
                } finally {
                    try {
                        Operaciones.cerrarConexion();
                    } catch (SQLException ex) {
                        Logger.getLogger(SubCategorias.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                response.sendRedirect(request.getContextPath() + "/SubCategorias");
                break;
            }

        }

    }
    
    protected List<Categoria> getAllCategorias(){
        List<Categoria> ListCategoria = new ArrayList<Categoria>();
        try{
            ConexionPool conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            ListCategoria = Operaciones.getTodos(new Categoria());
            Operaciones.commit();
        }catch(Exception ex){
            Logger.getLogger(SubCategorias.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                Operaciones.cerrarConexion();
            }catch(SQLException e){
                Logger.getLogger(SubCategorias.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return ListCategoria;        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
