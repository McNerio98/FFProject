/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.controladores;

import com.ferreteria.conexion.Conexion;
import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.DetalleProductosEnFactura;
import com.ferreteria.entidad.Producto;
import com.ferreteria.entidad.ProductoAux;
import com.ferreteria.operaciones.Operaciones;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author ZEUS
 */
public class Carrito extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String producto = request.getParameter("txtIdProducto");
        String unidades = request.getParameter("slcCantidad");
        //response.getWriter().println("Los Campos son: " + campo1 +" y " + campo2);
        HttpSession s = request.getSession();
        List<ProductoAux> carrito = (List<ProductoAux>) s.getAttribute("pCarrito");
        
        Producto p;
        ProductoAux pa;

        if (carrito == null) { //Si no existe la crea y la agrega a la sesion
            carrito = new ArrayList();
            s.setAttribute("pCarrito", carrito);
        }
        
        try {
            Conexion conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            
            p = Operaciones.get(Integer.parseInt(producto),new Producto());
            if(p.getIdProducto() != 0){
                pa = new ProductoAux();
                pa.setIdProducto(p.getIdProducto());
                pa.setDescripcionProducto(p.getDescripcionProducto());
                pa.setNombreProducto(p.getNombreProducto());
                pa.setPrecioComun(p.getPrecioComun().doubleValue());
                pa.setRutaImg(p.getRutaImg());
                double subt =  (p.getPrecioComun().doubleValue()) * Integer.parseInt(unidades);
                pa.setSubTotal(subt);
                pa.setUnidades(Integer.parseInt(unidades));
                
                carrito.add(pa);
                
            }
            s.setAttribute("pCarrito", carrito);
            
        } catch (Exception ex) {
            try {
                Operaciones.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Carrito.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                Logger.getLogger(Carrito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        request.getRequestDispatcher("TestPage.jsp").forward(request, response);

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
