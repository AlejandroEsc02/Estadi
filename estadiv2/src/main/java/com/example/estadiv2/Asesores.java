package com.example.estadiv2;

public class Asesores {
    String nombre,asesoria,fireid;

    public String getAsesoria() {
        return asesoria;
    }

    public void setAsesoria(String asesoria) {
        this.asesoria = asesoria;
    }


    public String getFireid() {
        return fireid;
    }

    public void setFireid(String fireid) {
        this.fireid = fireid;
    }

    public Asesores(String nombre, String asesoria,String fireid) {
        this.nombre = nombre;
        this.fireid=fireid;
        this.asesoria=asesoria;
    }

    public Asesores() {
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
