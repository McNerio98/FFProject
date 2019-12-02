/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.controladores;

import com.ferreteria.conexion.Conexion;
import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.operaciones.Operaciones;
import com.ferreteria.utilerias.ListarProductos;
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
public class ConsultarCategorias extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idSubCat = request.getParameter("idSubCategoria");
        if(idSubCat != null){
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                String sql = "";

                sql = "select * from producto where idSubCategoria = ?";
                List<Object> param = new ArrayList();
                param.add(request.getParameter("idSubCategoria"));
                
                String[][] result = null;
                result = Operaciones.consultar(sql, param);

                ListarProductos lp = new ListarProductos(result, ListarProductos.STYLECARD.STYLEMC);
                lp.setPageContext(request.getContextPath());
                lp.setControlador("/View");
                lp.setControllerCarrito("/Carrito");
                lp.setFolderPath("/ImgProductos");
                
                String ListadoProducto = lp.getProductos();


                request.setAttribute("listado", ListadoProducto);
                
                request.getRequestDispatcher("lista_productos.jsp").forward(request, response);

            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(ServletPrincipal.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }             
        }else{
            response.sendRedirect("ServletPrincipal?accion=2");
        }
       

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
