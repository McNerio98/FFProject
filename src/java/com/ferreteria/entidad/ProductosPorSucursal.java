/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.entidad;

import com.ferreteria.anotaciones.*;

/**
 *
 * @author A21-PC03
 */
@Entity(table="ProductosPorSucursal")
public class ProductosPorSucursal {
    @PrimaryKey    
    @NotNull
    private Integer idProducto;
    @NotNull
    //Deberia llevar otro PrimaryKey
    private Integer idSucursal;

    public ProductosPorSucursal() {
    }

    public ProductosPorSucursal(Integer idProducto, Integer idSucursal) {
        this.idProducto = idProducto;
        this.idSucursal = idSucursal;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    
}
