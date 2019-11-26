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
@Entity(table="VentaFactura")
public class VentaFactura {
    @PrimaryKey
    @AutoIncrement
    private Integer idVentaFactura;
    
    @NotNull
    @FieldName(name="FechaVenta")
    private Date fechaVenta;
    
    @NotNull
    @FieldName(name="CantidadProductos")
    private Integer cantidadProductos;
    
    @NotNull
    @FieldName(name="TotalVenta")
    private BigDecimal totalVenta;
    
    @NotNull
    @FieldName(name="TotalDescuentos")
    private BigDecimal totalDescuentos;
    
    @NotNull
    @FieldName(name="FormaPago")
    private String formaPago;
    
    @NotNull
    private Integer idCliente;

    public VentaFactura() {
    }

    public VentaFactura(Integer idVentaFactura, Date fechaVenta, Integer cantidadProductos, BigDecimal totalVenta, BigDecimal totalDescuentos, String formaPago, Integer idCliente) {
        this.idVentaFactura = idVentaFactura;
        this.fechaVenta = fechaVenta;
        this.cantidadProductos = cantidadProductos;
        this.totalVenta = totalVenta;
        this.totalDescuentos = totalDescuentos;
        this.formaPago = formaPago;
        this.idCliente = idCliente;
    }

    public Integer getIdVentaFactura() {
        return idVentaFactura;
    }

    public void setIdVentaFactura(Integer idVentaFactura) {
        this.idVentaFactura = idVentaFactura;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(Integer cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }

    public BigDecimal getTotalDescuentos() {
        return totalDescuentos;
    }

    public void setTotalDescuentos(BigDecimal totalDescuentos) {
        this.totalDescuentos = totalDescuentos;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    
    
    
    
}
