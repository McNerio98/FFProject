/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.controladores;

import com.ferreteria.conexion.Conexion;
import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.DetalleProductosEnFactura;
import com.ferreteria.entidad.ProductoAux;
import com.ferreteria.entidad.VentaFactura;
import com.ferreteria.operaciones.Operaciones;
import com.ferreteria.utilerias.DataList;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Compras extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession s = request.getSession();
        List<ProductoAux> carrito = (List<ProductoAux>) s.getAttribute("pCarrito");
        String cl = (String) s.getAttribute("Cliente");

        if (accion == null) {
            if (s.getAttribute("Cliente") == null) {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("mostrar_factura.jsp").forward(request, response);
            }
        } else if (accion.equals("facturar")) {
            //Validar si el carrito no esta vacio y hay un usuario
            if (carrito != null && (carrito.size() > 0) && cl != null) {
                
                DetalleProductosEnFactura df;
                VentaFactura vf;
                int producto = 0, unidades = 0, idFactura = 0;

                try {
                    Conexion conn = new ConexionPool();
                    conn.conectar();
                    Operaciones.abrirConexion(conn);
                    Operaciones.iniciarTransaccion();

                    //Crear VentaFactura 
                    Date fechaActual = new Date();
                    String sm = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(fechaActual);
                    Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(sm);

                    vf = new VentaFactura();
                    vf.setFechaVenta(new Timestamp(date.getTime())); //Se le establece por lo menos un valor

                    vf = Operaciones.insertar(vf);//Se conserva el Id

                    if (vf.getIdVentaFactura() != 0) { //Entonces se inserto 
                        //Se crean Los Detalle Factura y el update sobre la factura
                        idFactura = vf.getIdVentaFactura();
                        //facturar(carrito, vf.getIdVentaFactura(), Integer.parseInt(cl));

                        for (int i = 0; i < carrito.size(); i++) {
                            producto = carrito.get(i).getIdProducto();
                            unidades = carrito.get(i).getUnidades();

                            df = new DetalleProductosEnFactura();
                            df.setIdFactura(idFactura);
                            df.setIdProducto(producto);
                            df.setNumUnidades(unidades);
                            df = Operaciones.insertar(df);
                        }

                        String[] infoFactura = DataList.getDataFactura(carrito); //0 productos 1 unidades 2 monto

                        vf = Operaciones.get(idFactura, new VentaFactura()); //Se prepara la factura para realizar el update
                        //Este objeto ya contiene dos campos
                        vf.setCantidadProductos(Integer.parseInt(infoFactura[1]));
                        vf.setTotalVenta(BigDecimal.valueOf(Double.parseDouble(infoFactura[2])));
                        vf.setTotalDescuentos(BigDecimal.valueOf(0.0));
                        vf.setFormaPago("Debito");
                        vf.setIdCliente(Integer.parseInt(cl));

                        vf = Operaciones.actualizar(vf.getIdVentaFactura(), vf);

                        s.removeAttribute("pCarrito");
                        response.getWriter().print("Se creo la factura Aqui debe mostrar el PDF de la factura");

                    }

                    Operaciones.commit();
                } catch (Exception ex) {
                    try {
                        Operaciones.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                } finally {
                    try {
                        Operaciones.cerrarConexion();
                    } catch (SQLException ex) {
                        Logger.getLogger(Compras.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                //Si el carrito esta vacio o no hay usuario lo redireciona al inicio
                response.sendRedirect("ServletPrincipal");
            }
            //Remover lista en el carrito
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
