/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.utilerias;

import java.sql.SQLException;

/**
 *
 * @author ZEUS
 */
public class ListarProductos {
    private String estilo;
    private String[][] productos;

    public static final class STYLECARD {
        public static final String STYLEMC = "cardSales1";
        public static final String OTHERSTYLE = "card01";
    }
    private String controller;
    private String controllerCarrito;
    private String rutaImagenes;
    private String pageContext;
    
    
    public ListarProductos(String[][] rs, String estilo) {
       this.productos = rs;         
       this.estilo = estilo;
    }
    
    public String getProductos() throws SQLException{
        String lista ="";
        String[][] rst = this.productos;
        /*[Columnas][Filas]*/
        int numRegistros;
        int contador = 1; 
        
        if(rst !=null ){
            numRegistros = rst[0].length; //Si no es nulo se sabe que hay registros
            for(int i=0; i < numRegistros; i++){
                if(contador == 1){//Si son 4 habre una nueva row
                    lista += "<section class='row contenedor'>";
                }
                
                lista += "<article class='col-lg-3 col-sm-6'>";
                //Es de las columnas es explicito, se sabe en que posicion estan todos
                lista += "<div class='"+ estilo+"'><div class='row'><img src='" + this.getFolderLocation() + rst[5][i] + "'>";
                lista += "<div class='descripcion'><a href='" + getControlador() + "?id=" + rst[0][i] + "' id='linkage'>";
                lista += rst[1][i] + "</a><p id='precio'>Precio $<span>" + rst[3][i];
                lista += "</span></p></div></div><form method='POST' action='"+ getControllerCarrito() +"'><div class='priceForQuantity'><select name='slcCantidad' id='slcCantidad'>";
                lista += "<option value='1'>1</option><option value='2'>2</option><option value='3'>3</option>\n"
                            + "<option value='4'>4</option><option value='5'>5</option><option value='6'>6</option>\n"
                            + "<option value='7'>7</option><option value='0'>+MAS</option></select><span id='price'> $ \n";
                lista += rst[3][i] + "</span></div><input type='submit' class='opc' value='+ Agregar al Carrito'>";
                lista += "<input type='hidden' name='txtIdProducto' id='txtIdProducto' value='"+ rst[0][i]  +"'>";
                lista += "<a href='Disponibilidad' class='opc border1' id='verificar'>Ver Disponibilidad</a></form>";
                lista += "</div></article>";

                if(contador == 4 || i == (numRegistros-1)){
                    lista += "</section>";
                    contador = 1;
                }else{
                    contador++;//Si no es 4 se sigue agregando tarjetas                    
                }
                
            }
        }else{
            lista += "empty";
        }
        
        /*Si es igual a cuatro que cree una nueva row*/
        return lista;
    }
    
    public void setPageContext(String ruta){
        this.pageContext = ruta;
    }
    
    public void setFolderPath(String ruta){
        this.rutaImagenes = ruta;
    }
    
    private String getFolderLocation(){
        return this.pageContext + this.rutaImagenes;
    }
    
    public void setControlador(String c){
        this.controller = c;
    }
    
    public void setControllerCarrito(String cc){
        this.controllerCarrito = cc;
    }
    
    private String getControllerCarrito(){
        return this.pageContext + this.controllerCarrito;
    } 
    public String getControlador(){
        return this.pageContext + this.controller;
    }
    
    
    
}
