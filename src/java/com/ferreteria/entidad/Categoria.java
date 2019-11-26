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
@Entity(table="Categoria")
public class Categoria {
    @PrimaryKey
    @AutoIncrement
    private Integer idCategoria;
    
    @NotNull
    @FieldName(name="NombreCategoria")
    private String nombreCategoria;

    public Categoria() {
    }

    public Categoria(Integer idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    
    
    
    
}
