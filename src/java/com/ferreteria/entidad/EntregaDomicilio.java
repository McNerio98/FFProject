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
@Entity(table="EntregaDomicilio")
public class EntregaDomicilio {
    @PrimaryKey
    @AutoIncrement
    private Integer idEntrega;
    
    @NotNull
    @FieldName(name="FechaEstablecida")
    private Date fechaEstablecida;
    
    @NotNull
    @FieldName(name="FechaEntregado")
    private Date fechaEntregado;
    
    @NotNull
    @FieldName(name="GastosDeEnvio")
    private BigDecimal gastosDeEnvio;
    
    @NotNull
    @FieldName(name="EstadoEntrega")
    private String estadoEntrega;
    
    @NotNull
    private Integer idVentaFactura;

    public EntregaDomicilio() {
    }

    public EntregaDomicilio(Integer idEntrega, Date fechaEstablecida, Date fechaEntregado, BigDecimal gastosDeEnvio, String estadoEntrega, Integer idVentaFactura) {
        this.idEntrega = idEntrega;
        this.fechaEstablecida = fechaEstablecida;
        this.fechaEntregado = fechaEntregado;
        this.gastosDeEnvio = gastosDeEnvio;
        this.estadoEntrega = estadoEntrega;
        this.idVentaFactura = idVentaFactura;
    }

    public Integer getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(Integer idEntrega) {
        this.idEntrega = idEntrega;
    }

    public Date getFechaEstablecida() {
        return fechaEstablecida;
    }

    public void setFechaEstablecida(Date fechaEstablecida) {
        this.fechaEstablecida = fechaEstablecida;
    }

    public Date getFechaEntregado() {
        return fechaEntregado;
    }

    public void setFechaEntregado(Date fechaEntregado) {
        this.fechaEntregado = fechaEntregado;
    }

    public BigDecimal getGastosDeEnvio() {
        return gastosDeEnvio;
    }

    public void setGastosDeEnvio(BigDecimal gastosDeEnvio) {
        this.gastosDeEnvio = gastosDeEnvio;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public Integer getIdVentaFactura() {
        return idVentaFactura;
    }

    public void setIdVentaFactura(Integer idVentaFactura) {
        this.idVentaFactura = idVentaFactura;
    }
    
    
}
