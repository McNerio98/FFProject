/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.utilerias;

import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.entidad.Administrador;
import com.ferreteria.entidad.Menu;
import com.ferreteria.entidad.ProductoAux;
import com.ferreteria.entidad.Rol;
import com.ferreteria.operaciones.Operaciones;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ZEUS
 */
public class DataList {

    public static ArrayList<Rol> getAllRoles() {
        ArrayList<Rol> roles = new ArrayList<Rol>();

        try {
            ConexionPool conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            roles = Operaciones.getTodos(new Rol());
            Operaciones.commit();
            System.out.print("Se extrajeron los roles");
        } catch (Exception ex) {
            System.out.print("Error al solicitar conexion");
        } finally {
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException e) {
                System.out.print("Error al cerrar la conexion");
            }
        }
        return roles;
    }

    public static ArrayList<Administrador> getAllManager() {
        ArrayList<Administrador> lista = new ArrayList<Administrador>();
        try {
            ConexionPool conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            System.out.println("Datos Extraidos");
            lista = Operaciones.getTodos(new Administrador());
            Operaciones.commit();
        } catch (Exception ex) {
            System.out.println("Error al abrir conexion");
        } finally {
            try {
                Operaciones.cerrarConexion();
            } catch (SQLException e) {
                System.out.println("Error al Cerrar Conn");
            }
        }

        return lista;
    }
    
    
    public static String[] getDataFactura(List<ProductoAux> carrito){
        String[] datos = new String[3];
        int productos = 0;
        int unidades = 0;
        double monto = 0.0;
        
        productos = carrito.size(); //Cuantos productos hay en el carrito 
        
        for(int i = 0; i<carrito.size(); i++){
            unidades += carrito.get(i).getUnidades();
            monto += (carrito.get(i).getPrecioComun() * carrito.get(i).getUnidades());       
        }
        datos[0] = String.valueOf(productos); //Totalproductos index 0
        datos[1] = String.valueOf(unidades); //TotalUnidades index 1
        DecimalFormat df = new DecimalFormat("0.00");
        datos[2] = df.format(monto).replaceAll(",","."); //MontoTotal index 2
        return datos;
    }     



}
