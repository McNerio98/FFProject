/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.entidad;

import com.ferreteria.anotaciones.*;
import java.math.BigDecimal;

/**
 *
 * @author A21-PC03
 */
@Entity(table="Producto")
public class Producto {
    @PrimaryKey
    @AutoIncrement
    private Integer idProducto;
    
    @NotNull
    @FieldName(name="NombreProducto")
    private String nombreProducto;
    
    @NotNull
    @FieldName(name="DescripcionProducto")
    private String descripcionProducto;
    
    @NotNull
    @FieldName(name="PrecioComun")
    private BigDecimal precioComun;
    
    @NotNull
    @FieldName(name="PrecioMayorista")
    private BigDecimal precioMayorista;
    
    @NotNull
    @FieldName(name="RutaImg")
    private String rutaImg;
    
    @NotNull
    private Integer idMarca;
    
    @NotNull
    private Integer idSubCategoria;

    public Producto() {
    }

    public Producto(Integer idProducto, String nombreProducto, String descripcionProducto, BigDecimal precioComun, BigDecimal precioMayorista, String rutaImg, Integer idMarca, Integer idSubCategoria) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioComun = precioComun;
        this.precioMayorista = precioMayorista;
        this.rutaImg = rutaImg;
        this.idMarca = idMarca;
        this.idSubCategoria = idSubCategoria;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public BigDecimal getPrecioComun() {
        return precioComun;
    }

    public void setPrecioComun(BigDecimal precioComun) {
        this.precioComun = precioComun;
    }

    public BigDecimal getPrecioMayorista() {
        return precioMayorista;
    }

    public void setPrecioMayorista(BigDecimal precioMayorista) {
        this.precioMayorista = precioMayorista;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdSubCategoria() {
        return idSubCategoria;
    }

    public void setIdSubCategoria(Integer idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }
    
    
    
}
