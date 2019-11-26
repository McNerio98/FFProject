/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.controladores;

import com.ferreteria.conexion.Conexion;
import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.Administrador;
import com.ferreteria.entidad.Rol;
import com.ferreteria.operaciones.Operaciones;
import com.ferreteria.utilerias.DataList;
import com.ferreteria.utilerias.Hash;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author McNerio
 */
@MultipartConfig
public class Administradores extends HttpServlet {

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
                request.setAttribute("ListAdmin", DataList.getAllManager());
                request.setAttribute("numMenu", 7);
                request.getRequestDispatcher("Administradores/admin_consultar.jsp").forward(request, response);
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException e) {
                    Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        } else if (accion.equals("insert")) {
            request.setAttribute("ListRoles", DataList.getAllRoles());
            request.setAttribute("Operacion", "insert");
            request.setAttribute("numMenu", 7);
            request.getRequestDispatcher("Administradores/insert_update.jsp").forward(request, response);
        } else if (accion.equals("update")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();

                Administrador a = Operaciones.get(request.getParameter("id"), new Administrador());
                request.setAttribute("ad", a);
                Operaciones.commit();
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex2) {
                    Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
            request.setAttribute("ListRoles", DataList.getAllRoles());
            request.setAttribute("Operacion", "update");
            request.setAttribute("numMenu", 7);
            request.getRequestDispatcher("Administradores/insert_update.jsp").forward(request, response);
        } else if (accion.equals("delete")) {
            try {
                Conexion conn = new ConexionPool();
                conn.conectar();
                Operaciones.abrirConexion(conn);
                Operaciones.iniciarTransaccion();
                Administrador a = Operaciones.eliminar(request.getParameter("id"), new Administrador());
                request.getSession().setAttribute("resultado", 1);
                Operaciones.commit();
            } catch (Exception ex) {
                try {
                    Operaciones.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex1);
                }
                request.getSession().setAttribute("resultado", 0);
            } finally {
                try {
                    Operaciones.cerrarConexion();
                } catch (SQLException ex) {
                    Logger.getLogger(Administradores.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            response.sendRedirect(request.getContextPath()+"/Administradores");
    }

}

@Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        switch (accion) {
            case "insertar_modificar": {

                String userCuenta = request.getParameter("txtUsuario");
                String nombre = request.getParameter("txtNombre");
                String apellido = request.getParameter("txtApellido");
                String clave = request.getParameter("txtClave");
                String email = request.getParameter("txtEmail");
                String telefono = request.getParameter("txtTelefono");
                String val_Rol = request.getParameter("valSelectRol");
                Part arch = request.getPart("archivoPart");
                String rutaRelativaAppB = getServletConfig().getServletContext().getRealPath("/");
                String nombreImg = "";

                if (arch.getSize() > 0) { //Si seleciono una foto 
                    InputStream flujoEntrada = arch.getInputStream();
                    nombreImg = nameGenerated()+".jpg";
                    File f = new File(rutaRelativaAppB + "/Administradores/Photos/" + nombreImg);
                    FileOutputStream ous = new FileOutputStream(f);
                    int dato = flujoEntrada.read();
                    while (dato != -1) {
                        ous.write(dato);
                        dato = flujoEntrada.read();
                    }
                    ous.close();
                    flujoEntrada.close();
                } else {// Se le asigna el avatar
                    nombreImg = "avatar.jpg";
                }

                try {
                    Conexion conn = new ConexionPool();
                    conn.conectar();
                    Operaciones.abrirConexion(conn);
                    Operaciones.iniciarTransaccion();

                    if (request.getParameter("Operacion").equals("insert")) {//Esta insertando 
                        Administrador a = new Administrador(userCuenta, nombre, apellido, Hash.generarHash(clave, Hash.SHA256), "/"+nombreImg, email, telefono, Integer.parseInt(val_Rol));
                        a = Operaciones.insertar(a);
                        request.getSession().setAttribute("resultado", 1);
                    } else {//Esta actualizando
                        Administrador a = new Administrador(userCuenta, nombre, apellido, Hash.generarHash(clave, Hash.SHA256), "/"+nombreImg, email, telefono, Integer.parseInt(val_Rol));
                        a = Operaciones.actualizar(a.getUsuario(), a);
                        request.getSession().setAttribute("resultado", 1);
                    }

                    Operaciones.commit();
                } catch (Exception ex) {
                    try {
                        Operaciones.rollback();
                    

} catch (SQLException ex1) {
                        Logger.getLogger(Administradores.class
.getName()).log(Level.SEVERE, null, ex1);
                    }
                    request.getSession().setAttribute("resultado", 2);
                    ex.printStackTrace();
                } finally {
                    try {
                        Operaciones.cerrarConexion();
                    

} catch (SQLException ex) {
                        Logger.getLogger(Administradores.class
.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                response.sendRedirect(request.getContextPath() + "/Administradores");
                break;
            }
            case "eliminar": {
                break;
            }
        }
    }

    protected String nameGenerated() {
        java.util.Date fechaActual = new java.util.Date();
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        
        String cod = "";
        
        String tiempo = formatoHora.format(fechaActual).toString();
        String fecha = formatoFecha.format(fechaActual).toString();
        cod = tiempo.replaceAll(":", "") + fecha.replaceAll("/","");        
        return cod;
    }

    @Override
        public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
