/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ferreteria.entidad;

import com.ferreteria.anotaciones.*;

/**
 *
 * @author ZEUS
 */
@Entity(table="Administrador")
public class Administrador {
    @PrimaryKey
    @FieldName(name = "idAdmin")
    private String usuario;
    
    @NotNull
    @FieldName(name = "Nombre")
    private String nombre;
    
    @NotNull
    @FieldName(name = "Apellido")
    private String apellido;
    
    @NotNull
    @FieldName(name = "Clave")
    private String clave;
    
    @FieldName(name = "PhotoPath")
    private String photoPath;
    
    @FieldName(name = "Correo")
    private String correo;
    
    @FieldName(name = "Telefono")
    private String telefono;
    
    @NotNull
    private int idRol;

    public Administrador() {
    }

    public Administrador(String usuario, String nombre, String apellido, String clave, String photoPath, String correo, String telefono, int idRol) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.photoPath = photoPath;
        this.correo = correo;
        this.telefono = telefono;
        this.idRol = idRol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    
    
}
