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
@Entity(table="Marca")
public class Marca {
    @PrimaryKey
    @AutoIncrement
    private Integer idMarca;
    
    @NotNull
    @FieldName(name="NombreMarca")
    private String nombreMarca;
    
    @NotNull
    @FieldName(name="RutaImg")
    private String rutaImg;
    
    @NotNull
    @FieldName(name="Descripcion")
    private String descripcion;

    public Marca() {
    }

    public Marca(Integer idMarca, String nombreMarca, String rutaImg, String descripcion) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
        this.rutaImg = rutaImg;
        this.descripcion = descripcion;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    
}
