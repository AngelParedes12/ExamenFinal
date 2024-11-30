package com.example.examenp3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Activo {
    private final StringProperty id;
    private final StringProperty tipo;
    private final StringProperty marca;
    private final StringProperty modelo;
    private final StringProperty serial;
    private final StringProperty responsable;

    public Activo(String id, String tipo, String marca, String modelo, String serial, String responsable) {
        this.id = new SimpleStringProperty(id);
        this.tipo = new SimpleStringProperty(tipo);
        this.marca = new SimpleStringProperty(marca);
        this.modelo = new SimpleStringProperty(modelo);
        this.serial = new SimpleStringProperty(serial);
        this.responsable = new SimpleStringProperty(responsable);
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty tipoProperty() {
        return tipo;
    }

    public StringProperty marcaProperty() {
        return marca;
    }

    public StringProperty modeloProperty() {
        return modelo;
    }

    public StringProperty serialProperty() {
        return serial;
    }

    public StringProperty responsableProperty() {
        return responsable;
    }
}
