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
@Entity(table="DetalleProductoEnFactura")
public class DetalleProductosEnFactura {
    @PrimaryKey
    @NotNull
    private Integer idProducto;
    
    @PrimaryKey
    @NotNull
    private Integer idFactura;
    
    @NotNull
    @FieldName(name="NumUnidades")
    private Integer numUnidades;

    public DetalleProductosEnFactura() {
    }

    public DetalleProductosEnFactura(Integer idProducto, Integer idFactura, Integer numUnidades) {
        this.idProducto = idProducto;
        this.idFactura = idFactura;
        this.numUnidades = numUnidades;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getNumUnidades() {
        return numUnidades;
    }

    public void setNumUnidades(Integer numUnidades) {
        this.numUnidades = numUnidades;
    }    
    
}
