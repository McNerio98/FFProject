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
@Entity(table="Comprador")
public class Comprador {
    @PrimaryKey
    private Integer DUI;
    
    @NotNull
    @FieldName(name="Nombre")
    private String nombre;
    
    @NotNull
    @FieldName(name="Apellido")
    private String apellido;
    
    @NotNull
    @FieldName(name="Telefono")
    private Integer telefono;

    public Comprador() {
    }

    public Comprador(Integer DUI, String nombre, String apellido, Integer telefono) {
        this.DUI = DUI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }

    public Integer getDUI() {
        return DUI;
    }

    public void setDUI(Integer DUI) {
        this.DUI = DUI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
    
    
}
