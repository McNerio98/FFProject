package com.ferreteria.controladores;

import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.Administrador;
import com.ferreteria.entidad.Menu;
import com.ferreteria.operaciones.Operaciones;
import com.ferreteria.utilerias.Hash;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author McNerio
 * Esta clase Mostrar el Login 
 */
public class Manager extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("Administradores/Login.jsp").forward(request, response);
        
        /*if (request.getSession().getAttribute("Usuario") != null) {
            response.sendRedirect("ManagerPrincipal");
        } else {
            request.getRequestDispatcher("vistas/admin/loginAdministrador.jsp").forward(request, response);
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //Aqui para valisar datos del form login
            iniciarSesion(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        PrintWriter io = response.getWriter();
        String usuario = request.getParameter("txtUsuario");
        String clave = request.getParameter("txtClave");

        if (usuario == null) {
            usuario = "";
        }
        if (clave == null) {
            clave = "";
        }

        try {
            ConexionPool conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            //Se verifica si se logro solicitar la conexion al pool 
            //Preguntar por esta linea
            if (conn.getConexion() == null) {
                //Si no se logro solicitar la conexion, se devuelve al login
                request.getRequestDispatcher("Administradores/Login.jsp").forward(request, response);
            } else {
                HttpSession sesion = request.getSession();
                Administrador ad = Operaciones.get(usuario, new Administrador());
                if (ad.getUsuario() != null) {
                    //Si existe un registro con ese usuario
                    //Se procede a comprar las password con la que ingreso en el fomulario
                    if (ad.getClave().equals(Hash.generarHash(clave, Hash.SHA256))) {
                        sesion.setAttribute("Usuario", ad.getUsuario());
                        sesion.setAttribute("Nombre", ad.getNombre() + " " + ad.getApellido());
                        sesion.setAttribute("Photo", ad.getPhotoPath());
                        sesion.setAttribute("Rol", ad.getIdRol());
                        List<Menu> permisos = getPermisos(ad.getIdRol());
                        List<Menu> MenuPrincipal = permisos.stream().filter(field->field.getIdPadre()==0).collect(Collectors.toList());
                        
                        sesion.setAttribute("Permisos", permisos);
                        sesion.setAttribute("MenuPrincipal", MenuPrincipal);
                        
                        response.sendRedirect("ManagerPrincipal");
                    } else {
                        //La contraseña es incorrecta o se dejo vacia
                        request.setAttribute("error", 2);
                        request.getRequestDispatcher("Administradores/Login.jsp").forward(request, response);
                    }
                } else {
                    //El usuario es invalido
                    //Se dejo el campo Usuario  vacio o no existe en los registro                    
                    request.setAttribute("error", 2);
                    request.getRequestDispatcher("Administradores/Login.jsp").forward(request, response);
                }

            }
            Operaciones.commit();
        } catch (Exception ex) {
            Operaciones.rollback();
            io.print("Error al solicitar conexion");
        } finally {
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException ex) {
                io.print("Error al cerrar la conexion");
            }
        }
    }

    private List<Menu> getPermisos(Integer idRol) throws SQLException {
        List<Menu> permisos = new ArrayList();

        try {
            String sql = "select * from Menu where idMenu in (select idMenu from Permisos where idRol = ?)";
            List<Object> parametros = new ArrayList();
            parametros.add(idRol);
            String[][] result = Operaciones.consultar(sql, parametros);
            // Array[Columna][Fila]
            for (int i = 0; i < result[0].length; i++) {
                Menu m = new Menu(Integer.parseInt(result[0][i]), result[1][i], result[2][i], result[3][i], result[4][i], Integer.parseInt(result[5][i] == null ? "0" : result[5][i]));
                permisos.add(m);
            }

        } catch (Exception ex) {
            Operaciones.rollback();
        }

        return permisos;
    }

}
