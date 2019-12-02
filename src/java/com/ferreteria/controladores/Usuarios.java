
package com.ferreteria.controladores;

import com.ferreteria.conexion.Conexion;
import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.Comprador;
import com.ferreteria.entidad.ProductoAux;
import com.ferreteria.operaciones.Operaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class Usuarios extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        if(accion.equals("registrar")){
            request.getRequestDispatcher("registrarse.jsp").forward(request, response);
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession s = request.getSession();
        
        if(accion.equals("invitado")){
            String dui = request.getParameter("txtDUI");
            String tel = request.getParameter("txtTelefono");
            int duiInt = 0;
            
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                
                //Verifica si ya existe 
                Comprador c;
                c = Operaciones.get(Integer.parseInt(dui), new Comprador());
                
                
                
                if(c.getDUI() == null){
                    c = new Comprador(Integer.parseInt(dui),Integer.parseInt(tel)); //Si no existe se crea uno
                    duiInt = c.getDUI();
                    c = Operaciones.insertar(c);
                }
                
                c = Operaciones.get(duiInt, new Comprador());
                if(c.getDUI() != 0){
                    s.setAttribute("Cliente", c.getDUI().toString()); //Guardara el dui    
                }else{
                    response.sendRedirect("ServletPrincipal");
                }
                
                
                
                
                List<ProductoAux> carrito = (List<ProductoAux>) s.getAttribute("pCarrito");
                
                if(carrito != null){
                    String datos[] = getDataFactura(carrito);
                    request.setAttribute("pTotal", datos[0]);
                    request.setAttribute("pUnidades", datos[1]);
                    request.setAttribute("monto", datos[2]);
                    
                }
                
                request.getRequestDispatcher("mostrar_factura.jsp").forward(request, response);

                Operaciones.commit();
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex1);
                }
                //request.getSession().setAttribute("resultado", 0);
                response.getWriter().print("No se pudo");
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            
        }

    }
    
    private String[] getDataFactura(List<ProductoAux> carrito){
        String[] datos = new String[3];
        int productos = 0;
        int unidades = 0;
        double monto = 0.0;
        
        productos = carrito.size(); //Cuantos productos hay en el carrito 
        
        for(int i = 0; i<carrito.size(); i++){
            unidades += carrito.get(i).getUnidades();
            monto += (carrito.get(i).getPrecioComun() * carrito.get(i).getUnidades());       
        }
        datos[0] = String.valueOf(productos);
        datos[1] = String.valueOf(unidades);
        datos[2] = String.format("%.2f", monto);
            
        return datos;
    } 


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
