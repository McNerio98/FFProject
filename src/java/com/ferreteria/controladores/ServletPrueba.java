/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.controladores;

import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.Rol;
import com.ferreteria.operaciones.Operaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ServletPrueba extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletPrueba</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletPrueba at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().print("Hola");
        
        
        /*

        ArrayList<Rol> RolesDisponibles = new ArrayList<Rol>();

        try {
            RolesDisponibles = obtenerRoles();
            request.setAttribute("ListaRoles", RolesDisponibles);
            request.getRequestDispatcher("TestPage.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletPrueba.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("Login");
        }*/
        

    }

    public ArrayList<Rol> obtenerRoles() throws SQLException {
        ArrayList<Rol> roles = new ArrayList<Rol>();
        
        try {
            ConexionPool conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            roles = Operaciones.getTodos(new Rol());
            
            Operaciones.commit();
            System.out.print("Se realizaron todas las operaciones");
        } catch (Exception ex) {
            Operaciones.rollback();
            System.out.print("Error 1");
        } finally {
            try {
                Operaciones.cerrarConexion();
                System.out.print("Se Cerro la conexion");
            } catch (SQLException eQ) {
                System.out.print("Error 2");
            }
        }
        
        return roles;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
