package com.ferreteria.controladores;

//Vefiricar sino borrar
import com.ferreteria.conexion.Conexion;
import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.Sucursal;
import com.ferreteria.operaciones.Operaciones;
import com.ferreteria.utilerias.ListarProductos;
import com.ferreteria.utilerias.Tabla;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class ServletPrincipal extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("principal.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        try {
            Conexion conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();           
            String sql = "";
            
            if(accion == null || accion.equals("1")){
                sql = "select * from producto";    
            }else if(accion.equals("Categorias")){
                response.getWriter().print("Holis");
            } 

            
            
            String[][] result = null;
            result = Operaciones.consultar(sql, null);

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
                Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
/*
        if (accion == null) {
            request.getRequestDispatcher("principal.jsp").forward(request, response);
        } else if (accion.equals("2")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                String sql = "select * from producto";

                String[][] result = null;
                result = Operaciones.consultar(sql, null);

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
                    Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Marcas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
*/

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
