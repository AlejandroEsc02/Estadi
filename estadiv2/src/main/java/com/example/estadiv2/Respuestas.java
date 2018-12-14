package com.example.estadiv2;

public class Respuestas {
    String respuesta, usuario, fireid,date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Respuestas(String respuesta, String usuario, String fireid, String date) {
        this.respuesta = respuesta;
        this.usuario = usuario;
        this.fireid = fireid;
        this.date=date;

    }
    public Respuestas() {
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFireid() {
        return fireid;
    }

    public void setFireid(String fireid) {
        this.fireid = fireid;
    }
}
