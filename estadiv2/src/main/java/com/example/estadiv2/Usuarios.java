package com.example.estadiv2;

public class Usuarios {
    String nombre, sexo, asesor,uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAsesor() {
        return asesor;

    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public Usuarios(String nombre, String sexo, String asesor,String uid) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.asesor=asesor;
        this.uid=uid;

    }
    public Usuarios() {
    }

    public String getNombre() { return nombre;    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


}
