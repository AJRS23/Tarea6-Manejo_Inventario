package com.tec.alvaroramirez.tarea6inventario;

/**
 * Created by alvaroramirez on 5/24/18.
 */

public class Producto {
    String id;
    String nombre;
    float precio;
    String descripcion;

    public Producto(){

    }

    public Producto(String id,String nombre, Float precio, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getId() {
        return id;
    }
}
