package com.neurix.simrs.bpjs.eklaim.bo.model;

public class KlaimResponse {
    private String patientId;
    private String admissionId;
    private String hospitalAdmissionId;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(String admissionId) {
        this.admissionId = admissionId;
    }

    public String getHospitalAdmissionId() {
        return hospitalAdmissionId;
    }

    public void setHospitalAdmissionId(String hospitalAdmissionId) {
        this.hospitalAdmissionId = hospitalAdmissionId;
    }
}
