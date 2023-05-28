package com.example.hospital_gui;

import com.example.hospital_gui.Person;

class Doctor extends Person {
    private String specialization;

    public Doctor(String name, String id, String specialization) {
        super(name, id);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return super.toString() + ", Specialization: " + specialization;
    }
}
