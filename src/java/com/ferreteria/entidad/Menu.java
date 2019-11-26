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
@Entity(table="Menu")
public class Menu {
    @PrimaryKey
    private int idMenu;
    
    @NotNull
    @FieldName(name="Menu")
    private String menu;
    
    @FieldName(name="Descripcion")
    private String descripcion;
    
    @FieldName(name="IconPath")
    private String iconPath;
    
    @NotNull
    @FieldName(name="Controlador")
    private String controlador;
    
    private int idPadre;

    public Menu() {
    }

    public Menu(int idMenu, String menu, String descripcion, String iconPath, String controlador, int idPadre) {
        this.idMenu = idMenu;
        this.menu = menu;
        this.descripcion = descripcion;
        this.iconPath = iconPath;
        this.controlador = controlador;
        this.idPadre = idPadre;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getControlador() {
        return controlador;
    }

    public void setControlador(String controlador) {
        this.controlador = controlador;
    }

    public int getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }
    
    
}
