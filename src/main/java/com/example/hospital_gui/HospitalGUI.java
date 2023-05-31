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

    public HospitalGUI(Window primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        persons = new ArrayList<>();
        primaryStage.setTitle("Hospital Management System");
        GridPane ashraf = new GridPane();
        ashraf.setAlignment(Pos.CENTER);
        ashraf.setHgap(10);
        ashraf.setVgap(10);
        ashraf.setPadding(new Insets(10));

        Button showPatientsButton = new Button("Show all patients");
        Button addPatientButton = new Button("Add new patient");
        Button showDoctorsButton = new Button("Show all doctors");
        Button addDoctorButton = new Button("Add new doctor");

        showPatientsButton.setOnAction(e -> showAllPatients());
        addPatientButton.setOnAction(e -> addNewPatient());
        showDoctorsButton.setOnAction(e -> showAllDoctors());
        addDoctorButton.setOnAction(e -> addNewDoctor());

        ashraf.add(showPatientsButton, 0, 0);
        ashraf.add(addPatientButton, 1, 0);
        ashraf.add(showDoctorsButton, 0, 1);
        ashraf.add(addDoctorButton, 1, 1);

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(ashraf);

        Scene yahia = new Scene(vbox, 400, 300);
        primaryStage.setScene(yahia);
        primaryStage.show();
    }

    private void showAllPatients() {
        StringBuilder patientsInfo = new StringBuilder();
        for (Person person : persons) {
            if (person instanceof Patient) {
                patientsInfo.append(person.toString()).append("\n");
            }
        }
        if (patientsInfo.isEmpty()) {
            showAlert("Patients", "No patients found.");
        } else {
            showAlert("Patients", patientsInfo.toString());
        }
    }

    private void addNewPatient() {

        String name = showInputDialog("Enter patient name:");
        while (name.isEmpty()){
            showAlert("Error", "Please provide  a valid name.");
            name = showInputDialog( "Enter patient name:");
        }
        String id = showInputDialog( "Enter patient ID:");
        while (id.isEmpty()){
            showAlert("Error", "Please provide  a valid ID.");
            id = showInputDialog( "Enter patient ID:");
        }
        String medicalCase = showInputDialog( "Enter patient medical case:");
        while (medicalCase.isEmpty()){
            showAlert("Error", "Please provide  a valid medical case.");
            medicalCase = showInputDialog( "Enter patient medical case:");
        }
        String attachedDoctor = showInputDialog( "Enter attached doctor:");
        while (attachedDoctor.isEmpty()){
            showAlert("Error", "Please provide  a valid  doctor.");
            attachedDoctor = showInputDialog( "Enter attached doctor:");
        }
        Patient patient = new Patient(name, id, medicalCase, attachedDoctor);
        persons.add(patient);
        showAlert("Success", "Added patient: " + patient);
    }

    private void showAllDoctors() {
        StringBuilder doctorsInfo = new StringBuilder();
        for (Person person : persons) {
            if (person instanceof Doctor) {
                doctorsInfo.append(person.toString()).append("\n");
            }
        }
        if (doctorsInfo.isEmpty()) {
            showAlert("Doctors", "No doctors found.");
        } else {

            showAlert("Doctors", doctorsInfo.toString());
        }
    }

    private void addNewDoctor() {
        String name = showInputDialog( "Enter doctor name:");
        while (name.isEmpty()){
            showAlert("Error", "Please provide  a valid name.");
            name = showInputDialog("Enter doctor name:");
        }
        String id = showInputDialog( "Enter doctor ID:");
        while (id.isEmpty()){
            showAlert("Error", "Please provide  a valid ID.");
            id = showInputDialog( "Enter doctor ID:");
        }
        String specialization = showInputDialog( "Enter doctor specialization:");
        while (specialization.isEmpty()){
            showAlert("Error", "Please provide  a valid specialization.");
            specialization = showInputDialog( "Enter doctor specialization:");
        }
            Person doctor = new Doctor(name, id, specialization);
            persons.add(doctor);
            showAlert("Success", "Added doctor: " + doctor);
    }

    private String showInputDialog( String message) {
        TextInputDialog dialog = new TextInputDialog();
        //to get rid of that annoyingly big Confirmation with Question mark thingy
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
