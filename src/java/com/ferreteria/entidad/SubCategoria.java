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
@Entity(table="SubCategoria")
public class SubCategoria {
    @PrimaryKey
    @AutoIncrement
    private Integer idSubCategoria;
    
    @NotNull
    @FieldName(name="NombreSubCategoria")
    private String nombreSubCategoria;
    
    @NotNull
    private Integer idCategoria;

    public SubCategoria() {
    }

    public SubCategoria(Integer idSubCategoria, String nombreSubCategoria, Integer idCategoria) {
        this.idSubCategoria = idSubCategoria;
        this.nombreSubCategoria = nombreSubCategoria;
        this.idCategoria = idCategoria;
    }

    public Integer getIdSubCategoria() {
        return idSubCategoria;
    }

    public void setIdSubCategoria(Integer idSubCategoria) {
        this.idSubCategoria = idSubCategoria;
    }

    public String getNombreSubCategoria() {
        return nombreSubCategoria;
    }

    public void setNombreSubCategoria(String nombreSubCategoria) {
        this.nombreSubCategoria = nombreSubCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    
}
