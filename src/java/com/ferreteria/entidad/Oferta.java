/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.entidad;

import com.ferreteria.anotaciones.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author A21-PC03
 */
@Entity(table="Ofertas")
public class Oferta {
    @PrimaryKey
    @AutoIncrement
    private Integer idOferta;
    
    @NotNull
    @FieldName(name="FechaInicio")
    private Date fechaInicio;
    
    @NotNull
    @FieldName(name="FechaFin")
    private Date fechaFin;
    
    @NotNull
    @FieldName(name="Descuento")
    private BigDecimal descuento;
    
    @NotNull
    private Integer idProducto;

    public Oferta() {
    }

    public Oferta(Integer idOferta, Date fechaInicio, Date fechaFin, BigDecimal descuento, Integer idProducto) {
        this.idOferta = idOferta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descuento = descuento;
        this.idProducto = idProducto;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }
    
    
    
    
}
