package com.ferreteria.controladores;

//Vefiricar sino borrar
import com.ferreteria.conexion.Conexion;
import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.Sucursal;
import com.ferreteria.operaciones.Operaciones;
import com.ferreteria.utilerias.ListarCategorias;
import com.ferreteria.utilerias.ListarProductos;
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
public class ServletPrincipal extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("principal.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null || accion.equals("1")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                String sql = "";

                sql = "select * from producto";
                String[][] result = null;
                result = Operaciones.consultar(sql, null);

                ListarProductos lp = new ListarProductos(result, ListarProductos.STYLECARD.STYLEMC);
                lp.setPageContext(request.getContextPath());
                lp.setControlador("/View");
                lp.setControllerCarrito("/Carrito");
                lp.setFolderPath("/ImgProductos");
                String ListadoProducto = lp.getProductos();

                if (request.getParameter("commitAdd") != null) {
                    request.setAttribute("agregado", "commit");
                }
                request.setAttribute("listado", ListadoProducto);
                request.getRequestDispatcher("principal.jsp").forward(request, response);

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

        } else if (accion.equals("2")) {
        try {
            Conexion conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            String sql = "select * from Categoria";
            String[][] ListConsultar = null;
            String ListEnviar;

            ListConsultar = Operaciones.consultar(sql, null);
            
            ListarCategorias lc = new ListarCategorias(ListConsultar);
            lc.setPageContext(request.getContextPath());
            lc.setControlador("/ConsultarCategorias");
            
            ListEnviar = lc.getCategorias();
            request.setAttribute("listaCat", ListEnviar);
            request.getRequestDispatcher("listar_categorias.jsp").forward(request, response);

        } catch (Exception ex) {
            try {
                Operaciones.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ListarCategorias.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(ListarCategorias.class.getName()).log(Level.SEVERE, null, ex);
            }
        }            

        }

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
