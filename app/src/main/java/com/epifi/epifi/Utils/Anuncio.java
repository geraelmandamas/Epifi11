package com.epifi.epifi.Utils;

/**
 * Created by srgera on 15/3/18.
 */

public class Anuncio  {
    private String name;
    private String image;
    private String clase;
    private String location;
    private String  descripcion;
    private String  precio;

    public Anuncio(String name,String image, String clase,String location, String descripcion, String precio) {
        this.name = name;
        this.image = image;
        this.clase = clase;
        this.location = location;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    public Anuncio() {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}