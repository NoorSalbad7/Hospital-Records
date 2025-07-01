/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author NOOR
 */
public class prescription {

    private int patient_id;
    private int medicine_id;
    private int doctor_id;
    private String date;

    public prescription() {
    }
    public prescription(int medicine_id, int doctor_id, String date) {
        this.medicine_id = medicine_id;
        this.doctor_id = doctor_id;
        this.date = date;
    }
    public prescription(int patient_id, int medicine_id, int doctor_id, String date) {
        this.patient_id = patient_id;
        this.medicine_id = medicine_id;
        this.doctor_id = doctor_id;
        this.date = date;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(int medicine_id) {
        this.medicine_id = medicine_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
