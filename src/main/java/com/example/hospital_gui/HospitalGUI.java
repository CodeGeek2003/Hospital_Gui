package com.example.hospital_gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;

public class HospitalGUI extends Application {
    private ArrayList<Person> persons;
    private Window primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        persons = new ArrayList<>();

        primaryStage.setTitle("Hospital Management System");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        Button showPatientsButton = new Button("Show all patients");
        Button addPatientButton = new Button("Add new patient");
        Button showDoctorsButton = new Button("Show all doctors");
        Button addDoctorButton = new Button("Add new doctor");

        showPatientsButton.setOnAction(e -> showAllPatients());
        addPatientButton.setOnAction(e -> addNewPatient(primaryStage));
        showDoctorsButton.setOnAction(e -> showAllDoctors());
        addDoctorButton.setOnAction(e -> addNewDoctor(primaryStage));

        gridPane.add(showPatientsButton, 0, 0);
        gridPane.add(addPatientButton, 1, 0);
        gridPane.add(showDoctorsButton, 0, 1);
        gridPane.add(addDoctorButton, 1, 1);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(gridPane);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAllPatients() {
        if (persons.isEmpty()) {
            showAlert("Patients", "No patients found.");
        } else {
            StringBuilder patientsInfo = new StringBuilder();
            for (Person person : persons) {
                if (person instanceof Patient) {
                    patientsInfo.append(person.toString()).append("\n");
                }
            }
            showAlert("Patients", patientsInfo.toString());
        }
    }

    private void addNewPatient(Stage primaryStage) {
        String name = showInputDialog(primaryStage, "Enter patient name:");
        String id = showInputDialog(primaryStage, "Enter patient ID:");
        String medicalCase = showInputDialog(primaryStage, "Enter patient medical case:");
        String attachedDoctor = showInputDialog(primaryStage, "Enter attached doctor:");

        if (name != null && id != null && medicalCase != null && attachedDoctor != null) {
            Person patient = new Patient(name, id, medicalCase, attachedDoctor);
            persons.add(patient);
            showAlert("Success", "Added patient: " + patient);
        } else {
            showAlert("Error", "Failed to add patient. Please provide all the required information.");
        }
    }

    private void showAllDoctors() {
        if (persons.isEmpty()) {
            showAlert("Doctors", "No doctors found.");
        } else {
            StringBuilder doctorsInfo = new StringBuilder();
            for (Person person : persons) {
                if (person instanceof Doctor) {
                    doctorsInfo.append(person.toString()).append("\n");
                }
            }
            showAlert("Doctors", doctorsInfo.toString());
        }
    }

    private void addNewDoctor(Stage primaryStage) {
        String name = showInputDialog(primaryStage, "Enter doctor name:");
        String id = showInputDialog(primaryStage, "Enter doctor ID:");
        String specialization = showInputDialog(primaryStage, "Enter doctor specialization:");

        if (name != null && id != null && specialization != null) {
            Person doctor = new Doctor(name, id, specialization);
            persons.add(doctor);
            showAlert("Success", "Added doctor: " + doctor);
        } else {
            showAlert("Error", "Failed to add doctor. Please provide all the required information.");
        }
    }

    private String showInputDialog(Stage primaryStage, String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setContentText(message);
        dialog.initOwner(primaryStage);
        dialog.showAndWait();
        return dialog.getResult();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.initOwner(primaryStage);
        alert.showAndWait();
    }



}
