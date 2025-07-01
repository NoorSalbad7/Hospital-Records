/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author NOOR
 */
public class Patient {

    private int id;
    private String name;
    private int age;
    private String gender;
    private String problem;
    private int doctor_id;
    private String entranceDate;

    public static ObservableList<Patient> patien = FXCollections.observableArrayList();

    public Patient() {
    }

    public Patient(int id, String name, int age, String gender, String problem, int doctor_id, String entranceDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.problem = problem;
        this.doctor_id = doctor_id;
        this.entranceDate = entranceDate;
    }


    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(String entranceDate) {
        this.entranceDate = entranceDate;
    }
}