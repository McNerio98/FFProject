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
@Entity(table = "Sucursales")
public class Sucursal {
    @PrimaryKey
    @AutoIncrement
    private Integer idSucursal;
    
    @NotNull
    @FieldName(name="NombreSucursal")
    private String nombreSucursal;
    
    @NotNull
    @FieldName(name="Direccion")
    private String direccion;

    public Sucursal() {
    }

    public Sucursal(Integer idSucursal, String nombreSucursal, String direccion) {
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.direccion = direccion;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
