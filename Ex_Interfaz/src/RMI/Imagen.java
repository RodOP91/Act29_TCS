/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;


import java.io.Serializable;


/**
 *
 * @author ferzo
 */
public class Imagen implements Serializable{
    
    private String nombre;
    private String url;
    
    public Imagen(String nom, String url){
        this.nombre = nom;
        this.url = url;
    }

    //GETTERS & SETTERS---------------------------------------------------------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
