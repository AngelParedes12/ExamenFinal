package com.example.examenp3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HelloController {


    @FXML
    public TableView<Activo> tableView;
    @FXML
    private TableColumn<Activo, String> TCid;
    @FXML
    private TableColumn<Activo, String> TCtipo;
    @FXML
    private TableColumn<Activo, String> TCmarca;
    @FXML
    private TableColumn<Activo, String> TCmodelo;
    @FXML
    private TableColumn<Activo, String> TCserial;
    @FXML
    private TableColumn<Activo, String> TCresponsable;

    @FXML
    private TextField TFid;
    @FXML
    private TextField TFmarca;
    @FXML
    private TextField TFmodelo;
    @FXML
    private TextField TFserial;
    @FXML
    private TextField TFresponsable;
    @FXML
    private ComboBox<String> CBtipo;

    @FXML
    private Button BTguardar;
    @FXML
    private Button BTlimpiar;

    private ObservableList<Activo> listaActivos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        CBtipo.setItems(FXCollections.observableArrayList("Desktop", "Laptop", "Impresora", "Scanner", "Switch", "Router", "Firewall"));

        TCid.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        TCtipo.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
        TCmarca.setCellValueFactory(cellData -> cellData.getValue().marcaProperty());
        TCmodelo.setCellValueFactory(cellData -> cellData.getValue().modeloProperty());
        TCserial.setCellValueFactory(cellData -> cellData.getValue().serialProperty());
        TCresponsable.setCellValueFactory(cellData -> cellData.getValue().responsableProperty());


        tableView.setItems(listaActivos);
    }

    @FXML
    void ONguardar(ActionEvent event) {
        String id = TFid.getText();
        String tipo = CBtipo.getValue();
        String marca = TFmarca.getText();
        String modelo = TFmodelo.getText();
        String serial = TFserial.getText();
        String responsable = TFresponsable.getText();


        if (id.isEmpty() || tipo == null || marca.isEmpty() || modelo.isEmpty() || serial.isEmpty() || responsable.isEmpty()) {
            showAlert("Error", "Todos los campos son obligatorios.");
            return;
        }


        Activo nuevoActivo = new Activo(id, tipo, marca, modelo, serial, responsable);


        listaActivos.add(nuevoActivo);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventario.txt", true))) {
            writer.write(String.format("ID: %s, Tipo: %s, Marca: %s, Modelo: %s, Serial: %s, Responsable: %s%n",
                    id, tipo, marca, modelo, serial, responsable));
            System.out.println("Activo guardado en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar el activo en el archivo: " + e.getMessage());
        }

        limpiarCampos();
    }

    @FXML
    void ONlimpiar(ActionEvent event) {
        limpiarCampos();
    }

    private void limpiarCampos() {
        TFid.clear();
        TFmarca.clear();
        TFmodelo.clear();
        TFserial.clear();
        TFresponsable.clear();
        CBtipo.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
