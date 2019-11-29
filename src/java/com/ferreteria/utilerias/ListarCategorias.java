/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.utilerias;

import com.ferreteria.conexion.Conexion;
import com.ferreteria.conexion.ConexionPool;
import com.ferreteria.operaciones.Operaciones;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZEUS
 */
public class ListarCategorias {

    private String estilo;
    private String[][] categorias;
    private String[][] subcategorias;

    public static final class STYLECARD {

        public static final String LIST1 = "listado1";
        public static final String LIST2 = "listado2";
    }
    private String controller;
    private String pageContext;

    public ListarCategorias(String[][] cat) {
        this.categorias = cat;
    }

    public String getCategorias() throws SQLException {
        String lista = "";
        String[][] cat = this.categorias;
        /*[Columnas][Filas]*/
        int numCat, numSub;
        int contador = 1;

        if (cat != null) {
            lista += "<ul class='listado1 row'>";
            numCat = cat[0].length;
            for (int i = 0; i < numCat; i++) {
                lista += " <li class='titulo'><span>" + cat[1][i] + "</span>";
            }

        } else {
            lista += "empty";
        }

        return lista;
    }

    public void setPageContext(String ruta) {
        this.pageContext = ruta;
    }

    public void setControlador(String c) {
        this.controller = c;
    }

    private String getSubCategorias(int idCat) {
        String Sub[][] = null;/*Devolvera dos columnas y N Registros*/
        String lista = null;

        try {
            Conexion conn = new ConexionPool();
            conn.conectar();
            Operaciones.abrirConexion(conn);
            Operaciones.iniciarTransaccion();
            String sql = "select idSubCategoria,NombreSubCategoria from SubCategoria where idCategoria = ?";

            List<Object> params = new ArrayList<>();
            params.add(idCat);
            Sub = Operaciones.consultar(sql, params);
            
            if(Sub != null){
                lista += "<ul>";
                    for(int i=0; i<Sub[0].length; i++){
                        //Aqui me quede
                    }
                lista += "</ul>";
            }

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

        return ""; //Si no hay nada devuelve nulo
    }

}
