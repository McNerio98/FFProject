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
@Entity(table="Usuarios")
public class Usuario {
    @PrimaryKey
    @FieldName(name="NombreUsuario")
    private String nombreUsuario;
    
    @NotNull
    @FieldName(name="Clave")
    private String password;
    
    @NotNull
    private Integer DUI;
    
    @NotNull
    @FieldName(name="Nombre")
    private String nombre;

    public Usuario() {
    }

    public Usuario(String nombreUsuario, String password, Integer DUI, String nombre) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.DUI = DUI;
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
    
    
}
