/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.controladores;

import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.Administrador;
import com.ferreteria.entidad.Menu;
import com.ferreteria.operaciones.Operaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ZEUS
 */
public class ManagerPrincipal extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null) {
            HttpSession s = request.getSession();
            List<Menu> per = (List<Menu>) s.getAttribute("Permisos");
            
            String op = request.getParameter("op");
            
            if(op!=null){
                if(op.equals("1")){
                    request.setAttribute("numMenu", 1);
                }else if(op.equals("2")){
                    request.setAttribute("numMenu", 2);
                }else if(op.equals("3")){
                    request.setAttribute("numMenu", 3);
                }else if(op.equals("4")){
                    request.setAttribute("numMenu", 4);
                }else if(op.equals("5")){
                    request.setAttribute("numMenu", 5);
                }
                
                List<Menu> PermisosAsignados = per.stream().filter(field -> field.getIdPadre()==Integer.parseInt(op)).collect(Collectors.toList());
                request.setAttribute("PermisosAsignados",PermisosAsignados);
            }
            
            request.getRequestDispatcher("vistas/ad_principal.jsp").forward(request, response);

        } else if (accion.equals("logout")) {
            cerrarSesion(request, response);
        }
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession s = request.getSession();
        s.removeAttribute("Usuario");
        s.removeAttribute("Nombre");
        s.removeAttribute("Photo");
        s.invalidate();
        response.sendRedirect("Manager");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
