package com.example.estadiv2;

public class Generales {
    String pregunta, usuario,fireid,date;

    public String getFireid() {
        return fireid;
    }

    public void setFireid(String fireid) {
        this.fireid = fireid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Generales(String pregunta, String usuario, String fireid, String date) {
        this.pregunta = pregunta;
        this.usuario = usuario;
        this.fireid = fireid;
        this.date=date;

    }

    public String getPregunta() {

        return pregunta;
    }
    public Generales() {
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
