package com.example.hospital_gui;

class Patient extends Person {
    private String medicalCase;
    private String attachedDoctor;

    public Patient(String name, String id, String medicalCase, String attachedDoctor) {
        super(name, id);
        this.medicalCase = medicalCase;
        this.attachedDoctor = attachedDoctor;
    }

    public String getMedicalCase() {
        return medicalCase;
    }

    public String getAttachedDoctor() {
        return attachedDoctor;
    }

    @Override
    public String toString() {
        return super.toString() + ", Medical Case: " + medicalCase + ", Attached Doctor: " + attachedDoctor;
    }
}
